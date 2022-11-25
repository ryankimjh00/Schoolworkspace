import re

def get_morse_code_dict():
    morse_code = {
        "A": ".-", "N": "-.", "B": "-...", "O": "---", "C": "-.-.", "P": ".--.", "D": "-..", "Q": "--.-", "E": ".",
        "R": ".-.", "F": "..-.", "S": "...", "G": "--.", "T": "-", "H": "....", "U": "..-", "I": "..", "V": "...-",
        "K": "-.-", "X": "-..-", "J": ".---", "W": ".--", "L": ".-..", "Y": "-.--", "M": "--", "Z": "--..", " ": " "
    }
    return morse_code


# 영어 문장에 특수부호가 들어있는지를 확인해주는 함수. 숫자나 특수부호가 들어가면 오류를 준다.
def is_validated_english_sentence(user_input):
    var = list(user_input)
    for s in range(len(var)):
        number = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
        special = ['_', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '=', '[', ']', '{', '}', '"', "'", ';',
                   ':', '|', '`', '~']
        if var[s] in number:  # 숫자 확인
            return False
        elif var[s] in special:  # 특수부호 확인
            return False
        elif user_input == ' ':  # 띄어쓰기 확인
            return False
    return True


def get_cleaned_english_sentence(raw_english_sentence):
    var = re.sub('[.,!?]', '', raw_english_sentence)  # 특수부호를 제거해준다.
    var = var.upper() # 전부 대문자로 변경해준다

    return var


def encoding_character(english_character):
    morse_code_dict = get_morse_code_dict()
    for keys, values in morse_code_dict.items():  # morse_code_dict에 아이템을 하나씩 접근해서, key, value를 각각 저장
        if keys == english_character:
            return values


# 영어문장으로 입력된 문자열을 모스코드로 변환
def encoding_sentence(english_sentence):
    morse = get_cleaned_english_sentence(english_sentence)
    morse = list(morse)  # 리스트로 변환

    result = ''

    for i in range(len(morse)):
        if morse[i] == " ":
            result += ' '
        else:
            result += str(encoding_character(morse[i]))  # 변환된 모스부호 리스트를 문자열로 저장

    return result


def makeMos(message):
    while True:
        if message == '0':  # 0 입력되면 종료
            break
        elif is_validated_english_sentence(message):
            result = encoding_sentence(message)
            return result
