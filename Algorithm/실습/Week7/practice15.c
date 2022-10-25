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
// 초기화 함수
void init(LinkedStackType* s) {
	s->top = NULL;
}
// 공백 상태 검출 함수
int is_empty(LinkedStackType* s) {
	return (s->top == NULL);
}
// 삽입 함수
void push(LinkedStackType* s, element item) {
	StackNode* temp = (StackNode*)malloc(sizeof(StackNode));
	temp->data = item;
	temp->link = s->top; //현재 위치에 대한 값
	s->top = temp;

}
void print_stack(LinkedStackType* s) {
	for (StackNode* p = s->top; p != NULL; p = p->link) {
		printf("%d", p->data);
	}
}
// 삭제 함수
element pop(LinkedStackType* s) {
	if (!is_empty(s)) {
		exit(1);
	}
	StackNode* temp = s->top;
	element d = temp->data;
	s->top = s->top->link; // temp->link; 넣어도됨
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