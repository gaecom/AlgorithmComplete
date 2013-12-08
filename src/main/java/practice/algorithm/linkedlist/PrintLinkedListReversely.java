package practice.algorithm.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**题目描述：
 * 逆序输出链表
 * User: Daniel
 * Date: 13-12-8
 * Time: 下午9:04
 */
public class PrintLinkedListReversely {
	private static Logger logger = LoggerFactory.getLogger(PrintLinkedListReversely.class);

	/**
	 * print a link list reversely
	 * @param head the head node of the link list
	 */
	public static void printLinkedListReversely(Node head) {
		if (null == head) {
			return;
		}

		// define an array to hold all the nodes in the link list
		Node[] allNodes = new Node[100];
		int numOfNodes = 0;

		// traverse and save all nodes
		while (null != head) {
			allNodes[numOfNodes++] = head;
			head = head.next;
		}

		// print node value
		for (int j = numOfNodes - 1; j >= 0; j --) {
			logger.info("{}", allNodes[j].value);
		}
	}

	/**
	 * print link list reversely by recursion
	 * be care of stack overflow
	 * @param head
	 */
	public static void printRecursively(Node head) {
		if (null == head) {
			return;
		}

		printRecursively(head.next);
		logger.info("{}", head.value);
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		Node tmp = head;
		for (int i = 0; i < 5; i ++) {
			int value = i * 2 + 1;
			Node newNode = new Node(value);
			tmp.next = newNode;
			tmp = newNode;
		}

		printLinkedListReversely(head);
		printRecursively(head);
	}
}

/**
 * node info
 */
class Node {
	int value;
	Node next;
	public Node (int value) {
		this.value = value;
		this.next = null;
	}
}

/**
 * 思路：
 * 1. 翻转链表，如果允许改变输入的结构；
 * 2. 将链表元素保存在数组里，倒序输出。（后进先出，不是栈么？）
 * 3. 递归的本质也是栈。（不过递归要注意栈溢出）
 */
