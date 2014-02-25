package org.yousharp.pointatoffer.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.ListNode;

import java.util.LinkedList;

/**
 * there are N numbers in a circle, delete the Mth number every time
 *      from the circle and begin at the 0th number, the next begin number is the one
 *      that after the deleted number. What is the last number remained in the circle?
 * User: Daniel
 * Date: 14-1-17
 * Time: 下午8:42
 */
public class LastNumberInCircle {
	private static Logger logger = LoggerFactory.getLogger(LastNumberInCircle.class);

	/**
	 * we construct a linked list, and delete the Mth element from the circle each time,
	 *      remember to move the start node forward after each delete operation.
	 * @param head  the head node of the link list
	 * @param m delete the Mth number from the list
	 * @return  the last remaining number of the list
	 */
	public ListNode linkListSolution(ListNode head, int m) {
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
			head = preNode.next;
		}
		return head;
	}

	/**
	 * we do not construct linked list, we use the standard library LinkedList;
	 *      the key point is to get the next start number.
	 * @param numberList    the LinkedList containing all te numbers
	 * @param m delete the Mth number from the list
	 * @param n the number of numbers in the list
	 * @return  the last remaining number
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
			start = delIndex;
			if (start >= n) {
				start = 0;
			}
		}
		return numberList.getLast();
	}

	/**
	 * using the math recursive and implementing by loop:
	 *      f(n,m) = f'(n-1,m) = (f(n-1,m)+m) % n;
	 * @param numberList    the LinkedList containing all te numbers
	 * @param m delete the Mth number from the list
	 * @param n the number of numbers in the list
	 * @return  the last remaining number in the list
	 */
	public int mathSolutionByLoop(LinkedList<Integer> numberList, int m, int n) {
		if (null == numberList || 0 == numberList.size()) {
			return -1;
		}
		// f(1,m) = 0;
		int last = 0;
		// f(n,m) = (f(n-1,m) + m) % n;
		for (int i = 2; i <= n; i++) {
			last = (last + m) % i;
		}
		return numberList.get(last);
	}

	/**
	 * using the math recursive method, and implementing by recursion:
	 *      f(n,m) = (f(n-1,m) + m) % n
	 * @param m the Mth element to delete in each recursion
	 * @param n the number of numbers in the list
	 * @return  the index of the last number in the list
	 */
	public int mathSolutionByRecursive(int m, int n) {
		if (1 == n) {
			return 0;
		}
		return (mathSolutionByRecursive(m, n - 1) + m) % n;
	}


	/**
	 * just for testing
	 * @param args
	 */
	public static void main(String[] args) {
		LastNumberInCircle lastInstance = new LastNumberInCircle();
		ListNode head = new ListNode(4);
		ListNode node1 = head.next = new ListNode(7);
		ListNode node2 = node1.next = new ListNode(9);
		ListNode node3 = node2.next = new ListNode(2);
		ListNode node4 = node3.next = new ListNode(13);
		ListNode node5 = node4.next = new ListNode(98);
//		ListNode node6 = node5.next = new ListNode(6);
		logger.info("1. last value is: {}", lastInstance.linkListSolution(head, 4).value);

		LinkedList<Integer> numberList = new LinkedList<Integer>();
		numberList.add(4);
		numberList.add(7);
		numberList.add(9);
		numberList.add(2);
		numberList.add(13);
		numberList.add(98);
//		numberList.add(6);
		LinkedList<Integer> numberList2 = new LinkedList<Integer>();
		numberList2.addAll(numberList);
		logger.info("2. last value is: {}", lastInstance.arrayListSolution(numberList, 4, 6));
		logger.info("3. last value is: {}", lastInstance.mathSolutionByLoop(numberList2, 4, 6));
		int index = lastInstance.mathSolutionByRecursive(4, 6);
		logger.info("4. last value is: {}", numberList2.get(index));
	}
}

/**
 *  问题描述：给定数字序列，长度为n，如0, 1, 2, ... , n-1构成一个圆圈，从第一个数字开始，
 *      每次从序列中删除第m个元素，删除一个元素后，从删除元素的下一个元素继续，问序列中最后剩下的元素。
 *   思路：
 *   1.  构造一个链表，每次从链表中删除第m个元素，直到链表总只剩最后一个元素。
 *       复杂度：O(n^2)；
 *   2.   从数学的角度寻找规律：
 *      f(n,m):  表示从n个元素的序列删除m个元素后最后剩下的元素；
 *      删除第m个元素后，序列变成了：0, 1, 2, ... , m-2, m, m+1, ..., n-1，即：
 *      m, m+1, ..., n-1, 0, 1, 2, ..., m-2
 *      将该序列使用f'(n-1,m)表示，显然有f(n,m) = f'(n-1,m)，因为最后剩下的数字时相同的。
 *      原序列为的f(n-1,m)为：
 *      0, 1, 2, ..., n-2
 *      可见从f(n-1,m)-->f'(n-1, m)的映射关系为：(f(n-1,m)+m)%n，综合：f(n,m) = f'(n-1,m)，得到：
 *      f(n,m) = f'(n-1,m) = (f(n-1),m) + m) % n;
 *      所以只要得到f(n-1,m)即可得到f(n,m)，而f(1,m) = 0;所以有：
 *      f(n,m) = f'(n-1,m) = (f(n-1),m) + m) % n;  (n > 1)
 *      然后递归或循环都可以实现。
 *       复杂度：O（n）
 *       */