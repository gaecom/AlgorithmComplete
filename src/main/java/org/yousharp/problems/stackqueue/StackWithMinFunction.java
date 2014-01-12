package org.yousharp.problems.stackqueue;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：
 * 实现一个栈，包含一个返回栈中最小元素的函数，
 * 要求入栈、出栈和返回最小值的时间复杂度均为O(1)
 * User: Daniel
 * Date: 13-12-24
 * Time: 下午8:36
 */
public class StackWithMinFunction {
	private static Logger logger = LoggerFactory.getLogger(StackWithMinFunction.class);
	private LinkedList<Integer> data = new LinkedList<Integer>();
	private LinkedList<Integer> min = new LinkedList<Integer>();

	/**
	 * push and update the minimal value of the min stack
	 *
	 * @param value
	 */
	private void push(int value) {
		data.push(value);
		if (min.isEmpty()) {
			min.push(value);
		} else {
			int top = min.peek();
			if (top <= value) {
				min.push(top);
			} else {
				min.push(value);
			}
		}
	}

	/**
	 * need to pop out from the min stack
	 *
	 * @return
	 */
	private int pop() {
		if (min.isEmpty()) {
			throw new UnsupportedOperationException("stack is empty.");
		}
		min.pop();
		return data.pop();
	}

	/**
	 * return the first element
	 *
	 * @return
	 */
	private int min() {
		if (min.isEmpty()) {
			throw new UnsupportedOperationException("stack is empty");
		}
		return min.peek();
	}

	public static void main(String[] args) {
		StackWithMinFunction minStack = new StackWithMinFunction();
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