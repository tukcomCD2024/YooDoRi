import pandas as pd
import sys
from sklearn.tree import DecisionTreeClassifier
from io import StringIO
import pickle


# 저장된 예측 모델 불러오기
# 랜덤 포레스트 모델 사용
model_filename = 'random_forest_model.pkl'
with open(model_filename, 'rb') as model_file:
    model = pickle.load(model_file)

# 예측 함수
def predict(data):
    # 전처리한 데이터를 입력으로 사용

    prediction = model.predict(data)
    return prediction

# 전처리 함수
def preprocessing(data):
    # 클라이언트에서 넘어온 데이터를 입력으로 사용
    # ,로 구분된 한 줄 짜리 데이터
    data = pd.read_csv(StringIO(data), header=None, names=['시간', 'x1', 'y1', 'z1', 'x2', 'y2', 'z2', 'x3', 'y3', 'z3'])
    data = data.drop(['시간'], axis=1)

    return data

if __name__ == '__main__':
    # 파일 경로 가져오기
    # 지금은 데이터가 저장된 파일의 경로를 실행할 때 입력
    # 추후 수정 가능
    file_path = sys.argv[1]

    f = open(file_path, 'r')
    data = f.read()
    f.close()
    
    prepro_data = preprocessing(data)
    res = predict(prepro_data)
    
    print(res)
