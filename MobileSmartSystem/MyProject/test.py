# 모스코드의 대한 정의
import re


def get_morse_code_dict():
    morse_code = {
        "A": ".-", "N": "-.", "B": "-...", "O": "---", "C": "-.-.", "P": ".--.", "D": "-..", "Q": "--.-", "E": ".",
        "R": ".-.", "F": "..-.", "S": "...", "G": "--.", "T": "-", "H": "....", "U": "..-", "I": "..", "V": "...-",
        "K": "-.-", "X": "-..-", "J": ".---", "W": ".--", "L": ".-..", "Y": "-.--", "M": "--", "Z": "--..", " ": " "
    }
    return morse_code


# 영어 문장 데이터가 잘 맞는지 확인
# 특수문자와 숫자가 들어가 있으면 에러처리 
def is_validated_english_sentence(user_input):
    var = list(user_input)
    for s in range(len(var)):
        number = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
        special = ['_', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '=', '[', ']', '{', '}', '"', "'", ';',
                   ':', '|', '`', '~']
        if var[s] in number:
            return False
        elif var[s] in special:
            return False
        elif user_input == ' ':
            return False
    return True


# 영어 문장내 문장내 문장부호 삭제 그리고 공백 제거 함수
def get_cleaned_english_sentence(raw_english_sentence):
    var = re.sub('[.,!?]', '', raw_english_sentence)
    var = var.upper()

    return var


# 영어문장으로 입력된 값에 대하여 값을 반환
def encoding_character(english_character):
    morse_code_dict = get_morse_code_dict()

    for keys, values in morse_code_dict.items():  # mydict에 아이템을 하나씩 접근해서, key, value를 각각 name, age에 저장
        if keys == english_character:
            return values


# 영어문장으로 입력된 문자열을 모스코드로 변환
def encoding_sentence(english_sentence):
    morse = get_cleaned_english_sentence(english_sentence)
    morse = list(morse)

    result = ''

    for i in range(len(morse)):

        if morse[i] == " ":
            result += ' '
        else:
            result += str(encoding_character(morse[i]))


    return result


# 메인 작동
def makeMos():
    print("Morse Code Program!!")
    # ===Modify codes below=============
    game_on = True
    while game_on:
        user_input = input("Input your message: ")

        if user_input == '0':  # 0 입력되면 종료
            break
        elif is_validated_english_sentence(user_input):  # 정상적인 문장이 입력되었을때
            result = encoding_sentence(user_input)
            return result

