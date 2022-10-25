#include <stdio.h>
#include <stdlib.h>
typedef int element;// ����� Ÿ��
typedef struct QueueNode {// ť�� ����� Ÿ��
	element data;
	struct QueueNode* link;
} QueueNode;
typedef struct {// ť ADT ����
	QueueNode* front, * rear;
} LinkedQueueType;

void init(LinkedQueueType* q) {
	q->front = q->rear = NULL;
}
int is_empty(LinkedQueueType* q) {
	if (q->front == NULL) {
		return 1;
	}
	else {
		return 0;
	}
}

// ���� �Լ�
void enqueue(LinkedQueueType* q, element data) {
	QueueNode* temp = (QueueNode*)malloc(sizeof(QueueNode));
	temp->data = data;
	temp->link = NULL;
	if (is_empty(q)) {
		q->front = q->rear = temp;
	}
	else {
		q->rear->link = temp;
		q->rear = temp;
	}

}
// ���� �Լ�
element dequeue(LinkedQueueType* q) {
	QueueNode* temp = q->front;
	if (is_empty(q)) {
		exit(1);
	}
	element d = temp->data;
	q->front = q->front->link;
	free(temp);
	return d;
}

int main(void) {
	LinkedQueueType queue;

	init(&queue);
	enqueue(&queue, 0);
	enqueue(&queue, 1);
	for (int i = 0; i < 13; i++) {
		element e1 = dequeue(&queue);
		element e2 = dequeue(&queue);
		element e3 = e1 + e2;
		printf("%d ", e3);
		enqueue(&queue, e2);	// ť�� �߰��Ѵ�. 
		enqueue(&queue, e3);	// ť�� �߰��Ѵ�. 
	}
	return 0;

}