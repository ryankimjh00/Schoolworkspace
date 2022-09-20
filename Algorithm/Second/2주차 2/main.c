#include <stdio.h>
#include <math.h>

typedef struct point {
	int xpos;
	int ypos;
}Point;

typedef struct rectangle {
	Point leftup;
	Point rightdown;
}Rectangle;

void main() {
	Rectangle r1;
	printf("좌상단 좌표 입력: ");
	scanf_s("%d %d", &r1.leftup.xpos, &r1.leftup.ypos);
	printf("우하단 자표 입력: ");
	scanf_s("%d %d",&r1.rightdown.xpos, &r1.rightdown.ypos);
	Point leftdown;
	leftdown.xpos = r1.leftup.xpos;
	leftdown.ypos = r1.rightdown.ypos;
	Point rightup;
	rightup.xpos = r1.rightdown.xpos;
	rightup.ypos = r1.leftup.ypos;

	int cross = abs(r1.leftup.xpos - rightup.xpos);
	int vertical = abs(rightup.ypos - r1.rightdown.ypos);

	int m = cross * vertical;
	printf("넓이는: %d", m);

}