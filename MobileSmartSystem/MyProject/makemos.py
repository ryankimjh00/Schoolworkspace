import re


def get_morse_code_dict():
    morse_code = {
        "A": ".-", "N": "-.", "B": "-...", "O": "---", "C": "-.-.", "P": ".--.", "D": "-..", "Q": "--.-", "E": ".",
        "R": ".-.", "F": "..-.", "S": "...", "G": "--.", "T": "-", "H": "....", "U": "..-", "I": "..", "V": "...-",
        "K": "-.-", "X": "-..-", "J": ".---", "W": ".--", "L": ".-..", "Y": "-.--", "M": "--", "Z": "--..", " ": " "
    }
    return morse_code


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


def get_cleaned_english_sentence(raw_english_sentence):
    var = re.sub('[.,!?]', '', raw_english_sentence)
    var = var.upper()

    return var


def encoding_character(english_character):
    morse_code_dict = get_morse_code_dict()

    for keys, values in morse_code_dict.items():  # mydict에 아이템을 하나씩 접근해서, key, value를 각각 name, age에 저장
        if keys == english_character:
            return values


# 영어문장으로 입력된 문자열을 모스코드로
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


def makeMos(message):
    while True:
        if message == '0':  # 0 입력되면 종료
            break
        elif is_validated_english_sentence(message):
            result = encoding_sentence(message)
            return result
