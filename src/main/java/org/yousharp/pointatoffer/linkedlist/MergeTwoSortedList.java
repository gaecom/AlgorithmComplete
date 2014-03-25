package org.yousharp.pointatoffer.linkedlist;

import org.yousharp.common.ListNode;

/**
 * 问题描述：给定两个单链表，都是递增有序的，将它们合并，合并后的链表仍然有序。
 * User: lingguo
 * Date: 14-3-17
 * Time: 下午9:16
 */
public class MergeTwoSortedList {

	/**
	 * merge two sorted linked list
	 * @param firstHead     head of the first sorted linked list
	 * @param secondHead    head of the second sorted linked list
	 * @return  head of the merged sorted linked list
	 */
	public static ListNode merge(ListNode firstHead, ListNode secondHead) {
		// the exit of recursion
		if (firstHead == null) {
			return secondHead;
		}
		if (secondHead == null) {
			return firstHead;
		}

		// compare the two current nodes and pick the small one as the new head node
		ListNode currentHead = null;
		if (firstHead.value < secondHead.value) {
			currentHead = firstHead;
			// selected the new node, then recursing on the remain two parts
			currentHead.next = merge(firstHead.next, secondHead);
		} else {
			currentHead = secondHead;
			// selected the new node, then recursing on the remain two parts
			currentHead.next = merge(firstHead, secondHead.next);
		}
		return currentHead;     // return the new head
	}

	public static void main(String[] args) {
		ListNode firstHead = new ListNode(1);
		firstHead.next = new ListNode(3);
		firstHead.next.next = new ListNode(5);
		firstHead.next.next.next = new ListNode(14);

		ListNode secondHead = new ListNode(2);
		secondHead.next = new ListNode(6);
		secondHead.next.next = new ListNode(10);
		secondHead.next.next.next = new ListNode(20);

		ListNode newHead = MergeTwoSortedList.merge(firstHead, secondHead);
		while (newHead != null) {
			System.out.print(newHead.value + "->");
			newHead = newHead.next;
		}

	}
}
