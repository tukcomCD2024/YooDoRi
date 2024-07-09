from fastapi import HTTPException, status, Depends
from sqlalchemy.orm import Session
from ..database import Database
from .. import models
from ..bodymodel import *
from ..random_generator import RandomNumberGenerator

class UserService:
    def __init__(self, db: Session = Depends(Database().get_session)):
        self.db = db
        self.rng = RandomNumberGenerator()

    async def register_nok_info(self, request: ReceiveNokInfoRequest) -> ReceiveNokInfoResponse:
        dementia_info = self.get_dementia_info(request.keyFromDementia)
        nok_info = self.get_nok_info(dementia_info.dementia_key, request.nokName, request.nokPhoneNumber)

        if nok_info:
            self.update_fcm_token_if_needed(nok_info, request.fcmToken)
            nok_key = nok_info.nok_key
        else:
            nok_key = self.create_nok_info(dementia_info.dementia_key, request.nokName, request.nokPhoneNumber, request.fcmToken)

        return ReceiveNokInfoResponse(
            status="success",
            message="NOK information received",
            result={
                "dementiaInfoRecord": {
                    "dementiaKey": dementia_info.dementia_key,
                    "dementiaName": dementia_info.dementia_name,
                    "dementiaPhoneNumber": dementia_info.dementia_phonenumber
                },
                "nokKey": nok_key
            }
        )
    
    async def register_dementia_info(self, request: ReceiveDementiaInfoRequest) -> ReceiveDementiaInfoResponse:
        dementia_info = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_name == request.name, models.dementia_info.dementia_phonenumber == request.phoneNumber).first()

        if dementia_info:
            _key = dementia_info.dementia_key
        else:
            _key = self.create_dementia_info(request.name, request.phoneNumber)

        return ReceiveDementiaInfoResponse(
            status="success",
            message="Dementia information received",
            result={
                "dementiaKey": _key
            }
        )
    
    async def check_connection(self, request: ConnectionRequest) -> ConnectionResponse:
        nok_info = self.db.query(models.nok_info).filter(models.nok_info.dementia_info_key == request.dementiaKey).first()

        if not nok_info:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="NOK 정보 조회 실패")
        
        return ConnectionResponse(
            status="success",
            message="Connection check success",
            result={
                "nokInfoRecord": {
                    "nokKey": nok_info.nok_key,
                    "nokName": nok_info.nok_name,
                    "nokPhoneNumber": nok_info.nok_phonenumber,
                    "updateRate": nok_info.update_rate
                }
            }
        )
    async def auto_login(self, request: loginRequest) -> CommonResponse:
        _isDementia = request.isDementia

        if _isDementia == 0:
            nok_info = self.db.query(models.nok_info).filter(models.nok_info.nok_key == request.key).first()

            if not nok_info:
                raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="NOK 정보 조회 실패")
            
        elif _isDementia == 1:
            dementia_info = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_key == request.key).first()

            if not dementia_info:
                raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="보호 대상자 정보 조회 실패")
            
        return CommonResponse(
            status="success",
            message="User login success"
        )

    def get_dementia_info(self, key_from_dementia: str):
        dementia_info = self.db.query(models.dementia_info).filter(models.dementia_info.dementia_key == key_from_dementia).first()
        if not dementia_info:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="보호 대상자 키 조회 실패")
        return dementia_info

    def get_nok_info(self, dementia_key: str, nok_name: str, nok_phonenumber: str):
        return self.db.query(models.nok_info).filter(
            models.nok_info.nok_name == nok_name,
            models.nok_info.nok_phonenumber == nok_phonenumber,
            models.nok_info.dementia_info_key == dementia_key
        ).first()

    def update_fcm_token_if_needed(self, nok_info, fcm_token: str):
        if nok_info.fcm_token != fcm_token:
            nok_info.fcm_token = fcm_token
            self.db.commit()

    def create_dementia_info(self, dementia_name: str, dementia_phonenumber: str) -> str:
        unique_key = self.generate_unique_key()
        new_dementia = models.dementia_info(
            dementia_key=unique_key,
            dementia_name=dementia_name,
            dementia_phonenumber=dementia_phonenumber
        )
        self.db.add(new_dementia)
        self.db.commit()
        return unique_key
    
    def create_nok_info(self, dementia_key: str, nok_name: str, nok_phonenumber: str, fcm_token: str) -> str:
        unique_key = self.generate_unique_key()
        new_nok = models.nok_info(
            nok_key=unique_key,
            nok_name=nok_name,
            nok_phonenumber=nok_phonenumber,
            dementia_info_key=dementia_key,
            update_rate=1,
            fcm_token=fcm_token
        )
        self.db.add(new_nok)
        self.db.commit()
        return unique_key

    def generate_unique_key(self) -> str:
        for _ in range(10):
            unique_key = self.rng.generate_unique_random_number(100000, 999999)
            if not self.db.query(models.nok_info).filter(models.nok_info.nok_key == unique_key).first():
                return str(unique_key)
        raise HTTPException(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, detail="Unique key generation failed")
