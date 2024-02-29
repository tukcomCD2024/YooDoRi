from flask import Blueprint, request, jsonify
from .models import db, dementia_info, nok_info, location_info
from .random_generator import RandomNumberGenerator
from .update_user_status import UpdateUserStatus
from sqlalchemy import text
import json


# 블루프린트 생성
nok_info_routes = Blueprint('nok_info_routes', __name__)
dementia_info_routes = Blueprint('dementia_info_routes', __name__)
is_connected_routes = Blueprint('is_connected_routes', __name__)
location_info_routes = Blueprint('location_info_routes', __name__)
send_location_info_routes = Blueprint('send_live_location_info_routes', __name__)
user_login_routes = Blueprint('user_login_routes', __name__)

@nok_info_routes.route('/receive-nok-info', methods=['POST'])
def receive_nok_info():
    try:
        nok_data = request.json
        _keyfromdementia = nok_data.get('keyfromdementia')
        rng = RandomNumberGenerator()
        print(_keyfromdementia)

        # 인증번호 중복 여부 확인
        existing_dementia = dementia_info.query.filter_by(dementia_key=_keyfromdementia).first()
        print(existing_dementia)
        if existing_dementia:
            # 이미 등록된 인증번호에 해당하는 환자 정보가 있을 경우, 해당 환자의 key 값을 가져옴
            for _ in range(10):
                unique_random_number = rng.generate_unique_random_number(100000, 999999)
            
            _key = str(unique_random_number)  # 키 값을 문자열로 변환

            new_user = nok_info(nok_key=_key, nok_name=nok_data.get('name'), nok_phonenumber=nok_data.get('phone_number'), dementia_info_key = _keyfromdementia)
            db.session.add(new_user)
            db.session.commit()

            response_data = {'status': 'success', 'message': 'Next of kin data received successfully', 'nok_key' : _key}
        else:
            # 인증번호가 등록되지 않은 경우, 오류 전송
            response_data = {'status': 'error', 'message': 'Certification number not found'}

        
        return jsonify(response_data)

    except Exception as e:
        response_data = {'status': 'error', 'message': str(e)}
        return jsonify(response_data), 500
    
@dementia_info_routes.route('/receive-dementia-info', methods=['POST'])
def receive_dementia_info():
    try:
        dementia_data = request.json

        rng = RandomNumberGenerator()

        for _ in range(10):
            unique_random_numberfordementia = rng.generate_unique_random_number(100000, 999999)
    
        _dementia_key = str(unique_random_numberfordementia)  # 키 값을 문자열로 변환
    
        _dementia_name = dementia_data.get('name')
        _dementia_phonenumber = dementia_data.get('phone_number')

        new_user = dementia_info(dementia_key=_dementia_key, dementia_name = _dementia_name, dementia_phonenumber=_dementia_phonenumber)
        db.session.add(new_user)
        db.session.commit()
        
        response_data = {'status': 'success', 'message': 'Dementia paitient data received successfully', 'dementia_key': _dementia_key}
        return jsonify(response_data)
    
    except Exception as e:
        response_data = {'status': 'error', 'message': str(e)}
        return jsonify(response_data), 500

@is_connected_routes.route('/is-connected', methods=['POST'])
def is_connected():
    try:
        connection_request = request.json
        _dementia_key = connection_request.get('dementia_key')

        existing_dementia = nok_info.query.filter_by(dementia_info_key=_dementia_key).first()
        if existing_dementia:
            response_data = {'status': 'success', 'message': 'Connected successfully'}
        else:
            response_data = {'status': 'error', 'message': 'Connection failed'}

        return jsonify(response_data)
    
    except Exception as e:
        response_data = {'status': 'error', 'message': str(e)}
        return jsonify(response_data), 500
    
