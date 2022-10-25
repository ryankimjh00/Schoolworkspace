import random
import numpy as numpy


def inputData(size):
    data = []
    for i in range(size):
        data.append([int(random.random() * 5), random.random() * size])
    return data


def printArr(data):
    for i in range(len(data)):
        print("%2d %f" % (data[i][0], data[i][1]))


def printDict(dict):
    d = sorted(dict.items())
    numpy.set_printoptions(precision=6)  # numpy.array의 소수점을 제한한다.
    for key, value in d:
        value_str = str(numpy.array(value))[1:-1]
        print(f'{key}: {value_str}')


if __name__ == '__main__':
    dict = {1: [10, 20, 30], 0: [10], 2: [30, 40, 10]}
    printDict(dict)
