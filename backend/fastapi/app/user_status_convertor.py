def convertor(data):
    
    status = {
        "정지": 1,
        "도보": 2,
        "차량": 3,
        "지하철": 4
    }.get(data, None)

    return status
    
def convertor2(data):
    status = {
        1 : "정지",
        2 : "도보",
        3 : "차량",
        4 : "지하철"
    }.get(data, None)

    return status