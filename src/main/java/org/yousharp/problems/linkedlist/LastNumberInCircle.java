package org.yousharp.problems.linkedlist;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.algorithm.datastructure.ListNode;

/**
 * there are N numbers in a circle, delete the Mth number every time
 *      from the circle and begin at the 0th number. What is the last number
 *      remained in the circle?
 * User: Daniel
 * Date: 14-1-17
 * Time: 下午8:42
 */
public class LastNumberInCircle {
	private static Logger logger = LoggerFactory.getLogger(LastNumberInCircle.class);

	/**
	 * we construct a linked list, and delete the Mth element from the circle each time,
	 *      remember to move the start node forward after each delete operation.
	 * @param head
	 *      the head node of the link list
	 * @param m
	 *      delete the Mth number from the list
	 * @param n
	 *      the number of numbers in the list
	 * @return
	 *      the last remaining number of the list
	 */
	public ListNode linkListSolution(ListNode head, int m, int n) {
		if (null == head) {
			return null;
		}

		// link the last node to head to form a loop link list
		ListNode loopNode = head;
		while (null != loopNode.next) {
			loopNode = loopNode.next;
		}
		loopNode.next = head;

		// loop until there is only one node in the list
		while (head.next != head) {
			// first step: we need to find the node before the Mth node
			ListNode preNode = head;
			for (int i = 0; i < m - 2; i++) {
				preNode = preNode.next;
			}
			// the node to delete (the Mth node)
			ListNode delNode = preNode.next;
			// delete the Mth node
			preNode.next = delNode.next;
			// the next start node
			head = head.next;
		}
		return head;
	}

	/**
	 * we do not construct linked list, we use the standard library LinkedList;
	 *      the key point is to get the next start number.
	 * @param numberList
	 *      the LinkedList containing all te numbers
	 * @param m
	 *      delete the Mth number from the list
	 * @param n
	 *      the number of numbers in the list
	 * @return
	 *      the last remaining number
	 */
	public int arrayListSolution(LinkedList<Integer> numberList, int m, int n) {
		if (null == numberList || 0 == numberList.size()) {
			return -1;
		}

		// start from 0
		int start = 0;
		while (n > 1) {
			// get the index of the number to be deleted
			int delIndex = (start + m - 1) % n;
			numberList.remove(delIndex);
			n = numberList.size();
			// get the index of the next start number
			if (start == n) {
				start = 0;
			} else {
				start ++;
			}
		}
		return numberList.getLast();
	}

	/**
	 * using the math recursive:
	 *      f(n,m) = f'(n-1,m) = (f(n-1,m)+m) % n;
	 * @param numberList
	 *      the LinkedList containing all te numbers
	 * @param m
	 *      delete the Mth number from the list
	 * @param n
	 *      the number of numbers in the list
	 * @return
	 *      the last remaining number in the list
	 */
	public int mathSolutionByLoop(LinkedList<Integer> numberList, int m, int n) {
		if (null == numberList || 0 == numberList.size()) {
			return -1;
		}
		// f(1,m) = 0;
		int last = numberList.getFirst();
		// f(n,m) = (f(n-1,m) + m) % n;
		for (int i = 2; i <= n; i++) {
			last = numberList.get((i + m) % i);
		}
		return last;
	}

	public int mathSolutionByRecursive(LinkedList<Integer> numberList, int m, int n) {
		if (null == numberList || 0 == numberList.size()) {
			return -1;
		}
		if (1 == n) {
			return numberList.getFirst();
		}
		return mathSolutionByRecursive(numberList, m, n - 1) + numberList.get(m);
	}


	public static void main(String[] args) {
		LastNumberInCircle lastInstance = new LastNumberInCircle();
		ListNode head = new ListNode(0);
		ListNode node1 = head.next = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(2);
		ListNode node3 = node2.next = new ListNode(3);
		ListNode node4 = node3.next = new ListNode(4);
		logger.info("1. last value is: {}", lastInstance.linkListSolution(head, 3, 5).value);

		LinkedList<Integer> numberList = new LinkedList<Integer>();
		numberList.add(0);
		numberList.add(1);
		numberList.add(2);
		numberList.add(3);
		numberList.add(4);
		LinkedList<Integer> numberList2 = new LinkedList<Integer>();
		numberList2.addAll(numberList);
		logger.info("2. last value is: {}", lastInstance.arrayListSolution(numberList, 3, 5));
		logger.info("3. last value is: {}", lastInstance.mathSolutionByLoop(numberList2, 3, 5));
		logger.info("4. last value is: {}", lastInstance.mathSolutionByRecursive(numberList2, 3, 5));
	}
}
