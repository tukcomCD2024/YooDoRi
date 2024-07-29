from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from ..database import Database
from .. import models
from ..bodymodel import *
from ..config import Config

from PyKakao import Local


class CalculateAvg:
    def __init__(self, db: Session = Depends(Database().get_session)):
        self.db = db
        self.kakao = Local(service_key=Config.kakao_service_key)

    async def calculate_avg(self, request: AverageWalkingSpeedRequest) -> AverageWalkingSpeedResponse:
        loc_list = self.db.query(models.location_info).filter(models.location_info.dementia_key == request.dementiaKey, models.location_info.user_status == "도보").order_by(models.location_info.num.desc()).limit(10).all()

        if not loc_list:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Location 정보 조회 실패")
        
        sum_speed = 0
        for loc in loc_list:
            sum_speed += float(loc.current_speed)

        avg_speed = round(sum_speed / len(loc_list), 2)

        geo = self.kakao.geo_coord2address(loc_list[0].longitude, loc_list[0].latitude)

        if not geo['documents'][0]['road_address'] == None:
            xy2address = geo['documents'][0]['road_address']['address_name'] + " " + geo['documents'][0]['road_address']['building_name']
        else:
            xy2address = geo['documents'][0]['address']['address_name']

        return AverageWalkingSpeedResponse(
            status = 'success',
            message = 'Average walking speed calculated',
            result = {
                'averageSpeed': avg_speed,
                'lastLatitude': loc_list[0].latitude,
                'lastLongitude': loc_list[0].longitude,
                'addressName' : xy2address
            }
        )