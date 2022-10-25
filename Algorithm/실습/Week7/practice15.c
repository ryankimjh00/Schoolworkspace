#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef char element;
typedef struct StackNode {
	element data;
	struct StackNode* link;
} StackNode;
typedef struct {
	StackNode* top;
} LinkedStackType;
// �ʱ�ȭ �Լ�
void init(LinkedStackType* s) {
	s->top = NULL;
}
// ���� ���� ���� �Լ�
int is_empty(LinkedStackType* s) {
	return (s->top == NULL);
}
// ���� �Լ�
void push(LinkedStackType* s, element item) {
	StackNode* temp = (StackNode*)malloc(sizeof(StackNode));
	temp->data = item;
	temp->link = s->top; //���� ��ġ�� ���� ��
	s->top = temp;

}
void print_stack(LinkedStackType* s) {
	for (StackNode* p = s->top; p != NULL; p = p->link) {
		printf("%d", p->data);
	}
}
// ���� �Լ�
element pop(LinkedStackType* s) {
	if (!is_empty(s)) {
		exit(1);
	}
	StackNode* temp = s->top;
	element d = temp->data;
	s->top = s->top->link; // temp->link; �־��
	free(temp);
	return d;
}
int main(void) {
	char name[] = "Kwangseob Kim";
	LinkedStackType s;
	init(&s);
	for (int i = 0; i < strlen(name); i++) {
		push(&s, name[i]);
	}
	while (!is_empty(&s)) {
		printf("%c", pop(&s));
	}
	return 0;
}