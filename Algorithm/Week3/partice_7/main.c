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
	int r; // �̰� ������
}Circle;

void shapePrint(Line* lptr, int cur_line, Circle* cptr, int cur_cir);

int main(void) {
	Line lines[10];
	Circle circles[10];
	int input, cur_line = 0, cur_cir = 0;
	while (1) {
		printf("-----------------------------------\n");
		printf("1. ���� �Է�\n");
		printf("2. �� �Է�\n");
		printf("3. ����� ���� ���\n");
		printf("4. ����\n");
		printf("-----------------------------------\n");
		printf("���ϴ� ��ȣ�� �Է��Ͻÿ�[����� ����(����:%d, ��: %d)]\n", cur_line, cur_cir);
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
					printf("���ϴ� ��ȣ�� �����ϴ�\n");
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
