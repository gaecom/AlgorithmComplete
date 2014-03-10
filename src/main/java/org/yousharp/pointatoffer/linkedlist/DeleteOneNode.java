package org.yousharp.pointatoffer.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.ListNode;

/**
 * 问题：给定一个链表的头和其中的某一个节点，在O(1)的时间内删除该指定的节点。
 * User: lingguo
 * Date: 14-3-10
 * Time: 下午11:49
 */
public class DeleteOneNode {
	private static Logger logger = LoggerFactory.getLogger(DeleteOneNode.class);

	public static ListNode delete(ListNode head, ListNode toDelete) {
		// param error
		if (head == null || toDelete == null) {
			return null;
		}
		// if the node to delete is the last node of the list
		if (toDelete.next == null) {
			// only one node in the list
			if (head == toDelete) {
				head = null;
				return head;
			}
			// more than one node in the list
			ListNode node = head;
			while (node.next != toDelete) {
				node = node.next;
			}
			node.next = null;
			return head;
		}
		// if the node to delete is not the last node,
		// get the value of the next node and delete the next node
		int tmp = toDelete.value;
		toDelete.value = toDelete.next.value;
		toDelete.next.value = tmp;
		toDelete.next = null;
		return head;
	}

	public static void main(String[] args) {
		ListNode root = new ListNode(10);
		ListNode second = new ListNode(5);
		root.next = second;
		ListNode toDelete = second;
		logger.info("head: {}", DeleteOneNode.delete(root, toDelete).value);
	}
}


/**
 * 思路：对于指定的节点，保留其下一个节点的值，删除下一个节点即可。
 * 需要注意一些特殊情况：待删除的节点是链表的最后一个节点，或者链表仅有一个节点。
 */