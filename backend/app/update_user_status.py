from sklearn.tree import DecisionTreeClassifier
import json
import pandas as pd
from io import StringIO
import pickle

class UpdateUserStatus:
    def __init__(self):

        model_filename = 'app/random_forest_model.pkl'
        with open(model_filename, 'rb') as model_file:
            self.model = pickle.load(model_file)

    def predict(self, data):
        # 전처리 함수 호출
        preprocessed_data = self.preprocessing(data)
        
        # 모델 예측
        prediction = self.model.predict(preprocessed_data)
        return prediction

    def preprocessing(self, data):
        # 만약 이미 JSON 문자열이라면 변환할 필요가 없음
        try:
           json_data = json.loads(data)
        except json.JSONDecodeError:
            # 이미 JSON 형식이라면 변환할 필요 없음
            json_data = data

        # 딕셔너리를 데이터프레임으로 변환
        df = pd.DataFrame([json_data])

        # 가속도 센서, 자이로 센서, 방향 센서 값만 추출
        selected_columns = ['accelerationsensor_x', 'accelerationsensor_y', 'accelerationsensor_z',
                            'gyrosensor_x', 'gyrosensor_y', 'gyrosensor_z',
                            'directionsensor_x', 'directionsensor_y', 'directionsensor_z']
        data = df[selected_columns]

        return data
