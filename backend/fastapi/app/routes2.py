from fastapi import APIRouter, HTTPException, status, Depends, Request
from fastapi.security import OAuth2PasswordRequestForm, APIKeyHeader, OAuth2PasswordBearer

from apscheduler.schedulers.background import BackgroundScheduler
from passlib.context import CryptContext
from PyKakao import Local

from . import models
from .database import Database
from .bodymodel import *
from .util import JWTService
from .config import Config
from .schedularFunc import SchedulerFunc
from .validater import validateInSafeArea
from .fcm_notification import send_push_notification
from .service.user_service import UserService
from .service.location_service import LocService
from .service.modify import ModifyService
from .service.calculate_avg import CalculateAvg
from .service.get_userinfo import GetUserInfo
from .service.safe_area_service import SafeArea
from .predict.meaningful import MeaningfulLoc
from .predict.prediction import LocPredict

import asyncio


from openai import OpenAI




router = APIRouter()
db = Database()
session = next(db.get_session())
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")
jwt = JWTService()
schedFunc = SchedulerFunc()
sched = BackgroundScheduler(timezone="Asia/Seoul", daemon=True)
val = validateInSafeArea()
kakao = Local(service_key=Config.kakao_service_key)
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token")
client = OpenAI(
    api_key=Config.GPT_API_KEY,
)



#FCM 테스트
@router.post("/test/fcm", description="FCM 테스트", tags = ["Test"])
async def send_fcm(request: FCMRequest):
    
    await send_push_notification(request.token, request.title, request.body, request.data)

    return {"status": "success", "message": "FCM sent"}
#gpt test
@router.post("/gpt", description="GPT-3.5 테스트", tags = ["Test"])
async def gpt_test(request: GPTRequest):
    
    _key = request.dementiaKey
    _date = request.date

    try:
        loc_list = session.query(models.location_info).filter_by(dementia_key=_key, date=_date).all()

        if not loc_list:
            raise HTTPException(status_code=404, detail="Location data not found")

        route_descriptions = [f"({loc.latitude}, {loc.longitude})" for loc in loc_list]
        route_prompt = "User's travel route: " + " -> ".join(route_descriptions)

        response = client.chat.completions.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": route_prompt}
            ]
        )

        summary = response.choices[0].message['content'].strip()
        return {"summary": summary}
    
    
    except Exception as e:
        print(f"[ERROR] An error occurred: {e}")
        raise HTTPException(status_code=500, detail="An internal error occurred")
    
    finally:
        session.close()






#유저 등록
@router.post("/noks",status_code=status.HTTP_201_CREATED, responses = {201 : {"model" : ReceiveNokInfoResponse, "description" : "유저 등록 성공" },404: {"model": ErrorResponse, "description": "보호 대상자 키 조회 실패"}}, description="보호자가 보호 대상자의 정보를 등록 | fcmToken 없으면 그냥 빈칸으로 보낼 것", tags=["Register"])
async def receice_nok_info(request: ReceiveNokInfoRequest, user_service: UserService = Depends()):
    try:
        return await user_service.register_nok_info(request)
    
    except HTTPException as e:
        raise e

@router.post("/dementias", status_code=status.HTTP_201_CREATED, responses = {201 : {"model" : ReceiveDementiaInfoResponse, "description" : "유저 등록 성공" }}, description="보호 대상자의 정보를 등록 | fcmToken 없으면 그냥 빈칸으로 보낼 것", tags=["Register"])
async def receive_dementia_info(request: ReceiveDementiaInfoRequest, user_service: UserService = Depends()):

    try:
        return await user_service.register_dementia_info(request)
    
    except HTTPException as e:
        raise e

@router.post("/connection", responses = {200 : {"model" : ConnectionResponse, "description" : "연결 확인 성공" }, 400: {"model": ErrorResponse, "description": "연결 실패"}}, description="보호자와 보호 대상자의 연결 확인", tags=["Connection"])
async def is_connected(request: ConnectionRequest, user_service: UserService = Depends()):
    try:
        return await user_service.check_connection(request)
    
    except HTTPException as e:
        raise e

