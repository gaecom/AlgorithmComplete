package org.yousharp.pointatoffer.stackqueue;

import java.util.ArrayList;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.ListNode;
import org.yousharp.common.Queue;
import org.yousharp.common.Stack;

/**
 * 使用两个栈实现队列
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午8:13
 */
public class QueueByStacks {
	private static Logger logger = LoggerFactory.getLogger(QueueByStacks.class);

	private static LinkedList<Integer> firstStack = new LinkedList<Integer>();
	private static LinkedList<Integer> secondStack = new LinkedList<Integer>();

	/**
	 * get an element out of the queue
	 * the second stack is used to dequeue
	 * @return
	 */
	public static int deQueue() {
		if (secondStack.isEmpty()) {
			while (!firstStack.isEmpty()) {
				int element = firstStack.pop();
				secondStack.push(element);
			}
		}
		if (secondStack.isEmpty()) {
			logger.info("error: queue is empty!");
			return Integer.MAX_VALUE;
		}
		return secondStack.pop();
	}

	/**
	 * insert an element to the queue
	 * the first stack is used to enqueue
	 * @param element
	 */
	public static void enQueue(int element) {
		firstStack.push(element);
	}

	/**
	 * for test
	 * @param args
	 */
	public static void main(String[] args) {
		QueueByStacks.enQueue(10);
		QueueByStacks.enQueue(20);
		QueueByStacks.enQueue(30);

		logger.info("{}, {}, {}", QueueByStacks.deQueue(), QueueByStacks.deQueue(), QueueByStacks.deQueue());
	}
}

/**
 *  思路：栈1用来入队，栈2用来出队；
 *  出队列时：如果栈2为空，则将栈1中的所有元素依次出栈并放入栈2中。
 *  入队列：直接将元素压入栈1中。
 */
