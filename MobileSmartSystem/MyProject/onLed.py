import makemos
import time


def splitMos(message):
    mos = makemos.makeMos(message)
    mos_list = list(mos)
    return mos_list


def MosTest(message):
    mos = splitMos(message)
    mos_list = []
    for i in range(len(mos)):
        if mos[i] == '.':
            print("깜빡  ")
            time.sleep(0.5)
            # mos_list.append("깜빡")

        elif mos[i] == '-':
            print("깜---빡  ")
            time.sleep(1)
            # mos_list.append("깜---빡")
        else:
            print("******  ")
            time.sleep(2)
            # mos_list.append("******")
    print(mos)
    print(mos_list)

if __name__ == "__main__":
    MosTest("hello world")
