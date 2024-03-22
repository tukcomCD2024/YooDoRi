from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from .config import Config
from flask import jsonify
from apscheduler.schedulers.background import BackgroundScheduler
import os

db = SQLAlchemy()
app = Flask(__name__)
scheduler = BackgroundScheduler(timezone="Asia/Seoul")


def create_app():
    app.config.from_object(Config)
    CORS(app)

    '''if not app.debug:
        import logging
        from logging.handlers import RotatingFileHandler
        file_handler = RotatingFileHandler('website.log', maxBytes=10240, backupCount=10)
        file_handler.setLevel(logging.DEBUG)
        app.logger.addHandler(file_handler)'''

    # 다른 설정 및 확장 추가 가능 
    db.init_app(app)

    from .routes import nok_info_routes, dementia_info_routes, is_connected_routes, location_info_routes, send_location_info_routes, user_login_routes, user_info_modification_routes, caculate_dementia_avarage_walking_speed_routes, get_user_info_routes, analyze_schedule
    #from . import error_handler
    #from .analyzer_scheduler import AnalyzerScheduler

    app.register_blueprint(nok_info_routes)
    app.register_blueprint(dementia_info_routes)
    app.register_blueprint(is_connected_routes)
    app.register_blueprint(location_info_routes)
    app.register_blueprint(send_location_info_routes)
    app.register_blueprint(user_login_routes)
    app.register_blueprint(user_info_modification_routes)
    app.register_blueprint(caculate_dementia_avarage_walking_speed_routes)
    app.register_blueprint(get_user_info_routes)

    '''app.register_error_handler(error_handler.CustomError, error_handler.success)
    app.register_error_handler(error_handler.CustomError, error_handler.wrong_request)
    app.register_error_handler(error_handler.CustomError, error_handler.key_not_found)
    app.register_error_handler(error_handler.CustomError, error_handler.loc_data_not_found)
    app.register_error_handler(error_handler.CustomError, error_handler.loc_data_not_enough)
    app.register_error_handler(error_handler.CustomError, error_handler.login_success)
    app.register_error_handler(error_handler.CustomError, error_handler.login_failed)
    app.register_error_handler(error_handler.CustomError, error_handler.undef_err)'''

    app.register_blueprint(analyze_schedule)

    #AS = AnalyzerScheduler()
    #scheduler.add_job(analyze_meaningful_location, 'cron', hour = '14', minute = '34')
    #scheduler.start()
    

    

    return app

def create_db():
    with db.app.app_context():
        if not os.path.exists('website/' + db.engine.url.database):
            db.create_all()
