#include <stdio.h>
int min(int num[], int array_size);
int max(int num[], int array_size);
void main() {
    int num[] = { 8, 54,11,-45,43,26,66,12,13,65 };
    int array_size = sizeof(num);
    int i;
    printf("배열 값: ", print_array(num));
    printf("\n최대값: %d\n", max(num, array_size));
    printf("최소값: %d\n", min(num, array_size));

}
int max(int num[], int array_size) {
    array_size = sizeof(num);

    for (int i = 1; i < array_size; i++) {
        int m = num[0];
        int max = 0;
        if (num[i] > m)
            num[i] = max;
        else
            m = max;
    }
}
int min(int num[], int array_size) {
    array_size = sizeof(num);
    int n = num[0];
    int min = 0;
    for (int i = 1; i < array_size; i++) {
        if (num[i] < n)
            num[i] = min;
        else
            n = min;
    }
}
int print_array(int num[], int array_size) {
    array_size = sizeof(num);
    for (int i = 0; i < array_size;i++) {
        printf("%d", num[i]);
    }
}