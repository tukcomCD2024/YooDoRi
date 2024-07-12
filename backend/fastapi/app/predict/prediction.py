from fastapi import HTTPException, status, Depends
from datetime import datetime, timedelta
from sqlalchemy import and_, func
from PyKakao import Local

from ..database import Database
from .. import models
from ..bodymodel import *
from ..user_status_convertor import convertor
from .LocationPredict import ForecastLSTMClassification, Preprocessing
from ..config import Config

import pandas as pd


class LocPredict:
    def __init__(self, db = Depends(Database().get_session)):
        self.db = db
        self.today = datetime.today()
        self.kakao = Local(service_key=Config.kakao_service_key)

    async def predpredict_locationict_loc(self, dementiaKey) -> PredictLocationResponse:
        loc_list = []
        
        #2주전 날짜
        two_weeks_ago = self.today - timedelta(days=14)

        location_list = self.db.query(models.location_info).filter(
            and_(
                models.location.dementia_key == dementiaKey,
                func.STR_TO_DATE(models.location_info.date, '%Y-%m-%d') >= two_weeks_ago,
                func.STR_TO_DATE(models.location_info.date, '%Y-%m-%d') <= self.today
            )
        ).all()

        if not location_list:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Location 정보 조회 실패")
        
        for location in location_list:
            status = convertor(location.user_status)
            loc_list.append({
                'date' : location.date,
                'time' : location.time,
                'latitude' : location.latitude,
                'longitude' : location.longitude,
                'userStatus' : status
            })
        
        #dataframe으로 변환
        loc_list_df = loc_list = pd.DataFrame(loc_list, columns = ['date', 'time','latitude', 'longitude', 'userStatus'])

        pr = Preprocessing(loc_list_df)
        df, meaningful_df = pr.run_analysis()

        test_idx = int(len(df)*0.8)
        df_train = df.iloc[:test_idx]
        df_test = df.iloc[test_idx:]

        seq_len = 5
        steps = 5
        single_output = False
        metrics = ['accuracy']
        lstm_params = {
            "seq_len": seq_len,
            "epochs": 100,  # epochs 반복 횟수
            "patience": 30,  # early stopping 조건
            "steps_per_epoch": 5,  # 1 epochs 시 dataset을 5개로 분할하여 학습
            "learning_rate": 0.03,
            "lstm_units": [64, 32],  # Dense Layer: 2, Unit: (64, 32)
            "activation": "softmax",
            "dropout": 0,
            "validation_split": 0.3,  # 검증 데이터셋 30%
        }
        fl = ForecastLSTMClassification(class_num=len(df['y'].unique()))
        model = fl.fit_lstm(
            df = df_train,
            steps = steps,
            single_output = single_output,
            verbose = True,
            metrics = metrics,
            **lstm_params
        )
        y_pred = fl.pred(
            df = df_test,
            num_classes=len(df['y'].unique()),
            seq_len = seq_len,
            single_output = single_output
        )

        pred_loc = meaningful_df.iloc[y_pred].iloc[-1]

        geo = self.kakao.geo_coord2address(pred_loc.longitude, pred_loc.latitude)

        if not geo['documents'][0]['road_address'] == None:
            xy2addr = geo['documents'][0]['road_address']['address_name'] + " " + geo['documents'][0]['road_address']['building_name']
                    
        else:
            xy2addr = geo['documents'][0]['address']['address_name']

        police = self.kakao.search_keyword("경찰서", x = pred_loc.longitude, y = pred_loc.latitude, sort = 'distance')

        police_list = []
        if police['meta']['total_count'] == 0:
            print(f"[INFO] No police station found near {xy2addr}")
        else:
            for pol in police['documents']:
                if not pol['phone'] == '':
                    new_police = {
                        "policeName" :  pol['place_name'],
                        "policeAddress" : pol['road_address_name'],
                        "policePhoneNumber" : pol['phone'],
                        "distance" : pol['distance'],
                        "latitude" : pol['y'],
                        "longitude" : pol['x']
                        }
                    
                    police_list.append(new_police)
                else:
                    pass
        
        return PredictLocationResponse(
            status = 'success',
            message = 'Predicted location received',
            result = {
                'predictLocation': {
                    'latitude': pred_loc.latitude,
                    'longitude': pred_loc.longitude,
                    'address': xy2addr,
                    'policeInfo': police_list[:3]
                }
            }
        )