@user_login_routes.route('/receive-user-login', methods=['POST'])
def receive_user_login():
    response_data = {}
    try:
        data = request.json
        
        _key = data.get('key')
        _isdementia = data.get('isdementia') # 0: NOK, 1: dementia

        if _isdementia == 0: # nok일 경우
            existing_nok = nok_info.query.filter_by(nok_key=_key).first()
            if existing_nok:
                response_data = {'status': 'success', 'message': 'Login success'}
            else:
                response_data = {'status': 'error', 'message': 'Login failed'}
        elif _isdementia == 1: # dementia일 경우
            existing_dementia = dementia_info.query.filter_by(dementia_key=_key).first()
            if existing_dementia:
                response_data = {'status': 'success', 'message': 'Login success'}
            else:
                response_data = {'status': 'error', 'message': 'Login failed'}

        return jsonify(response_data)
    
    except Exception as e:
        response_data = {'status': 'error', 'message': str(e)}
        return jsonify(response_data), 500
    
@location_info_routes.route('/receive-location-info', methods=['POST'])
def receive_location_info():
    try:
        data = request.json
        json_data = json.dumps(data)
        
        _dementia_key = data.get('dementia_key')
        
        existing_dementia = dementia_info.query.filter_by(dementia_key=_dementia_key).first()

        if existing_dementia:
            # UpdateUserStatus 클래스의 인스턴스 생성
            user_status_updater = UpdateUserStatus()

            # 예측 수행
            prediction = user_status_updater.predict(json_data)

            new_location = location_info(
                dementia_key=data.get('dementia_key'),
                date=data.get('date'),
                time=data.get('time'),
                latitude=data.get('latitude'),
                longitude=data.get('longitude'),
                user_status=int(prediction[0]),  # 예측 결과로 업데이트
                accelerationsensor_x=data.get('accelerationsensor_x'),
                accelerationsensor_y=data.get('accelerationsensor_y'),
                accelerationsensor_z=data.get('accelerationsensor_z'),
                gyrosensor_x=data.get('gyrosensor_x'),
                gyrosensor_y=data.get('gyrosensor_y'),
                gyrosensor_z=data.get('gyrosensor_z'),
                directionsensor_x=data.get('directionsensor_x'),
                directionsensor_y=data.get('directionsensor_y'),
                directionsensor_z=data.get('directionsensor_z'),
                lightsensor=data.get('lightsensor'),
                battery=data.get('battery'),
                isInternetOn=data.get('isInternetOn'),
                isGpsOn=data.get('isGpsOn'),
                isRingstoneOn=data.get('isRingstoneOn')
            )

            print(int(prediction[0]))

            db.session.add(new_location)
            db.session.commit()
            response_data = {'status': 'success', 'message': 'Location data received successfully'}
        else:
            response_data = {'status': 'error', 'message': 'Dementia data not found'}
            
        return jsonify(response_data)
    
    except Exception as e:
        print(e)
        response_data = {'status': 'error', 'message': str(e)}
        return jsonify(response_data), 500

@send_location_info_routes.route('/send-live-location-info', methods=['GET'])
def send_location_info():
    try:
        data = request.json
        
        dementia_key = data.get('dementia_key')
        
        latest_location = location_info.query.filter_by(dementia_key=dementia_key).order_by(location_info.date.desc()).first()
        
        if latest_location:
            response_data = {
                'status': 'success',
                'message': 'Location data sent successfully',
                'latitude': latest_location.latitude,
                'longitude': latest_location.longitude,
                'user_status': latest_location.user_status, # 1: 정지, 2: 도보, 3: 차량, 4: 지하철
                'accelerationsensor_x': latest_location.accelerationsensor_x,
                'accelerationsensor_y': latest_location.accelerationsensor_y,
                'accelerationsensor_z': latest_location.accelerationsensor_z,
                'gyrosensor_x': latest_location.gyrosensor_x,
                'gyrosensor_y': latest_location.gyrosensor_y,
                'gyrosensor_z': latest_location.gyrosensor_z,
                'directionsensor_x': latest_location.directionsensor_x,
                'directionsensor_y': latest_location.directionsensor_y,
                'directionsensor_z': latest_location.directionsensor_z,
                'lightsensor': latest_location.lightsensor,
                'battery': latest_location.battery,
                'isInternetOn': latest_location.isInternetOn,
                'isGpsOn': latest_location.isGpsOn,
                'isRingstoneOn': latest_location.isRingstoneOn
            }
        else:
            response_data = {'status': 'error', 'message': 'Location data not found'}
        
        return jsonify(response_data)
    
    except Exception as e:
        response_data = {'status': 'error', 'message': str(e)}
        return jsonify(response_data), 500