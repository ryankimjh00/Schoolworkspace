#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef int element;
typedef struct TreeNode {
	char name[100];
	char phone[100];
	struct TreeNode* left, * right;
} TreeNode;

TreeNode* new_node(char name[], char phone[]) {
	TreeNode* temp = (TreeNode*)malloc(sizeof(TreeNode));
	strcpy(temp->name, name);//
	strcpy(temp->phone, phone);//
	temp->left = temp->right = NULL;
	return temp;
}
TreeNode* min_value_node(TreeNode* node) {
	TreeNode* current = node;
	while (current->left != NULL) {
		current = current->left;
	}
	return current;
}
TreeNode* insert_node(TreeNode* node, char name[], char phone[]) {
	if (node == NULL)
		return new_node(name, phone);
	if (strcmp(name, node->name) < 0)
		node->left = insert_node(node->left, name, phone);
	else if (strcmp(name, node->name) > 0)
		node->right = insert_node(node->right, name, phone);
	return node;
}
TreeNode* delete_node(TreeNode* root, char name[]) {
	if (root == NULL)
		return root;
	if (strcmp(name, root->name) < 0)
		root->left = delete_node(root->left, name);
	else if (strcmp(name, root->name) > 0)
		root->right = delete_node(root->right, name);
	else {
		if (root->left == NULL) {
			TreeNode* temp = root->right;
			free(root);
			return temp;
		}
		else if (root->right == NULL) {
			TreeNode* temp = root->left;
			free(root);
			return temp;
		}
		TreeNode* temp = min_value_node(root->right);
		//strcpy() name, phone
		root->right = delete_node(root->right, temp->name);
	}
	return root;
}
TreeNode* search(TreeNode* node, char name) {
	if (node == NULL) {
		return NULL;
	}
	if (strcmp(name, node->name) == 0) {
		return node;
	}
	else if (strcmp(name, node->name) < 0) {
		return search(node->left, name);
	}
	else {
		return search(node->right, name);
	}
}


int main(void) {
	char command, name[100], phone[100];
	TreeNode* root = NULL, * p;


	FILE* fp = ;

	do {
		printf("삽입(i), 파일 저장(S), 탐색(s), 삭제(d), 전체 출력(p),종료(q) ");
		command = getchar();
		fflush(stdin);
		switch (command) {
		case 'i':
			scanf("%s", name);
			fflush(stdin);
			scanf("%s", phone);
			fflush(stdin);
			root = insert_node(root, name, phone);
			break;
		case 'S':
			fopen(&fp, "phonebook.txt", "wt");
			break;
		case 's':
			search(root, name);
			break;
		case 'd':
			scanf("%s", name);
			fflush(stdin);
			root = delete_node(root, name);
			break;
		case 'p':
			break;
		case 'q':
			break;
		}
		while (getchar() != '\n');
	} while (command != 'q');
}