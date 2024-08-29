import json
import pandas as pd
import pickle
from .bodymodel import ReceiveLocationRequest

class UpdateUserStatus:
    def __init__(self):

        model_filename = 'app/random_forest_model_mk4.pkl'
        with open(model_filename, 'rb') as model_file:
            self.model = pickle.load(model_file)

    def predict(self, accel, gyro, direction):
        # 전처리 함수 호출
        preprocessed_data = self.preprocessing(accel, gyro, direction)
        
        print(preprocessed_data)
        # 모델 예측
        prediction = self.model.predict(preprocessed_data)
        
        print(prediction)
        return prediction

    def preprocessing(self, accel, gyro, direction):

        processed_data = [{
            'accel.x': accel[0],
            'accel.y': accel[1],
            'accel.z': accel[2],
            'gyro.x': gyro[0],
            'gyro.y': gyro[1],
            'gyro.z': gyro[2],
            'mag.x': direction[0],
            'mag.y': direction[1],
            'mag.z': direction[2]
        }]

        df = pd.DataFrame(processed_data)

        return df
