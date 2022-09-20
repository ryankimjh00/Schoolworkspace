#include <stdio.h>

int main() {
	int A[2][2] = { {5,7},{3,2} };
	int B[2][1] = { {2},{4} };
	int result[2][1];
	int sum = 0;

	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 1; j++) {
			sum = 0;
			for (int k = 0; k < 2; k++) {
				sum += A[i][k] + B[k][j];
			}
			result[i][j] = sum;
		}
	}
}

// A배열 1,1 x B 배열 1,1 + A 1,2 x B 2,1