@router.post("/login", responses = {200 : {"model" : CommonResponse, "description" : "로그인 성공" }, 400: {"model": ErrorResponse, "description": "로그인 실패"}}, description="보호자와 보호 대상자의 로그인 | isDementia : 0(보호자), 1(보호 대상자)", tags=["Login"])
async def receive_user_login(request: loginRequest, user_service: UserService = Depends()):
    try:
        return await user_service.auto_login(request)
    
    except HTTPException as e:
        raise e



#위치 정보 전송
@router.post("/locations/dementias", responses = {200 : {"model" : TempResponse, "description" : "위치 정보 전송 성공" }, 404: {"model": ErrorResponse, "description": "보호 대상자 키 조회 실패"}}, description="보호 대상자의 위치 정보를 전송 | isRingstoneOn : 0(무음), 1(진동), 2(벨소리)", tags=["Location"])
async def receive_location_info(request: ReceiveLocationRequest, loc_service: LocService = Depends()):
    try:
        return await loc_service.register_location(request)
    
    except HTTPException as e:
        raise e

@router.get("/locations/noks", responses = {200 : {"model" : GetLocationResponse, "description" : "위치 정보 전송 성공" }, 404: {"model": ErrorResponse, "description": "위치 정보 없음"}}, description="보호자에게 보호 대상자의 위치 정보를 전송(쿼리 스트링) | userStatus : 1(정지), 2(도보), 3(차량), 4(지하철) | isRingstoneOn : 0(무음), 1(진동), 2(벨소리)", tags=["Location"])
async def send_live_location_info(dementiaKey : str, loc_service : LocService = Depends()):
    try:
        return await loc_service.send_live_location_info(dementiaKey)
    
    except HTTPException as e:
        raise e

# 유저 정보 수정
@router.post("/users/modification/userInfo", responses = {200 : {"model" : CommonResponse, "description" : "유저 정보 수정 성공" }, 404: {"model": ErrorResponse, "description": "유저 키 조회 실패"}}, description="보호자와 보호대상자의 정보를 수정 | isDementia : 0(보호자), 1(보호대상자) | 변경하지 않는 값은 기존의 값을 그대로 수신할 것", tags=["User"])
async def modify_user_info(request: ModifyUserInfoRequest, modify : ModifyService = Depends()):
    try:
        return await modify.modify_userInfo(request)
    
    except HTTPException as e:
        raise e

@router.post("/users/modification/updateRate", responses = {200 : {"model" : CommonResponse, "description" : "업데이트 주기 수정 성공" }, 404: {"model": ErrorResponse, "description": "유저 키 조회 실패"}}, description="보호자와 보호대상자의 업데이트 주기를 수정 | isDementia : 0(보호자), 1(보호대상자)", tags=["User"])
async def modify_updatint_rate(request: ModifyUserUpdateRateRequest, modify : ModifyService = Depends()):
    try:
        return await modify.modify_update_rate(request)
    
    except HTTPException as e:
        raise e

#유저 정보 전달
@router.post("/dementias/averageWalkingSpeed", responses = {200 : {"model" : AverageWalkingSpeedResponse, "description" : "평균 걷기 속도 계산 성공" }, 404: {"model": ErrorResponse, "description": "보호 대상자 키 조회 실패 or 위치 정보 부족"}}, description="보호 대상자의 평균 걷기 속도를 계산 및 마지막 정보 전송", tags=["User"])
async def caculate_dementia_average_walking_speed(requset: AverageWalkingSpeedRequest, cal_service : CalculateAvg = Depends()): # current_user : int = Depends(APIKeyHeader(name = "Authorization"))
    try:
        return await cal_service.calculate_avg(requset)
    
    except HTTPException as e:
        raise e

