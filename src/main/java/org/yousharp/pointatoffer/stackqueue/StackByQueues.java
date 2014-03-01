package org.yousharp.pointatoffer.stackqueue;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.ListNode;
import org.yousharp.common.Queue;
import org.yousharp.common.Stack;

import sun.util.logging.resources.logging;

/**
 * implement stack by two queues
 * User: Daniel
 * Date: 13-12-15
 * Time: 上午11:19
 */
public class StackByQueues {
	private static Logger logger = LoggerFactory.getLogger(StackByQueues.class);

	private static LinkedList<Integer> firstQueue = new LinkedList<Integer>();
	private static LinkedList<Integer> secondQueue = new LinkedList<Integer>();

	/**
	 * push an element to the stack
	 * the queue which is un-empty, is used to add elements
	 *
	 * @param element
	 */
	public static void push(int element) {
		if (firstQueue.isEmpty()) {
			secondQueue.offer(element);
		} else {
			firstQueue.offer(element);
		}
	}

	/**
	 * pop and element of the stack
	 * the last element of the queue which is not empty is ready to pop
	 *
	 * @return
	 */
	public static int pop() {
		if (firstQueue.isEmpty() && secondQueue.isEmpty()) {
			logger.info("error: the stack is empty.");
			return Integer.MAX_VALUE;
		}
		if (firstQueue.isEmpty()) {
			while (secondQueue.size() != 1) {
				int element = secondQueue.poll();
				firstQueue.offer(element);
			}
			return secondQueue.poll();
		}
		if (secondQueue.isEmpty()) {
			while (firstQueue.size() != 1) {
				int element = firstQueue.poll();
				secondQueue.offer(element);
			}
			return firstQueue.poll();
		}
		return Integer.MAX_VALUE;
	}




	/**
	 * for test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		StackByQueues.push(10);
		StackByQueues.push(20);
		StackByQueues.push(30);
		logger.info("{},{},{}", StackByQueues.pop(), StackByQueues.pop(), StackByQueues.pop(), StackByQueues.pop());

	}
}

/**
 *  思路：两个队列q1和q2，向q1里压入3个元素a, b, c，如果需要弹出最后压入的元素，即a，
 *  此时，先将q1中除头部(即a元素)外的所有元素都出队列，并入队到q2，将q1仅有的一个元素出队；再出栈，将
 *  q2中的除头部元素(即b元素)外的所有元素都出队并入队到q1，q2仅有的元素出队，即可模仿出栈操作；模仿入栈，
 *  两个队列始终有一个为空，非空的队列入队一个元素，即为入栈了。
 */