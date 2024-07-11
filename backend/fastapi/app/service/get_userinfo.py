from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from ..database import Database
from .. import models
from ..bodymodel import *
from ..config import Config


class GetUserInfo:
    def __init__(self, db: Session = Depends(Database().get_session)):
        self.db = db
    
    async def get_user_info(self, nokKey: str) -> GetUserInfoResponse:
        nok_info_record = self.db.query(models.nok_info).filter(models.nok_info.nok_key == nokKey).first()

        if not nok_info_record:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="NOK 정보 조회 실패")
        
        dementia_info_record = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_key == nok_info_record.dementia_info_key).first()

        if not dementia_info_record:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="보호 대상자 정보 조회 실패")
        
        return GetUserInfoResponse(
            status="success",
            message="User information received",
            result={
                "dementiaInfoRecord": {
                    "dementiaKey": dementia_info_record.dementia_key,
                    "dementiaName": dementia_info_record.dementia_name,
                    "dementiaPhoneNumber": dementia_info_record.dementia_phonenumber,
                    "updateRate": dementia_info_record.update_rate
                },
                "nokInfoRecord": {
                    "nokKey": nok_info_record.nok_key,
                    "nokName": nok_info_record.nok_name,
                    "nokPhoneNumber": nok_info_record.nok_phonenumber,
                    "updateRate": nok_info_record.update_rate
                }
            }
        )