@router.get("/users/info", responses = {200 : {"model" : GetUserInfoResponse, "description" : "유저 정보 전송 성공" }, 404: {"model": ErrorResponse, "description": "유저 정보 없음"}}, description="보호자와 보호 대상자 정보 전달(쿼리 스트링)", tags=["User"])
async def get_user_info(nokKey : str, get_info : GetUserInfo = Depends()):
    try:
        return await get_info.get_user_info(nokKey)
    
    except HTTPException as e:
        raise e

#의미장소, 위치 이력, 위치 예측
@router.get("/locations/meaningful", responses = {200 : {"model" : MeaningfulLocResponse, "description" : "의미장소 전송 성공" }, 404: {"model": ErrorResponse, "description": "의미 장소 없음"}}, description="보호 대상자의 의미 장소 정보 및 주변 경찰서 정보 전달(쿼리 스트링)", tags=["Location"])
async def send_meaningful_location_info(dementiaKey: str, mean_service : MeaningfulLoc = Depends()):
    try:
        return await mean_service.get_meaningful_loc(dementiaKey)
    
    except HTTPException as e:
        raise e

@router.get("/locations/history", responses = {200 : {"model" : LocHistoryResponse, "description" : "위치 이력 전송 성공" }, 404: {"model": ErrorResponse, "description": "위치 이력 없음"}}, description="보호 대상자의 위치 이력 정보 전달(쿼리 스트링) | distance는 현재 값과 다음 값과의 거리 | date : YYYY-MM-DD", tags=["Location"])
async def send_location_history(date: str, dementiaKey: str, loc_service : LocService = Depends()):
    try:
        return await loc_service.send_location_history(date, dementiaKey)
    
    except HTTPException as e:
        raise e

@router.get("/locations/predict", responses = {200 : {"model" : PredictLocationResponse, "description" : "위치 예측 성공" }, 404: {"model": ErrorResponse, "description": "위치 정보 부족"}}, description="보호 대상자의 다음 위치 예측(쿼리 스트링) | 2주치 위치 데이터 사용(임시)", tags=["Location"])
async def predict_location(dementiaKey: str, pred_service : LocPredict = Depends()):
    try:
        return await pred_service.predict_location(dementiaKey)
    
    except HTTPException as e:
        raise e
    

@router.get("/locations/predict/gura", tags = ["Location"])
async def predict_location(dementiaKey : str):
    
    try:
        # db 에서 의미장소 정보 가져오기
        meaningful_location_list = session.query(models.meaningful_location_info).filter_by(dementia_key = dementiaKey, address = '서울 중구 정동 5-8').limit(1).all()
        police_info = session.query(models.police_info).filter_by(key = meaningful_location_list[0].key).order_by(models.police_info.distance).limit(3).all()

        for police in police_info:
            del police.num
            del police.key

        pred_loc = {
            "latitude" : meaningful_location_list[0].latitude,
            "longitude" : meaningful_location_list[0].longitude,
            "address" : meaningful_location_list[0].address
        }

        result = {
            'predictLocation' : pred_loc,
            'policeInfo' : police_info
        }

        response = {
            'status': 'success',
            'message': 'Predict location data sent',
            'result': result
        
        }

        #time.sleep(10)

        return response
    finally:
        session.close()


