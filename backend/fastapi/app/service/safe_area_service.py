from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from datetime import datetime
from pytz import timezone

from ..database import Database
from .. import models
from ..bodymodel import *
from .user_service import UserService
from ..random_generator import RandomNumberGenerator


class SafeArea:
    def __init__(self, db: Session = Depends(Database().get_session)):
        self.db = db

    async def register_safe_area(self, request: RegisterSafeAreaRequest) -> CommonResponse:
        if not self.db.query(models.safe_area_info).filter(models.safe_area_info.dementia_key == request.dementiaKey, models.safe_area_info.group_key == request.groupKey, 
        models.safe_area_info.area_name == request.areaName).first() == None:
            raise(HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Safe area already exists in group"))

        _group_key = None

        if request.groupKey == '':
            _default_group = self.db.query(models.safe_area_group_info).filter(models.safe_area_group_info.dementia_key == request.dementiaKey, models.safe_area_group_info.group_name == '기본 그룹').first()
            if _default_group:
                _group_key = _default_group.group_key
            else:
                _group_key = self.generate_code()
                new_gorup = models.safe_area_group_info(
                    group_key = _group_key,
                    group_name = '기본 그룹',
                    dementia_key = request.dementiaKey
                )
                self.db.add(new_gorup)
        else:
            _group_key = request.groupKey

        _area_key = int(request.dementiaKey) + datetime.timestamp(datetime.now(timezone('Asia/Seoul'))) + ord(request.areaName[0])

        new_area = models.safe_area_info(
            dementia_key = request.dementiaKey,
            group_key = _group_key,
            area_key = _area_key,
            area_name = request.areaName,
            latitude = request.latitude,
            longitude = request.longitude,
            radius = request.radius
        )
        self.db.add(new_area)

        self.db.commit()

        return CommonResponse(
            status = 'success',
            message = 'Safe area information received'
        )

    async def register_safe_area_group(self, request : RegisterSafeAreaGroupRequest) -> RegisterSafeAreaGroupResponse:
        if self.db.query(models.safe_area_group_info).filter(models.safe_area_group_info.dementia_key == request.dementiaKey, models.safe_area_group_info.group_name == request.groupName).first():
            raise(HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Group name already exists"))
        else:
            new_group = models.safe_area_group_info(
                dementia_key = request.dementiaKey,
                group_key = self.generate_code(),
                group_name = request.groupName
            )    
            self.db.add(new_group)
            self.db.commit()

            return RegisterSafeAreaGroupResponse(
                status = 'success',
                message = 'Safe area group information received',
                result = {
                    'groupKey' : str(new_group.group_key)
                }
            )

    async def get_safe_area_info(self, dementiaKey : str) -> GetSafeAreaResponse:
        group_list = self.db.query(models.safe_area_group_info).filter(models.safe_area_group_info.dementia_key == dementiaKey).all()

        group_lists = []
        for group in group_list:
            group_lists.append({
                'groupKey' : str(group.group_key),
                'groupName' : group.group_name
            })

        return GetSafeAreaResponse(
            status = 'success',
            message = 'Safe area information received',
            result = {
                'groupList' : group_lists
            }
        )
        
    async def get_safe_area_list(self, dementiaKey : str, groupKey : str) -> GetSafeAreaGroupResponse:
        group_key = self.db.query(models.safe_area_group_info).filter(models.safe_area_group_info.dementia_key == dementiaKey, models.safe_area_group_info.group_key == groupKey).first()

        if not group_key:
            raise(HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Group does not exist"))
        
        safe_area_list = self.db.query(models.safe_area_info).filter(models.safe_area_info.dementia_key == dementiaKey, models.safe_area_info.group_key == groupKey).all()

        safe_area = []

        for safe in safe_area_list:
            safe_area.append({
                'areaName' : safe.area_name,
                'areaKey' : str(safe.area_key), 
                'latitude' : safe.latitude,
                'longitude' : safe.longitude,
                'radius' : safe.radius
            })

        return GetSafeAreaGroupResponse(
            status = 'success',
            message = 'Safe area information received',
            result = {
                'safeAreas' : safe_area
            }
        )

    async def get_safe_area_all(self, dementiaKey : str) -> GetSafeAreaAllResponse:
        safe_area_list = self.db.query(models.safe_area_info).filter(models.safe_area_info.dementia_key == dementiaKey).all()

        areas = []

        for area in safe_area_list:
            areas.append({
                'areaName' : area.area_name,
                'latitude' : area.latitude,
                'longitude' : area.longitude,
                'radius' : area.radius
            })

        return GetSafeAreaAllResponse(
            status = 'success',
            message = 'Safe area information received',
            result = {
                'safeAreas' : areas
            }
        )

    async def delete_safe_area(self, request : DeleteSafeAreaRequest) -> CommonResponse:
        existing_area = self.db.query(models.safe_area_info).filter(models.safe_area_info.dementia_key == request.dementiaKey, models.safe_area_info.area_key == request.areaKey).first()

        if not existing_area:
            raise HTTPException(status_code=404, detail="Safe area information not found")
            
        self.db.delete(existing_area)
        self.db.commit()

        return CommonResponse(
            status = 'success',
            message = 'Safe area information deleted'    
        )
    
    async def delete_safe_area_group(self, request : DeleteSafeAreaGroupRequest) -> CommonResponse:
        existing_group = self.db.query(models.safe_area_group_info).filter(models.safe_area_group_info.dementia_key == request.dementiaKey, models.safe_area_group_info.group_key == request.groupKey).first()

        not_grouped = self.db.query(models.safe_area_info).filter(models.safe_area_info.dementia_key == request.dementiaKey, models.safe_area_group_info.group_name == "기본 그룹").first()

        if not not_grouped:
            default_key = self.generate_code()

            new_group = models.safe_area_group_info(
                dementia_key = request.dementiaKey,
                group_key = default_key,
                group_name = "기본 그룹"
            )
            self.db.add(new_group)
        else:
            default_key = not_grouped.group_key
        
        if not existing_group:
            raise HTTPException(status_code=404, detail="Group information not found")
        else:
            safe_area_list = self.db.query(models.safe_area_info).filter(models.safe_area_info.group_key == existing_group.group_key).all()
            if safe_area_list:
                for safe in safe_area_list:
                    safe.group_key = default_key
            else:
                pass

            self.db.delete(existing_group)
            self.db.commit()

        return CommonResponse(
            status = 'success',
            message = 'Safe area group information deleted'
        )


    def generate_code(self):
        rng = RandomNumberGenerator()
        for _ in range(10):
            new_key = rng.generate_unique_random_number(100000, 999999)
        return new_key
