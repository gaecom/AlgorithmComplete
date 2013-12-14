package org.yousharp.algorithm.datastructure;

/**
 * queue definition
 * front->next->...->tail
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午11:12
 */
public class Queue {
	public ListNode front;
	public ListNode tail;

	/**
	 * insert a node into the queue
	 * @param newNode
	 */
	public void enqueue(ListNode newNode) {
		if (null != newNode) {
			if (null == tail) {
				tail = newNode;
				front = tail;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
		}
	}

	/**
	 * remove a node from the queue
	 * @return
	 */
	public ListNode dequeue() {
		if (null != front) {
			ListNode frontBak = new ListNode(front.value);
			front = front.next;
			return frontBak;
		}
		return null;
	}


}
