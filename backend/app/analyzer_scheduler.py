from .models import db, location_info, meaningful_location_info
from .LocationAnalyzer import LocationAnalyzer
import datetime

class AnalyzerScheduler:
    def __init__(self, today, location_list):

        self.LL = self.makeing_file(location_list, today)
    
    def makeing_file(self, location_list, today):
        try:
            errfile = f'error_{today}.txt'
            if location_list:
                dementia_keys = set([location.dementia_key for location in location_list])
                for key in dementia_keys:
                    key_location_list = [location for location in location_list if location.dementia_key == key]

                    if len(key_location_list) <= 100:
                        with open(errfile, 'a') as file:
                            file.write(f'{key} dementia location data not enough\n')
                    else:
                        filename = f'location_data_for_dementia_key_{key}_{today}.txt'
                        with open(filename, 'w') as file:
                            for location in key_location_list:
                                file.write(f'{location.latitude},{location.longitude},{location.date},{location.time}\n')
            else:
                print("location_list가 비어 있습니다.")

            print('[system] {} dementia meaningful location data analysis finished'.format(today))
        
        except Exception as e:
            return e
    
    def analyze_meaningful_location(self, filename, key):
        LA = LocationAnalyzer(filename)
        predict_meaningful_location_data = LA.gmeansFunc()

        meaningful_location_record = []

        for i in range(len(predict_meaningful_location_data) - 1):
            new_meaningful_location = meaningful_location_info(
                dementia_key=key,
                latitude=predict_meaningful_location_data[i][0],
                longitude=predict_meaningful_location_data[i][1]
                )
            meaningful_location_record.append(new_meaningful_location)
        
        return meaningful_location_record
        
if __name__ == "__main__":
    AS = AnalyzerScheduler()
    AS.analyze_meaningful_location()
