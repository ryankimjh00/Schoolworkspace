def arr2dict(data):
    dict = {}
    for elements in data:
        try:
            dict[elements[0]].append(elements[1])
        except KeyError:
            dict[elements[0]] = [elements[1]]
    return dict


if __name__ == '__main__':
    # 이것은 모듈 테스트용 입니다.
    data = [[0, 10], [2, 11], [1, 30], [0, 50], [1, 30]]
    dict = arr2dict(data)
    print(dict)
