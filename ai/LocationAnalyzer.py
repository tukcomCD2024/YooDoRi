import numpy as np
import pandas as pd
import os
import sys
from pyclustering.cluster.gmeans import gmeans

# 폴더 내의 모든 파일 읽음
# 데이터 예시 (39.984702,116.318417,0,492,39744.1201851852,2008-10-23,02:53:04)
# (위도, 경도, 0, 고도, 1899년 이후 경과한 시간, 날짜, 시간)
# 하나의 파일로 수정 필요 *
def fileReader(folderPath, filename):

    latitude = []   # 위도
    longitude = []  # 경도
    date = []       # 날짜
    time = []       # 시간

    filePath = os.path.join(folderPath, filename)
            
    # 텍스트 파일을 DataFrame에 추가

    with open(filePath, 'r') as file: 
        data = file.read()

    # 데이터에 불필요한 부분 제거
    # 추후 데이터 형식에 따라 수정 필요 *
    data = data.split('\n')[6:-1]
    for i in range(len(data)):
        line = data[i].split(',')
        latitude.append(line[0])    # 위도
        longitude.append(line[1])   # 경도
        date.append(line[5])        # 날짜
        time.append(line[6])        # 시간

    df = pd.DataFrame({"latitude":latitude, "longitude":longitude, "date":date, "time":time})

    return df

# 의미장소 추출
def gmeansFit(df):
    # 두 열을 선택하고 넘파이 배열로 변환
    selectedColumns = ['latitude', 'longitude']
    resultList = df[selectedColumns].values.tolist()    # 리스트로 변환
    
    gmeansInstance = gmeans(resultList).process()       # 클러스터링

    centers = gmeansInstance.get_centers()              # 클러스터의 중심 (의미장소)
    clusters = gmeansInstance.get_clusters()            # 분류된 클러스터


    return clusters, centers
    
# 호출 함수
def gmeansFunc(folder_path):
    
    j = 0
    data_dict = {}
    # 폴더 내의 모든 텍스트 파일을 읽음
    for filename in os.listdir(folder_path):

        df = fileReader(folder_path, filename)

        # 위도 경도 데이터 형식 변경
        df['latitude'] = df['latitude'].astype(float)
        df['longitude'] = df['longitude'].astype(float)
        # 날짜, 시간 데이터 병합
        df['datetime'] = pd.to_datetime(df['date'] + ' ' + df['time'], format='%Y-%m-%d %H:%M:%S')
        df['datetime'] = df['datetime'].dt.floor('T')
        df = df.drop(['date', 'time'], axis=1)
        # 1분 단위로 데이터 병합
        df = df.drop_duplicates(['datetime'], ignore_index=True)

        # 의미장소 추출
        clusters, centers = gmeansFit(df)
        
        # 딕셔너리 형식으로 데이터 저장
        data_dict[j] = pd.DataFrame({"clusters":clusters, "centers":centers})
        
        # 클러스터가 10개 미만인 의미장소 제거
        for k in range(len(data_dict[j].clusters)):
            if (len(data_dict[j].clusters[k]) < 10):
                data_dict[j].drop(index=k, inplace=True)
        data_dict[j] = data_dict[j].sort_index(axis=1)
        
        j += 1
    return data_dict

if __name__ == '__main__':
    # 파일 경로 가져오기
    # 지금은 데이터가 저장된 파일의 경로를 실행할 때 입력
    # 추후 수정 가능
    file_path = sys.argv[1]

    f = open(file_path, 'r')
    data = f.read()
    f.close()

    dataDict = gmeansFunc(data)
    
    print(dataDict)
