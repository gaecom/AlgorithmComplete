package org.yousharp.pointatoffer.stackqueue;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：
 * 实现一个栈，包含一个返回栈中最小元素的函数，
 * 要求入栈、出栈和返回最小值的时间复杂度均为O(1)
 *
 * User: lingguo
 * Date: 14-3-25
 * Time: 下午10:45
 */
public class StackWithMinMethod {
	private static Logger logger = LoggerFactory.getLogger(StackWithMinMethod.class);

	// dataStack: used to store data of the stack
	// minStack: used to store the current minimum data of the stack
	private static LinkedList<Integer> dataStack = new LinkedList<Integer>();
	private static LinkedList<Integer> minStack = new LinkedList<Integer>();
	private static final int RETURN_ERROR = Integer.MIN_VALUE;  // error flag

	/**
	 * push an element to the stack
	 *
	 * @param element   the element to push
	 */
	public static void push(int element) {
		// if the minStack is not empty and the element to push is bigger than the current min element,
		// the current min element is not changed, so store it again
		if (!minStack.isEmpty() && element > minStack.peek()) {
			minStack.push(minStack.peek());
		} else {    // the min element is changed, so update it
			minStack.push(element);
		}
		dataStack.push(element);       // push to data stack
	}

	/**
	 * pop an element from the data stack
	 *
	 * @return  the top element
	 */
	public static int pop() {
		// both the dataStack and minStack are updated
		if (!dataStack.isEmpty() && !minStack.isEmpty()) {
			minStack.pop();
			return dataStack.pop();
		}
		return RETURN_ERROR;
	}

	/**
	 * get the min element of the data stack
	 *
	 * @return the min element
	 */
	public static Object min() {
		// get from the min stack, that's it
		if (!minStack.isEmpty()) {
			return minStack.peek();
		}
		return RETURN_ERROR;
	}

	public static void main(String[] args) {
		StackWithMinMethod minStack = new StackWithMinMethod();
		minStack.push(10);
		logger.info("min: {}", minStack.min());
		minStack.push(8);
		logger.info("min: {}", minStack.min());
		minStack.push(12);
		logger.info("min: {}", minStack.min());
		minStack.pop();
		minStack.pop();
		logger.info("min: {}", minStack.min());
		minStack.pop();
		minStack.pop();
		logger.info("min: {}", minStack.min());
	}
}

/**
 *  思路：空间换时间，使用另一个辅助栈用来保存当前栈中的最小值。
 *  入栈：首先和辅助栈的栈顶元素比较，如果小于栈顶元素，则将该元素也push进辅助栈，如果
 *     该元素小于辅助栈栈顶元素，则将辅助栈的栈顶元素重新push进辅助栈；
 *  出栈：出栈的同时也将辅助栈的栈顶元素出栈；
 *  返回最小值：返回辅助栈的栈顶元素即可。
 */
