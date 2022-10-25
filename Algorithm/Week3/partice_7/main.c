#include <stdio.h>
typedef struct point {
	int x;
	int y;
}Point;

typedef struct line {
	Point begin;
	Point end;
}Line;

typedef struct circle {
	Point middle;
	int r; // 이거 못했음
}Circle;

void shapePrint(Line* lptr, int cur_line, Circle* cptr, int cur_cir);

int main(void) {
	Line lines[10];
	Circle circles[10];
	int input, cur_line = 0, cur_cir = 0;
	while (1) {
		printf("-----------------------------------\n");
		printf("1. 라인 입력\n");
		printf("2. 원 입력\n");
		printf("3. 저장된 도형 출력\n");
		printf("4. 종료\n");
		printf("-----------------------------------\n");
		printf("원하는 번호를 입력하시오[저장된 도형(라인:%d, 원: %d)]\n", cur_line, cur_cir);
		scanf_s("%d", &input);
		switch (input) {
		case 1:

			break;
		case 2:
			break;
		case 3:
			shapePrint(*lines, *circles);
			break;
		case 4:
			return 0;
			default:
					printf("원하는 번호가 없습니다\n");
		}
		system("cls");
	}
}
void ClearLineFromReadBuffer(void) {
	while(getchar() != '\n');
}
void shapePrint(Line line, Circle circle) {
	Point p1;
	Point p2;
	Line l1;
}
