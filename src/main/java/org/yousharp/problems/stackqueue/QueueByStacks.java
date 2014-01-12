package org.yousharp.problems.stackqueue;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.algorithm.datastructure.ListNode;
import org.yousharp.algorithm.datastructure.Stack;

/**
 * 使用两个栈实现队列
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午8:13
 */
public class QueueByStacks {
	private static Logger logger = LoggerFactory.getLogger(QueueByStacks.class);
	private static Stack firstStack = new Stack();
	private static Stack secondStack = new Stack();

	/**
	 * enqueue opration
	 *
	 * @param newNode
	 */
	private void enqueue(ListNode newNode) {
		firstStack.push(newNode);
	}

	/**
	 * dequeue operation
	 *
	 * @return
	 */
	private ListNode dequeue() {
		if (secondStack.size() <= 0) {
			while (firstStack.size() > 0) {
				ListNode firstTopBak = firstStack.peek();
				firstStack.pop();
				secondStack.push(firstTopBak);
			}
			if (secondStack.size() <= 0) {
				logger.info("two stacks are empty, cannot dequeue.");
			}
		}
		ListNode secondTopBak = new ListNode(secondStack.peek().value);
		secondStack.pop();
		return secondTopBak;
	}

	/**
	 * return the size of the current queue
	 *
	 * @return
	 */
	private int size() {
		return (firstStack.size() + secondStack.size());
	}

	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ListNode> nodeList = new ArrayList<ListNode>();
		nodeList.add(new ListNode(1));
		nodeList.add(new ListNode(2));
		nodeList.add(new ListNode(3));
		nodeList.add(new ListNode(4));
		nodeList.add(new ListNode(5));

		QueueByStacks queue = new QueueByStacks();

		for (ListNode node : nodeList) {
			queue.enqueue(node);
		}

		while (queue.size() > 0) {
			logger.info("queue size: {}", queue.size());
			logger.info("queue member: {}", queue.dequeue().value);
		}
	}
}

/**
 *  思路：栈1用来入队，栈2用来出队；
 *  如果栈2为空，则将栈1中的所有元素依次出栈并放入栈2中。
 */
