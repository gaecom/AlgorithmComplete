package org.yousharp.pointatoffer.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.ListNode;

/**
 * 描述：寻找链表中倒数第k个节点
 * User: lingguo
 * Date: 14-3-11
 * Time: 下午11:51
 */
public class FindKthNode {
	private static Logger logger = LoggerFactory.getLogger(FindKthNode.class);

	/**
	 * using two pointers, the first one walk forward (k-1) steps and then the two
	 * pointers walk forward together. when the first one points to the last node,
	 * the second on points the Kth node in reverse orcer.
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode find(ListNode head, int k) {
		if (head == null || k <= 0) {
			return null;
		}

		// the first node walks k-1 steps
		ListNode firstNode = head;
		int i = 0;
		while ((i < k -1) && (firstNode.next != null)) {
				firstNode = firstNode.next;
				i++;
		}
		// if the length of the list is less than K, return
		if (i != k - 1) {
			return null;
		}

		// the two pointers walk together until the first one reaches to the end
		ListNode secondNode = head;
		while (firstNode.next != null) {
			firstNode = firstNode.next;
			secondNode = secondNode.next;
		}
		return secondNode;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(10);
		head.next = new ListNode(20);
		head.next.next = new ListNode(30);
		head.next.next.next = new ListNode(40);
		logger.info("{}", FindKthNode.find(head, 1).value);
	}
}
