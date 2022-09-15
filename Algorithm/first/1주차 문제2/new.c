#include <stdio.h>;
#include <string.h>
#include <stdlib.h>

int main() {
	char input_str[100] = { "\0" };
	char result_str[100] = { "\0" };
	int num;
	int length, i;
	int str_to_int;
	printf("사용할 문자열을 입력하시오: ");
	gets(input_str);
	length = strlen(input_str);
	while (1) {
		printf("-------------문자열 처리 프로그램------------\n");
		printf("1. 소문자 to 대문자                       \n");
		printf("2. 대문자 to 소문자                        \n");
		printf("3. 문자 to 숫자 후 300과 더하기            \n");
		printf("4. 문자를 거꾸로 출력                      \n");
		printf("0. 프로그램 종료                           \n");
		printf("--------------------------------------------\n");
		printf("원하시는 처리를 숫자로 입력 해주세요: ");
		scanf_s("%d", &num);
		fflush(stdin);
		switch (num)
		{
			// 대문자 65부터 90까지, 소문자 97부터 122까지
		case 1:
			for (int i = 0; i < length; i++) {
				printf("%c ", ((int)input_str[i] + 32));
			}
			printf("\n");
			break;
		case 2:
			for (int i = 0; i < length; i++) {
				printf("%c ", ((int)input_str[i] - 32));
			}
			printf("\n");
			break;
		case 3:
			str_to_int = atoi(input_str);
			printf("%d + 300 = %d ", str_to_int, str_to_int + 300);
			break;
		}
	}

}