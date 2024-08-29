def convertor(data):
    
    status = {
        "정지": 0,
        "도보": 1,
        "차량": 2,
        "지하철": 3
    }.get(data, None)

    return status
    
def convertor2(data):
    status = {
        0 : "정지",
        1 : "도보",
        2 : "차량",
        3 : "지하철"
    }.get(data, None)

    return status