#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef int element;
#define MAX_STACK_SIZE 100
typedef struct {
    element data[MAX_STACK_SIZE];
    int top;
} StackType;

void init_stack(StackType* s) {
    s->top = -1;
}

int is_empty(StackType* s) {
    return (s->top == -1);
}

int is_full(StackType* s) {
    return (s->top == (MAX_STACK_SIZE - 1));
}

void push(StackType* s, element item) {
    if (is_full(s)) {
        fprintf(stderr, "스택 포화 에러\n");
        return;
    }
    else s->data[++(s->top)] = item;
}

element pop(StackType* s) {
    if (is_empty(s)) {
        fprintf(stderr, "스택 공백 에러\n");
        exit(1);
    }
    else return s->data[(s->top)--];
}

element peek(StackType* s) {
    if (is_empty(s)) {
        fprintf(stderr, "스택 공백 에러\n");
        exit(1);
    }
    else return s->data[s->top];
}

int eval(char exp[]) {
    int op1, op2, value, i = 0;
    int len = strlen(exp);
    char ch;
    StackType s;
    init_stack(&s);
    for (i = 0; i < len; i++) {
        if (exp[i] == ' ') {
            i++;
        }
        ch = exp[i];
        if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
            char temp[100];
            int j = 0;
            while (exp[i] != ' ') {
                temp[j] = exp[i];
                i++;
                j++;
            }
            temp[j] = "/0";
            value = atoi(temp);
            push(&s, value);
        }
        else {
            op2 = pop(&s);
            op1 = pop(&s);
            switch (ch) {
            case '+': push(&s, op1 + op2); break;
            case '-': push(&s, op1 - op2); break;
            case '*': push(&s, op1 * op2); break;
            case '/': push(&s, op1 / op2); break;
            }
        }
    }
    return pop(&s);
}
int main(void) {
    int result;
    printf("후위표기식은 2 200 2 5 * 3 - + *\n");
    result = eval("2 200 2 5 * 3 - + *");
    printf("결과값은 %d\n", result);
    return 0;
}
