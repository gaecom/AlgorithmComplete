package org.yousharp.problems.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.algorithm.datastructure.TreeNode;

/**
 * 问题描述：
 * 求二叉树的镜像
 * User: Daniel
 * Date: 13-12-22
 * Time: 上午11:29
 */
public class MirrorOfBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(MirrorOfBinaryTree.class);

	/**
	 * get the mirror of a binary tree
	 * we should reverse the left and right child of each node
	 *
	 * @param head
	 */
	private void getMirrorOfTree(TreeNode head) {
		if (null == head) {
			return;
		}

		TreeNode leftBak = head.left;
		head.left = head.right;
		head.right = leftBak;

		getMirrorOfTree(head.left);
		getMirrorOfTree(head.right);
	}


	public static void main(String[] args) {
		TreeNode head = new TreeNode(8);
		head.left = new TreeNode(6);
		head.right = new TreeNode(10);
		head.left.left = new TreeNode(5);
		head.left.right = new TreeNode(7);
		head.right.left = new TreeNode(9);
		head.right.right = new TreeNode(11);

		MirrorOfBinaryTree mirrorOfTree = new MirrorOfBinaryTree();
		TraverseBinaryTreeByTier tier = new TraverseBinaryTreeByTier();

		tier.traverseBinaryTreeByTier(head);
		mirrorOfTree.getMirrorOfTree(head);
		tier.traverseBinaryTreeByTier(head);
	}
}
