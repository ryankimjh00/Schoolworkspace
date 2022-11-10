#include <stdio.h>
#include <stdlib.h>
void main() {
	char* menu[4][2] = {
	{"한성라면","1500원"},
	{"한성김밥","1500원"},
	{"돈가스","3500원"},
	{"김치볶음밥","4000원"}
	};
	int input, order_num = 0;
	int orders[100] = { 0 }, totalPrice = 0;
	printf("-----------------메뉴판-----------------\n");
	printf("0. 주문 종료\n");
	printf("1. %s / %s \n", menu[0][0], menu[0][1]);
	printf("2. %s / %s \n", menu[1][0], menu[1][1]);
	printf("3. %s / %s \n", menu[2][0], menu[2][1]);
	printf("4. %s / %s \n", menu[3][0], menu[3][1]);
	printf("----------------------------------------\n");
	while (1) {
		/*input 값을 입력 받는다.
			1~4 사이 값인 경우 orders 배열에 - 1을 하여 삽입(주문)
			order_num 을 하나 증가
			0일 경우 while 문 종료
			그 외 값일 경우 “없는 메뉴라고 출력”*/
		scanf_s("%d", &input);
		if (input > 0 && input < 5) {
			orders[order_num] = input - 1;
			order_num++;
		}
		else if (input == 0) {
			break;
		}
		else
			printf("없는 메뉴");
	}
	FILE* fp = NULL;
	fopen_s(&fp, "receipt.txt", "wt");
	if (fp != NULL) {
		fprintf(fp, "\t****컴공 분식****\n");
		fprintf(fp, "----------------------------------------\n");
		fprintf(fp, "번호\t품명\t\t\t가격\n");
		fprintf(fp, "----------------------------------------\n");
		// fprintf를 활용하여 위 그림과 같이 입력한 내용을 파일로 출력
		for (int i = 0; i < order_num; i++) {
			fprintf(fp, "%d\t%s\t\t%s\n", i, menu[orders[i]][0], menu[orders[i]][1]);
			totalPrice += atoi(menu[orders[i]][1]);
		}
		
		fprintf(fp, "----------------------------------------\n");
		fprintf(fp, "총액\t\t\t\t %d원 \n", totalPrice);
		fclose(fp);
		printf("\n\n주문 내역을 receipt.txt로 출력 하였습니다\n");
		printf("\n주문 내역\n");
	}
	fopen_s(&fp, "receipt.txt", "r");
	char readStr1[80], readStr2[80];
	if (fp != NULL) {
		for (int i = 0; i < 4; i++) {
			fgets(readStr1, 80, fp);
			printf("%s", readStr1);
		}
		int num = 0;
		//fscanf_s를 활용하여 파일 값 입력 받아 printf 로 출력
		for (int i = 0; i < order_num; i++) {
			fscanf_s(fp, "%d %s %s", &num, readStr1, 80, readStr2, 80);
			printf("%d\t%s\t\t%s\n", num, readStr1, readStr2);
		}

			for (int i = 0; i < 3; i++) {
				fgets(readStr1, 80, fp);
				printf("%s", readStr1);
			}
		fclose(fp);
	}
}