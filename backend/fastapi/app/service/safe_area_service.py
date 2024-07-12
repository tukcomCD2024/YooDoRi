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
            pass

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

    def generate_code(self):
        rng = RandomNumberGenerator()
        for _ in range(10):
            new_key = rng.generate_unique_random_number(100000, 999999)
        return new_key
