from .models import db, location_info, meaningful_location_info
from .LocationAnalyzer import LocationAnalyzer
import datetime

class AnalyzerScheduler:
    def __init__(self):
        pass
    
    def analyze_meaningful_location(self):
        try:
            today = datetime.datetime.now()
            test = today - datetime.timedelta(days=2)
            test = test.strftime('%Y-%m-%d')
            print('[system] {} dementia meaningful location data analysis started'.format(test))

            location_list = location_info.query.filter(location_info.date == test).order_by(location_info.dementia_key.desc(), location_info.time.desc()).limit(10).all()

            print('[system] {} dementia location data loaded successfully'.format(test))
            errfile = f'error_{test}.txt'
            if location_list:
                dementia_keys = set([location.dementia_key for location in location_list])
                for key in dementia_keys:
                    key_location_list = [location for location in location_list if location.dementia_key == key]

                    if len(key_location_list) <= 100:
                        with open(errfile, 'a') as file:
                            file.write(f'{key} dementia location data not enough\n')
                        continue

                    filename = f'location_data_for_dementia_key_{key}_{today}.txt'
                    with open(filename, 'w') as file:
                        for location in key_location_list:
                            file.write(f'{location.latitude},{location.longitude},{location.date},{location.time}\n')

                    LA = LocationAnalyzer(filename)
                    predict_meaningful_location_data = LA.gmeansFunc()

                    new_meaningful_location = meaningful_location_info(
                        dementia_key=key,
                        latitude=predict_meaningful_location_data[0],
                        longitude=predict_meaningful_location_data[1]
                    )
                    db.session.add(new_meaningful_location)
                
                # 여기서 한꺼번에 DB에 커밋
                db.session.commit()

                for key in dementia_keys:
                    print(f'[system] {key} dementia meaningful location data saved successfully')

            else:
                print("location_list가 비어 있습니다.")

            print('[system] {} dementia meaningful location data analysis finished'.format(test))
        
        except Exception as e:
            return e
        
if __name__ == "__main__":
    AS = AnalyzerScheduler()
    AS.analyze_meaningful_location()
