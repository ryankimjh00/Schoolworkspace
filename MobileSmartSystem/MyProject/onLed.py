import test


def splitMos():
    mos = test.makeMos()
    mos_list = list(mos)
    return mos_list


def MosTest():
    mos = splitMos()
    mos_list = []
    for i in range(len(mos)):
        if mos[i] == '.':
            # print("short  ")
            mos_list.append("깜빡")

        elif mos[i] == '-':
            # print("long  ")
            mos_list.append("깜---빡")
        else:
            # print("term  ")
            mos_list.append("******")
    print(mos)
    print(mos_list)

if __name__ == "__main__":
    MosTest()
