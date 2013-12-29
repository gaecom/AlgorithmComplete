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
	 * you have to judge if the queue is empty and if the queue has
	 * only one element, in which case, front == tail
	 * @return
	 */
	public ListNode dequeue() {
		if (front != tail) {
			ListNode frontBak = new ListNode(front.value);
			front = front.next;
			return frontBak;
		} else if (front == tail && null != front) {
			ListNode frontBak = new ListNode(front.value);
			front = null;
			tail = null;
			return frontBak;
		}
		return null;
	}

	/**
	 * return the size of the queue
	 * @return
	 */
	public int size() {
		int size = 0;
		if (null == front) {    // the queue is empty
			return size;
		}
		size++;
		ListNode frontBak = front;
		while (frontBak != tail) {
			size++;
			frontBak = frontBak.next;
		}
		return size;
	}
}
