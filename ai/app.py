import numpy as np
import pandas as pd
import sys
from sklearn.tree import DecisionTreeClassifier
from io import StringIO
import pickle
#from flask import Flask

# app = Flask(__name__)

# Fixme 데이터 저장 위치 변경 필요
# 데이터 불러오기
"""
url = 'C:\\Users\\sk002\\OneDrive\\바탕 화면\\학교\\Yoodori\\data.csv'
df = pd.read_csv(url)

# Data split
X = df.drop(['label'], axis=1)  # 'label' 컬럼 제외한 나머지를 특성으로 사용
y = df['label']  # 'label' 컬럼을 타겟으로 사용

# train : valid : test = 0.8 : 0.2
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)

# DecisionTreeClassifier 모델 생성 및 학습
model = DecisionTreeClassifier(random_state=0)
model.fit(X_train, y_train)
"""

# 저장된 모델 불러오기
model_filename = 'decision_tree_model.pkl'
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
