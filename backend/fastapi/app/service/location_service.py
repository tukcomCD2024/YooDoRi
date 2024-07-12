from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from ..database import Database
from .. import models
from ..bodymodel import *
from ..update_user_status import UpdateUserStatus
from ..validater import validateInSafeArea
from ..user_status_convertor import convertor2

from haversine import haversine


class LocService:
    def __init__(self, db: Session = Depends(Database().get_session)):
        self.db = db
        self.usu = UpdateUserStatus()
        self.val = validateInSafeArea()
    
    async def register_location(self, request: ReceiveLocationRequest) -> TempResponse:
        dementia_info = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_key == request.dementiaKey).first()
        nok_info = self.db.query(models.nok_info).filter(models.nok_info.dementia_info_key == request.dementiaKey).all()
        safe_area_list = self.db.query(models.safe_area_info).filter(models.safe_area_info.dementia_key == request.dementiaKey).all()
        latest_loc = self.db.query(models.location_info).filter(models.location_info.dementia_key == request.dementiaKey).order_by(models.location_info.num.desc()).first()

        current_loc = (request.latitude, request.longitude)

        if dementia_info:
            accel = request.accelerationSensor
            gyro = request.gyroSensor
            direc = request.directionSensor

            prediction = self.usu.predict(accel, gyro, direc)

            _near_safe_area, _isInSafeArea = self.val.isinsafearea(current_loc, safe_area_list)

            new_loc = self.register_loc(request, convertor2(int(prediction[0])), _near_safe_area, _isInSafeArea)
            self.db.add(new_loc)
            self.db.commit()

            if nok_info:
                for nok in nok_info:
                    if nok.fcm_token == None:
                        pass
                    else:
                        await self.val.pushNotification(nok.fcm_token, new_loc, latest_loc, _near_safe_area)
            else:
                pass

            return TempResponse(
                status="success",
                message="Location information received",
                result = int(prediction[0])
                
            )
        
    async def send_live_location_info(self, dementia_key) -> GetLocationResponse:
        latest_loc = self.db.query(models.location_info).filter(models.location_info.dementia_key == dementia_key).order_by(models.location_info.num.desc()).first()
        
        if latest_loc:
            return GetLocationResponse(
                status="success",
                message="Location information received",
                result={
                    "latitude": latest_loc.latitude,
                    "longitude": latest_loc.longitude,
                    "bearing": latest_loc.bearing,
                    "currentSpeed": latest_loc.current_speed,
                    "userStatus": latest_loc.user_status, # 1: 정지, 2: 도보, 3: 차량, 4: 지하철
                    "battery": latest_loc.battery,
                    "isInternetOn": latest_loc.isInternetOn,
                    "isGpsOn": latest_loc.isGpsOn,
                    "isRingstoneOn": latest_loc.isRingstoneOn # 0 : 무음, 1 : 진동, 2 : 벨소리
                }
            )
        else:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="최신 위치 정보 조회 실패")
    
    async def send_location_history(self, date, dementia_key) -> LocHistoryResponse:
        loc_list = self.db.query(models.location_info).filter(models.location_info.dementia_key == dementia_key, models.location_info.date == date).all()

        if not loc_list:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Location 정보 조회 실패")
        
        loc_history = []
        prev_loc = None

        for index, location in enumerate(loc_list):
            current_loc = (location.latitude, location.longitude)
            distance = 0

            if prev_loc:
                distance = round(haversine(prev_loc, current_loc, unit = 'm'), 2)

            if not loc_history or location.user_status != "정지" or loc_history[-1]['userStatus'] != "정지" :
                loc_history.append({
                    'latitude': location.latitude,
                    'longitude': location.longitude,
                    'time' : location.time,
                    'userStatus': location.user_status,
                    'distance': distance
                })
            else:
                loc_history[-1]['time'] = loc_history[-1]['time'] + " ~ " + location.time

            prev_loc = current_loc

            if index == len(loc_list) - 1:
                loc_history[-1]['distance'] = 0

        return LocHistoryResponse(
            status="success",
            message="Location history received",
            result  = {
                "locationHistory": loc_history
            }
        )
    
    def register_loc(self, request, status, _near_safe_area, _isInSafeArea):
        accel = request.accelerationSensor
        gyro = request.gyroSensor
        direc = request.directionSensor

        new_location = models.location_info(
                dementia_key = request.dementiaKey,
                date = request.date,
                time = request.time,
                latitude = request.latitude,
                longitude = request.longitude,
                bearing = request.bearing,
                user_status = status,
                accelerationsensor_x = accel[0],
                accelerationsensor_y = accel[1],
                accelerationsensor_z = accel[2],
                directionsensor_x = direc[0],
                directionsensor_y = direc[1],
                directionsensor_z = direc[2],
                gyrosensor_x = gyro[0],
                gyrosensor_y = gyro[1],
                gyrosensor_z = gyro[2],
                lightsensor = request.lightSensor[0],
                battery = request.battery,
                isInternetOn = request.isInternetOn,
                isRingstoneOn = request.isRingstoneOn,
                isGpsOn = request.isGpsOn,
                current_speed = request.currentSpeed,
                isInSafeArea = _isInSafeArea,
                nearSafeArea = _near_safe_area.area_key
            )
        return new_location


