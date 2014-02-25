package org.yousharp.common;

/**
 * queue definition using LinkList
 * tail->next->...->front
 * enqueue: add a node to front
 * dequeue: delete a node from tail
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午11:12
 */
public class Queue {
	public ListNode front;
	public ListNode tail;

	/**
	 * add a node to the queue, check if the queue is empty
	 * front.next = newNode; front = newNode;
	 * @param newNode
	 */
	public void enqueue(ListNode newNode) {
		if (null != newNode) {
			if (null == front) {
				front = newNode;
				tail = front;
			} else {
				front.next = newNode;
				front = newNode;
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
		// the queue has more than one elements
		if (front != tail) {
			ListNode tailBak = new ListNode(tail.value);
			tail = tail.next;
			return tailBak;
		// the queue contains only one element
		} else if (front == tail && null != front) {
			ListNode tailBak = new ListNode(tail.value);
			front = null;
			tail = null;
			return tailBak;
		}
		return null;    // the queue is empty
	}

	/**
	 * return the size of the queue
	 * @return
	 */
	public int size() {
		int size = 0;
		if (null == tail) {    // the queue is empty
			return size;
		}
		size++;
		ListNode tailBak = tail;
		while (tailBak != front) {
			size++;
			tailBak = tailBak.next;
		}
		return size;
	}
}
