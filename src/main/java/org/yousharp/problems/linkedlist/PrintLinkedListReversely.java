package org.yousharp.algorithm.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.algorithm.datastructure.ListNode;

/**题目描述：
 * 给定一个链表，逆序输出节点的值
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
	public static void printLinkedListReversely(ListNode head) {
		if (null == head) {
			return;
		}

		// define an array to hold all the nodes in the link list
		ListNode[] allNodes = new ListNode[100];
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
	public static void printRecursively(ListNode head) {
		if (null == head) {
			return;
		}

		printRecursively(head.next);
		logger.info("{}", head.value);
	}

	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode tmp = head;
		for (int i = 0; i < 5; i ++) {
			int value = i * 2 + 1;
			ListNode newNode = new ListNode(value);
			tmp.next = newNode;
			tmp = newNode;
		}

		printLinkedListReversely(head);
		printRecursively(head);
	}
}

/**
 * 思路：
 * 1. 反转链表，如果允许改变输入的结构；
 * 2. 将链表元素保存在数组里，倒序输出。（后进先出，不是栈么？）
 * 3. 递归的本质也是栈。（不过递归要注意栈溢出）
 */
