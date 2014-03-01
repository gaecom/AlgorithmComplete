package org.yousharp.pointatoffer.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.ListNode;

/**
 * 题目描述：
 * 给定一个链表，逆序输出节点的值
 * User: Daniel
 * Date: 13-12-8
 * Time: 下午9:04
 */
public class PrintLinkedListReversely {
	private static Logger logger = LoggerFactory.getLogger(PrintLinkedListReversely.class);

	/**
	 * implement by stack
	 * note: LinkedList can be used as both stack and queue in Java
	 * @param head the head of the link list
	 */
	public static void stackImpl(LinkedList<Integer> head) {
		if (null == head) {
			return;
		}
		LinkedList<Integer> tmp = new LinkedList<Integer>();

		Iterator<Integer> iter = head.iterator();
		while (iter.hasNext()) {
			tmp.push(iter.next());
		}

		while (!tmp.isEmpty()) {
			logger.info("node: {}", tmp.pop());
		}
	}

	/**
	 * recursive implementation
	 * be care of stack overflow
	 * @param head
	 */
	public static void recursionImpl(ListNode head) {
		if (null == head) {
			return;
		}
		recursionImpl(head.next);
		logger.info("node: {}", head.value);
	}

	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);

		stackImpl(list);

	}
}

/**
 * 思路：
 * 1. 反转链表，如果允许改变输入的结构；
 * 2. 将链表元素保存在数组里，倒序输出。（后进先出，不是栈么？）
 * 3. 递归的本质也是栈。（不过递归要注意栈溢出）
 */
