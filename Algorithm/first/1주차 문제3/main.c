#include <stdio.h>
#include <time.h>

int main() {
	int q[3] = { 0 };
	for (int i= 0; i < 3; i++) {
		q[i] = rand() % 10;
		for (int j = 0; j < i; j++) {
			if (q[i] == q[j]) {
				i--;
			}
		}
	}

	printf("%d %d %d\n", q[0], q[1], q[2]);
	int a[3] = { 0 };
	int strike = 0;
	int ball = 0;
	int out = 0;
	int sbo[3] = { 0 };
	for (int i = 6; i >= 0; i--) {
		scanf_s("%d %d %d", &a[0], &a[1], &a[2]);
		// ���� ���ڸ� �Է��� ��� .. �ٽ� �ѹ� �� �Է��� �� �ֵ���
		if (a[0] == a[1] || a[1] == a[2] || a[0] == a[2]) {
			printf("���� ���ڸ� �Է��߽��ϴ�.\n");
			i = i + 1;
		}
		// 0~9 ������ ���ڸ� ������ �ؾ��ϹǷ� �Ѿ�� �ٽ� �Է�
		for (int i = 0; i < 3; i++) {
			if (a[i] < 0 || a[i]>9) {
				i = i + 1;
				printf("9 �̻��� ���� �Է�\n");
			}
			continue;
		}
		// for 0~3 ��Ʈ����ũ, ��
		for (int j = 0; j < 3; j++) {
			if (a[j] == q[j]) {
				strike++;
				sbo[0] = strike;
			}
			else {
				for (int z = 0; z < 3; z++) {
					if (j == z) {
						continue;
					}
					else if (q[j] == a[z]) {
						ball++;
						sbo[1] = ball;
					}
				}
			}

		}
		sbo[2] = 3 - (sbo[0] + sbo[1]);
	}
}
