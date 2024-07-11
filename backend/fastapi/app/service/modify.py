from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from ..database import Database
from .. import models
from ..bodymodel import *

class ModifyService:
    def __init__(self, db: Session = Depends(Database().get_session)):
        self.db = db

    async def modify_userInfo(self, request):
        _is_dementia = request.isDementia

        if _is_dementia == 0:
            nok_info = self.db.query(models.nok_info).filter(models.nok_info.nok_key == request.key).first()

            if not nok_info:
                raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="NOK 정보 조회 실패")
            
            if not nok_info.nok_name == request.name:
                nok_info.nok_name = request.name
            
            if not nok_info.nok_phonenumber == request.phoneNumber:
                nok_info.nok_phonenumber = request.phoneNumber
            
            self.db.commit()

        elif _is_dementia == 1:
            dementia_info = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_key == request.key).first()

            if not dementia_info:
                raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="보호 대상자 정보 조회 실패")
            
            if not dementia_info.dementia_name == request.name:
                dementia_info.dementia_name = request.name
            
            if not dementia_info.dementia_phonenumber == request.phoneNumber:
                dementia_info.dementia_phonenumber = request.phoneNumber
            
            self.db.commit()
        
        return CommonResponse(
            status="success",
            message="User information modification success"
        )
    async def modify_update_rate(self, request):
        if request.isDementia == 0:
            nok_info = self.db.query(models.nok_info).filter(models.nok_info.nok_key == request.key).first()

            if not nok_info:
                raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="NOK 정보 조회 실패")
            
            if not nok_info.update_rate == request.updateRate:
                nok_info.update_rate = request.updateRate
            
        elif request.isDementia == 1:
            dementia_info = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_key == request.key).first()

            if not dementia_info:
                raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="보호 대상자 정보 조회 실패")
            
            if not dementia_info.update_rate == request.updateRate:
                dementia_info.update_rate = request.updateRate
        
        self.db.commit()

        return CommonResponse(
            status="success",
            message="Update rate modification success"
        )

