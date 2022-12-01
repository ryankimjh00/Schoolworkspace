#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define SWAP(x, y, t) ( (t)=(x), (x)=(y), (y)=(t) )
#define MAX_SIZE 100000L
int sel[MAX_SIZE];
int in[MAX_SIZE];
int bubble[MAX_SIZE];
int shell[MAX_SIZE];
int merge[MAX_SIZE];
int sorted[MAX_SIZE];
int qu[MAX_SIZE];

void selection_sort(int list[], int n) {
	int i, j, least, temp;
	for (i = 0; i < n - 1; i++) {
		least = i;
		for (j = i + 1; j < n; j++)
			if (list[j] < list[least]) least = j;
		SWAP(list[i], list[least], temp);
	}
}

void insertion_sort(int list[], int n) {
	int i, j, key;
	for (i = 1; i < n; i++) {
		key = list[i];
		for (j = i - 1; j >= 0 && list[j] > key; j--)
			list[j + 1] = list[j];
		list[j + 1] = key;
	}
}

void bubble_sort(int list[], int n) {
	int i, j, temp;
	for (i = n - 1; i > 0; i--) {
		for (j = 0; j < i; j++)
			if (list[j] > list[j + 1])
				SWAP(list[j], list[j + 1], temp);
	}
}

void inc_insertion_sort(int list[], int first, int last, int gap) {
	int i, j, key;
	for (i = first + gap; i <= last; i = i + gap) {
		key = list[i];
		for (j = i - gap; j >= first && key < list[j]; j = j - gap)
			list[j + gap] = list[j];
		list[j + gap] = key;
	}
}

void shell_sort(int list[], int n) {
	int i, gap;
	for (gap = n / 2; gap > 0; gap = gap / 2) {
		if ((gap % 2) == 0) gap++;
		for (i = 0; i < gap; i++)
			inc_insertion_sort(list, i, n - 1, gap);
	}
}

void merge_s(int list[], int left, int mid, int right) {
	int i, j, k, l;
	i = left; j = mid + 1; k = left;
	while (i <= mid && j <= right) {
		if (list[i] <= list[j])
			sorted[k++] = list[i++];
		else
			sorted[k++] = list[j++];
	}
	if (i > mid)
		for (l = j; l <= right; l++)
			sorted[k++] = list[l];
	else
		for (l = i; l <= mid; l++)
			sorted[k++] = list[l];
	for (l = left; l <= right; l++)
		list[l] = sorted[l];
}

void merge_sort(int list[], int left, int right)
{
	int mid;
	if (left < right) {
		mid = (left + right) / 2;
		merge_sort(list, left, mid);
		merge_sort(list, mid + 1, right);
		merge_s(list, left, mid, right);
	}
}

int partition(int list[], int left, int right) {
	int pivot, temp;
	int low, high;
	low = left;
	high = right + 1;
	pivot = list[left];
	do {
		do
			low++;
		while (list[low] < pivot);
		do
			high--;
		while (list[high] > pivot);
		if (low < high) SWAP(list[low], list[high], temp);
	} while (low < high);
	SWAP(list[left], list[high], temp);
	return high;
}

void quick_sort(int list[], int left, int right) {
	if (left < right) {
		int q = partition(list, left, right);
		quick_sort(list, left, q - 1);
		quick_sort(list, q + 1, right);
	}
}

int main(void) {
	int i;
	int n = MAX_SIZE;
	double start, end;
	srand(time(NULL));
	for (i = 0; i < n; i++) // 난수 생성 및 출력
		sel[i] = rand() % 100; // 난수 발생 범위 0~99

	memmove(in, sel, sizeof(int) * MAX_SIZE);
	memmove(bubble, sel, sizeof(int) * MAX_SIZE);
	memmove(shell, sel, sizeof(int) * MAX_SIZE);
	memmove(qu, sel, sizeof(int) * MAX_SIZE);

	start = (double)clock() / CLOCKS_PER_SEC;
	selection_sort(sel, n); // 선택정렬 호출
	end = (((double)clock()) / CLOCKS_PER_SEC);
	printf("선택 정렬 수행시간 %lf\n", end - start);

	start = (double)clock() / CLOCKS_PER_SEC;
	insertion_sort(in, n); // 삽입정렬 호출
	end = (((double)clock()) / CLOCKS_PER_SEC);
	printf("삽입 정렬 수행시간 %lf\n", end - start);

	start = (double)clock() / CLOCKS_PER_SEC;
	bubble_sort(bubble, n); // 버블정렬 호출
	end = (((double)clock()) / CLOCKS_PER_SEC);
	printf("버블 정렬 수행시간 %lf\n", end - start);

	start = (double)clock() / CLOCKS_PER_SEC;
	shell_sort(shell, n); // 쉘정렬 호출
	end = (((double)clock()) / CLOCKS_PER_SEC);
	printf("쉘 정렬 수행시간 %lf\n", end - start);

	start = (double)clock() / CLOCKS_PER_SEC;
	merge_sort(merge, 0, n - 1); // 합병정렬 호출
	end = (((double)clock()) / CLOCKS_PER_SEC);
	printf("합병 정렬 수행시간 %lf\n", end - start);

	start = (double)clock() / CLOCKS_PER_SEC;
	quick_sort(qu, 0, n - 1); // 퀵정렬 호출
	end = (((double)clock()) / CLOCKS_PER_SEC);
	printf("퀵 정렬 수행시간 %lf\n", end - start);

	//for (i = 0; i < n; i++)
	//printf("%d ", list[i]);
	//printf("\n");
	return 0;
}