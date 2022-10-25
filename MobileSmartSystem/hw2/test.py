import numpy as numpy

dict = {1: [10.123123123, 20, 30], 0: [10], 2: [30, 40, 10]}

for key, value in sorted(dict.items()):
    value_str = str(numpy.float_(value))[1:-1]
    print(f'{key}: {value_str}')
