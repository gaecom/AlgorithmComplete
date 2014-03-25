package org.yousharp.pointatoffer.linkedlist;

import org.yousharp.common.ListNode;

/**
 * 问题：反转单链表
 * User: lingguo
 * Date: 14-3-17
 * Time: 下午9:03
 */
public class ReverseLinkList {

	/**
	 * reverse a linked list
	 * @param head  the head of the linked list
	 * @return  the head of the reversed list
	 */
	public static ListNode reverse(ListNode head) {
		// if the list is null or the list has only one node
		if (head == null || head.next == null) {
			return head;
		}

		// we need to record three pointers: the current one, the previous and the next
		ListNode previous = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = previous;
			previous = head;
			head = next;
		}
		return previous;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(10);
		head.next = new ListNode(20);
		head.next.next = new ListNode(30);
		head.next.next.next = new ListNode(40);

		ListNode newHead = ReverseLinkList.reverse(head);
		while (newHead != null) {
			System.out.println(newHead.value + "->");
			newHead = newHead.next;
		}
	}
}
