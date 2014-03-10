package org.yousharp.common;

/**
 * node of a linked list, stack or queue
 * next: reference to the next node
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午8:19
 */
public class ListNode {
	public int value;
	public ListNode next;

	public ListNode (int value) {
		this.value = value;
		this.next = null;
	}

	public ListNode() {}
}
