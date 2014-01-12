package org.yousharp.problems.stackqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.algorithm.datastructure.ListNode;
import org.yousharp.algorithm.datastructure.Queue;

/**
 * implement stack by two queues
 * User: Daniel
 * Date: 13-12-15
 * Time: 上午11:19
 */
public class StackByQueues {
	private static Logger logger = LoggerFactory.getLogger(StackByQueues.class);
	private Queue firstQueue = new Queue();
	private Queue secondQueue = new Queue();

	/**
	 * push an element to the stack
	 * only one queue is not empty, push is enqueue on the non-empty queue
	 *
	 * @param newNode
	 */
	private void push(ListNode newNode) {
		if (0 == firstQueue.size()) {
			secondQueue.enqueue(newNode);
		} else if (0 == secondQueue.size()) {
			firstQueue.enqueue(newNode);
		}
	}

	/**
	 * pop out the top element of the stack
	 * only one queue is not empty, let's say q1, and q2 is empty
	 * 1. dequeue all elements except for the front node from q1;
	 * 2. enqueue all nodes of them to q2;
	 * 3. dequeue the only node from q1;
	 *
	 * @return
	 */
	private ListNode pop() {
		if (0 == firstQueue.size() && 0 == secondQueue.size()) {
			return null;
		} else if (0 != firstQueue.size()) {    // q1 is not empty
			while (firstQueue.size() > 1) {
				ListNode tailNode = firstQueue.dequeue();
				secondQueue.enqueue(tailNode);
			}
			return firstQueue.dequeue();
		} else if (0 != secondQueue.size()) {   // q2 is not empty
			while (secondQueue.size() > 1) {
				ListNode tailNode = secondQueue.dequeue();
				firstQueue.enqueue(tailNode);
			}
			return secondQueue.dequeue();
		}
		return null;
	}

	/**
	 * size of the stack
	 * the peek function is similar to the pop
	 *
	 * @return
	 */
	private int size() {
		int firstSize = firstQueue.size();
		int secondSize = secondQueue.size();
		return (firstSize != 0 ? firstSize : secondSize);
	}


	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		StackByQueues stack = new StackByQueues();
		stack.push(new ListNode(1));
		stack.push(new ListNode(2));
		stack.push(new ListNode(3));

		logger.info("size: {}", stack.size());
		logger.info("top: {}", stack.pop().value);
		logger.info("top: {}", stack.pop().value);

		stack.push(new ListNode(4));
		logger.info("top: {}", stack.pop().value);
	}
}

/**
 *  思路：两个队列q1和q2，向q1里压入3个元素a, b, c，如果需要弹出最后压入的元素，即a，
 *  此时，先将q1中除头部(即a元素)外的所有元素都出队列，并入队到q2，将q1仅有的一个元素出队；再出栈，将
 *  q2中的除头部元素(即b元素)外的所有元素都出队并入队到q1，q2仅有的元素出队，即可模仿出栈操作；模仿入栈，
 *  两个队列始终有一个为空，非空的队列入队一个元素，即为入栈了。
 */