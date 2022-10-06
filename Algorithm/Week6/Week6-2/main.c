#include <stdio.h>
#include <stdlib.h>
#define MAX_QUEUE_SIZE 10

typedef int element;

typedef struct { // ť Ÿ��
	element data[MAX_QUEUE_SIZE];
	int front, rear;
} DequeType;
void error(char* message) {
	fprintf(stderr, "%s\n", message);
	exit(1);
}
void init_deque(DequeType* q) {
	q->front = q->rear = 0;
}
int is_empty(DequeType* q) {
	return (q->front == q->rear);
}
int is_full(DequeType* q) {
	return ((q->rear + 1) % MAX_QUEUE_SIZE == q->front);
}
void add_rear(DequeType* q, element item) {
	if (is_full(q))
		error("ť�� ��ȭ�����Դϴ�");
	q->rear = (q->rear + 1) % MAX_QUEUE_SIZE;
	q->data[q->rear] = item;
}
element get_front(DequeType* q) {
	if (is_empty(q))
		error("ť�� ��������Դϴ�");
	return q->data[(q->front + 1) % MAX_QUEUE_SIZE];
}void deque_print(DequeType* q) {
	printf("DEQUE(front=%d rear=%d) = ", q->front, q->rear);
	if (!is_empty(q)) {
		int i = q->front;
		do {
			i = (i + 1) % (MAX_QUEUE_SIZE);
			printf("%d | ", q->data[i]);
			if (i == q->rear)
				break;
		} while (i != q->front);
	}
	printf("\n");
}
void add_front(DequeType* q, element val) {
	if (is_full(q))
		error("ť�� ��ȭ�����Դϴ�");
	q->data[q->front] = val;
	q->front = (q->front - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
}
element get_rear(DequeType* q) {
	if (is_empty(q))
		error("ť�� ��������Դϴ�");
	return q->data[q->rear];
}
element delete_rear(DequeType* q) {
	int prev = q->rear;
	if (is_empty(q))
		error("ť�� ��������Դϴ�");
	q->rear = (q->rear - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
	return q->data[prev];
}
element delete_front(DequeType* q) {
	if (is_empty(q))
		error("ť�� ��������Դϴ�");
	q->front = (q->front + 1) % MAX_QUEUE_SIZE;
	return q->data[q->front];
}
int get_count(DequeType* q) { // �����Ͱ� �󸶳� �ִ��� �Ǵ��ϴ� �Լ�
	return q->rear - q->front;
}
void main() {
	DequeType queue;
	char string[100];
	printf("���ڿ��� �Է����ּ���: ");
	gets_s(string, 100);

	init_deque(&queue);
	int equal = 1;

	for (int i = 0; i < strlen(string); i++) {
		add_front(&queue, string[i]);
	}

	while (get_count(&queue) > 1) {
		//0�϶� while ������
		char f = delete_front(&queue);
		char r = delete_rear(&queue);
		if (f != r) {
			//print�� �ٷ� ����ص���
			equal = 0;
			break;
		}
		if (equal) {
			printf("%s ȭ��", string);
		}
		else {
			printf("%s ȸ�� �ƴ�", string);
		}
	}
	

}