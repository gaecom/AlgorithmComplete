package org.yousharp.pointatoffer.tree;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.TreeNode;

/**
 * 问题描述：求二叉树的镜像
 *
 * User: Daniel
 * Date: 13-12-22
 * Time: 上午11:29
 */

public class MirrorOfBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(MirrorOfBinaryTree.class);

	/**
	 * recursion method
	 *
	 * @param root  the root of the tree
	 */
	private static void mirrorByRecursion(TreeNode root) {
		if (null == root) {
			return;
		}

		// swap left child and right child of the current node
		TreeNode leftBak = root.left;
		root.left = root.right;
		root.right = leftBak;

		// swap left child tree and right child tree by recursion
		mirrorByRecursion(root.left);
		mirrorByRecursion(root.right);
	}

	/**
	 * loop method: with the help of a stack, traverse the tree breadth-first
	 *
	 * @param root the root of the tree
	 */
	public static void mirrorByLoop(TreeNode root) {
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);   // push the root to the stack
		while (!stack.isEmpty()) {
			TreeNode currNode = stack.pop();    // get the top node
			if (currNode != null) {
				// push left and right children
				stack.push(currNode.left);
				stack.push(currNode.right);

				// swap left and right children
				TreeNode leftBak = currNode.left;
				currNode.left = currNode.right;
				currNode.right = leftBak;
			}
		}
	}


	public static void main(String[] args) {
		TreeNode head = new TreeNode(8);
		head.left = new TreeNode(6);
		head.right = new TreeNode(10);
		head.left.left = new TreeNode(5);
		head.left.right = new TreeNode(7);
		head.right.left = new TreeNode(9);
		head.right.right = new TreeNode(11);


		TraverseBinaryTreeByTier.traverse(head);
//		MirrorOfBinaryTree.mirrorByRecursion(head);
		MirrorOfBinaryTree.mirrorByLoop(head);
		TraverseBinaryTreeByTier.traverse(head);
	}
}