#안심구역
@router.post("/safeArea/register", status_code=status.HTTP_201_CREATED, responses = {201 : {"model" : CommonResponse, "description" : "안전 지역 등록 성공" }, 404: {"model": ErrorResponse, "description": "보호 대상자 키 조회 실패"}}, description="보호 대상자의 안전 지역을 등록 | 단위는 km | groupName이 없으면 notGrouped로 저장됨", tags = ["SafeArea"])
async def register_safe_area(request: RegisterSafeAreaRequest, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.register_safe_area(request)

    except HTTPException as e:
        raise e

@router.post("/safeArea/register/group", status_code=status.HTTP_201_CREATED, responses = {201 : {"model" : RegisterSafeAreaGroupResponse, "description" : "안전 지역 그룹 등록 성공" }, 404: {"model": ErrorResponse, "description": "보호 대상자 키 조회 실패"}}, description="보호 대상자의 안전 지역 그룹을 등록", tags = ["SafeArea"])
async def register_safe_area_group(request: RegisterSafeAreaGroupRequest, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.register_safe_area_group(request)
    
    except HTTPException as e:
        raise e

@router.get("/safeArea/info", responses = {200 : {"model" : GetSafeAreaResponse, "description" : "안전 지역 정보 전송 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 정보 없음"}}, description="보호 대상자의 안전 지역 정보 전달(쿼리 스트링)", tags = ["SafeArea"])
async def get_safe_area_info(dementiaKey: str, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.get_safe_area_info(dementiaKey)
    
    except HTTPException as e:
        raise e

@router.get("/safeArea/info/group", responses = {200 : {"model" : GetSafeAreaGroupResponse, "description" : "안전 지역 그룹 정보 전송 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 그룹 정보 없음"}}, description="보호 대상자의 특정 안전 지역 그룹 정보 전달(쿼리 스트링)", tags = ["SafeArea"])
async def get_safe_area_group_info(dementiaKey: str, groupKey: str, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.get_safe_area_list(dementiaKey, groupKey)
    
    except HTTPException as e:
        raise e
    
@router.get("/safeArea/info/all", responses = {200 : {"model" : GetSafeAreaAllResponse, "description" : "전체 안전 지역 정보 전송 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 정보 없음"}}, description="보호 대상자의 전체 안전 지역 정보 전달(쿼리 스트링)", tags = ["SafeArea"])
async def get_safe_area_all_info(dementiaKey: str, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.get_safe_area_all(dementiaKey)
    
    except HTTPException as e:
        raise e

@router.post("/safeArea/modification/name", responses = {200 : {"model" : CommonResponse, "description" : "안전 지역 정보 수정 성공" }, 400 : {"model" : ErrorResponse, "description" : "안심 구역 이름 중복"},404: {"model": ErrorResponse, "description": "안전 지역 정보 없음"}}, description="보호 대상자의 안전 지역 정보 수정", tags = ["SafeArea"])
async def modify_name_safe_area_info(request: ModifySafeAreaName):
    try:
        _dementaia_key = request.dementiaKey
        _area_key = request.areaKey
        _after_name = request.afterAreaName

        existing_area = session.query(models.safe_area_info).filter_by(dementia_key = _dementaia_key, area_key = _area_key).first()

        if existing_area:
            if not session.query(models.safe_area_info).filter_by(dementia_key = _dementaia_key, area_name = _after_name).first() == None:
                print(f"[ERROR] Safe area already exists for {_dementaia_key}")

                raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Safe area already exists")
            else:
                existing_area.area_name = _after_name
                session.commit()

                print(f"[INFO] Safe area name modified for {_dementaia_key}")

                response = {
                    'status': 'success',
                    'message': 'Safe area name modified'
                }

                return response
        else:
            raise HTTPException(status_code=404, detail="Safe area information not found")

    finally:
        session.close()

@router.post("/safeArea/modification/group", responses = {200 : {"model" : CommonResponse, "description" : "안전 지역 정보 수정 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 정보 없음"}}, description="보호 대상자의 안전 지역 그룹 이동 | areaKey : 옮기고자 하는 안심구역, groupKey : 옮길 그룹", tags = ["SafeArea"])
async def modify_group_safe_area_info(request: ModifySafeAreaGroup):
    try:
        _dementia_key = request.dementiaKey
        _area_key = request.areaKey
        _group_key = request.groupKey

        existing_area = session.query(models.safe_area_info).filter_by(dementia_key = _dementia_key, area_key = _area_key).first()

        if existing_area:
            before_group = session.query(models.safe_area_group_info).filter_by(group_key = existing_area.group_key).first()
            after_group = session.query(models.safe_area_group_info).filter_by(group_key = _group_key, dementia_key = _dementia_key).first()
            
            if after_group:
                existing_area.group_key = after_group.group_key
            else:
                raise HTTPException(status_code=404, message="Safe area group information not found")
            
            if session.query(models.safe_area_info).filter_by(group_key = before_group.group_key).count() == 0:
                session.delete(before_group)
            else:
                pass

            session.commit()
            
            print(f"[INFO] Safe area group modified for {_dementia_key}")

            response = {
                'status': 'success',
                'message': 'Safe area group modified'
            }

            return response
        
        else:
            return ErrorResponse(status_code=404, message="Safe area information not found")
        
        
    finally:
        session.close()

@router.post("/safeArea/modification/groupName", responses = {200 : {"model" : CommonResponse, "description" : "안전 지역 그룹 정보 수정 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 그룹 정보 없음"}}, description="보호 대상자의 안전 지역 그룹 이름 수정", tags = ["SafeArea"])
async def modify_group_name_safe_area_info(request: ModifySafeAreaGroupName):
    try:
        _dementia_key = request.dementiaKey
        _group_key = request.groupKey
        _after_group_name = request.afterGroupName

        existing_group = session.query(models.safe_area_group_info).filter_by(dementia_key = _dementia_key, group_key = _group_key).first()

        if existing_group:
            existing_group.group_name = _after_group_name
            session.commit()

            print(f"[INFO] Safe area group name modified for {_dementia_key}")

            response = {
                'status': 'success',
                'message': 'Safe area group name modified'
            }

            return response
        
        else:
            raise HTTPException(status_code=404, message="Safe area group information not found")
        
    finally:
        session.close()

@router.delete("/safeArea/delete", responses = {200 : {"model" : CommonResponse, "description" : "안전 지역 삭제 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 정보 없음"}}, description="보호 대상자의 안전 지역 삭제", tags = ["SafeArea"])
async def delete_safe_area(request: DeleteSafeAreaRequest, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.delete_safe_area(request)
    
    except HTTPException as e:
        raise e

@router.delete("/safeArea/delete/group", responses = {200 : {"model" : CommonResponse, "description" : "안전 지역 그룹 삭제 성공" }, 404: {"model": ErrorResponse, "description": "안전 지역 그룹 정보 없음"}}, description="보호 대상자의 안전 지역 그룹 삭제", tags = ["SafeArea"])
async def delete_safe_area_group(request: DeleteSafeAreaGroupRequest, safe_area_service : SafeArea = Depends()):
    try:
        return await safe_area_service.delete_safe_area_group(request)
    
    except HTTPException as e:
        raise e


#유틸
@router.post("/address/conversion", responses = {200 : {"model" : AddressConversionResponse, "description" : "주소 변환 성공" }, 404: {"model": ErrorResponse, "description": "주소 변환 실패"}}, description="주소를 위경도로 변환", tags = ["Util"])
async def address_conversion(request: AddressConversionRequest):
    try:
        _address = request.address

        xy = kakao.search_keyword(_address)

        latitude = xy['documents'][0]['y']
        longitude = xy['documents'][0]['x']

        result = {
            'latitude': latitude,
            'longitude': longitude
        }

        response = {
            'status': 'success',
            'message': 'Address conversion complete',
            'result': result
        }
    
        return response
    
    except Exception as e:
        print(f"[ERROR] Address conversion failed: {e}")

        raise HTTPException(status_code=404, detail=f"{e}")

    finally:
        session.close()





'''@sched.scheduled_job('cron', hour=0, minute=0, id = 'analyze_location_data')
def analyzing_location_data():
    asyncio.run(schedFunc.load_analyze_location_data(session))

@sched.scheduled_job('cron', hour=0, minute=59, id = 'geocoding')
def geocoding():
    asyncio.run(schedFunc.load_kakao_api(session))'''

