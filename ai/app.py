import numpy as np
import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report
from sklearn.manifold import TSNE
import matplotlib.pyplot as plt
from flask import Flask

app = Flask(__name__)
# Fixme 데이터 저장 위치 변경 필요
# 데이터 불러오기
# 체류 상태
f = open("C:\\Users\\sk002\\OneDrive\\바탕 화면\\학교\\Yoodori\\SensorData1.txt")

data1 = f.read()

# 도보 상태
f = open("C:\\Users\\sk002\\OneDrive\\바탕 화면\\학교\\Yoodori\\SensorData2.txt")

data2 = f.read()

# 차량 상태
f = open("C:\\Users\\sk002\\OneDrive\\바탕 화면\\학교\\Yoodori\\SensorData3.txt")

data3 = f.read()

# 지하철 이용 상태
f = open("C:\\Users\\sk002\\OneDrive\\바탕 화면\\학교\\Yoodori\\SensorData4.txt")

data4 = f.read()

f.close()

# 데이터 전처리
data1 = data1.split('\n')
data1 = data1[:-1]

data2 = data2.split('\n')
data2 = data2[:-1]

data3 = data3.split('\n')
data3 = data3[:-1]

data4 = data4.split('\n')
data4 = data4[:-1]

# 데이터를 ','로 분리하고 DataFrame으로 변환
# 1 : 가속도 | 2 : 자이로 | 3 : 방향
df1 = pd.DataFrame([row.split(',') for row in data1],
                  columns=['시간', 'x1', 'y1', 'z1', 'x2', 'y2', 'z2', 'x3', 'y3', 'z3'])
df1['label'] = 1

df2 = pd.DataFrame([row.split(',') for row in data2],
                  columns=['시간', 'x1', 'y1', 'z1', 'x2', 'y2', 'z2', 'x3', 'y3', 'z3'])
df2['label'] = 2

df3 = pd.DataFrame([row.split(',') for row in data3],
                  columns=['시간', 'x1', 'y1', 'z1', 'x2', 'y2', 'z2', 'x3', 'y3', 'z3'])
df3['label'] = 3

df4 = pd.DataFrame([row.split(',') for row in data4],
                  columns=['시간', 'x1', 'y1', 'z1', 'x2', 'y2', 'z2', 'x3', 'y3', 'z3'])
df4['label'] = 4

# 데이터가 1초에 여러번 저장되는 에러 방지
# 추후 제거
df1 = df1.drop_duplicates(subset = '시간', keep = 'first')
df2 = df2.drop_duplicates(subset = '시간', keep = 'first')
df3 = df3.drop_duplicates(subset = '시간', keep = 'first')
df4 = df4.drop_duplicates(subset = '시간', keep = 'first')

# 각 df 병합
df_combined = pd.concat([df1, df2, df3, df4], ignore_index=True)

# 시간 열 제거
df_combined = df_combined.drop(['시간'], axis=1)

# 중복 데이터 제거
df_combined = df_combined.drop_duplicates()

# Data split
X = df_combined.drop(['label'], axis=1)  # 'label' 컬럼 제외한 나머지를 특성으로 사용
y = df_combined['label']  # 'label' 컬럼을 타겟으로 사용

# train : valid : test = 0.72 : 0.08 : 0.2
X, X_test, y, y_test = train_test_split(X, y, test_size=0.2, random_state=0)
X_train, X_valid, y_train, y_valid = train_test_split(X, y, test_size=0.1, random_state=0)

# DecisionTreeClassifier 모델 생성 및 학습
model = DecisionTreeClassifier(random_state=42)
model.fit(X_train, y_train)

# 예측 함수
def predict(data):
    # 클라이언트에서 받은 데이터를 입력으로 사용

    prediction = model.predict(data)
    return prediction

if __name__ == '__main__':
    app.run()