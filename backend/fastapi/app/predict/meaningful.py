from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from ..database import Database
from .. import models
from ..bodymodel import *


class MeaningfulLoc:
    def __init__(self, db = Depends(Database().get_session)):
        self.db = db

    async def get_meaningful_loc(self, dementiaKey) -> MeaningfulLocResponse:
        meaningful_loc_list = self.db.query(models.meaningful_location_info).filter(models.meaningful_location_info.dementia_key == dementiaKey).all()

        if not meaningful_loc_list:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="의미 장소 정보 조회 실패")
        
        mean_dict = {}

        for loc in meaningful_loc_list:
            address = loc.address
            day_of_week = loc.day_of_the_week
            time = loc.time

            if address not in mean_dict:
                police_list = self.db.query(models.police_info).filter(models.police_info.key == loc.key).order_by(models.police_info.distance).limit(3).all()
                
                pol_dicts = []

                for pol in police_list:
                    pol_info_dicts = {
                        'policeName': pol.policeName,
                        'policePhoneNumber': pol.policePhoneNumber,
                        'policeAddress': pol.policeAddress,
                        'distance': pol.distance,
                        'latitude': pol.latitude,
                        'longitude': pol.longitude
                    }
                    pol_dicts.append(pol_info_dicts)    

                mean_dict[address] = {
                    'address': address,
                    'timeInfo': [],
                    'latitude': loc.latitude,
                    'longitude': loc.longitude,
                    'policeStationInfo' : pol_dicts
                }

            time_info_list = mean_dict[address]['timeInfo']
            if {'dayOfTheWeek' : day_of_week, 'time' : time} not in time_info_list:
                time_info_list.append({'dayOfTheWeek' : day_of_week, 'time' : time})
                
        return MeaningfulLocResponse(
            status = 'success',
            message = 'Meaningful location data sent',
            result = MeaningfulLocRecord(
                meaningfulPlaces = list(mean_dict.values())
            )
        )