#include <stdio.h>;
#include <string.h>
#include <stdlib.h>

int main() {
	char input_str[100] = { "\0" };
	char result_str[100] = { "\0" };
	int num;
	int length, i;
	int str_to_int;
	printf("����� ���ڿ��� �Է��Ͻÿ�: ");
	gets(input_str);
	length = strlen(input_str);
	while (1) {
		printf("-------------���ڿ� ó�� ���α׷�------------\n");
		printf("1. �ҹ��� to �빮��                       \n");
		printf("2. �빮�� to �ҹ���                        \n");
		printf("3. ���� to ���� �� 300�� ���ϱ�            \n");
		printf("4. ���ڸ� �Ųٷ� ���                      \n");
		printf("0. ���α׷� ����                           \n");
		printf("--------------------------------------------\n");
		printf("���Ͻô� ó���� ���ڷ� �Է� ���ּ���: ");
		scanf_s("%d", &num);
		fflush(stdin);
		switch (num)
		{
			// �빮�� 65���� 90����, �ҹ��� 97���� 122����
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