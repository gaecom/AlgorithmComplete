package org.yousharp.problems.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.algorithm.datastructure.TreeNode;

/**
 * 问题描述：
 * 两个二叉树，判断二叉树A是否包含二叉树B，
 * 即二叉树B是二叉树A的子结构。
 * Uer: Daniel
 * Date: 13-12-22
 * Time: 下午1:59
 */
public class SubBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(SubBinaryTree.class);

	/**
	 * check if tree second is the subtree of tree first
	 * first of all, we should find a node that is identical to the root node of tree second.
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	private boolean isSubBinaryTree(TreeNode first, TreeNode second) {
		// if any one of them is null, no sub tree
		if (null == first || null == second) {
			return false;
		}

		boolean result = false;
		// ok, we find a node in tree first, and we should check if it contains tree second
		if (first.value == second.value) {
			result = isContain(first, second);
		}

		// check failed, go left side
		if (false == result) {
			result = isSubBinaryTree(first.left, second);
		}

		// check faild, go right side
		if (false == result) {
			result = isSubBinaryTree(first.right, second);
		}

		return result;
	}

	/**
	 * check if tree first contains tree second
	 * if any paired nodes are not equal, return false
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	private boolean isContain(TreeNode first, TreeNode second) {
		if (null == second) {
			return true;
		}
		if (null == first) {
			return false;
		}

		boolean result = false;
		if (first.value == second.value) {
			result = (isContain(first.left, second.left) && isContain(first.right, second.right));
		}

		return result;
	}


	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode first = new TreeNode(8);
		first.left = new TreeNode(8);
		first.right = new TreeNode(7);
		first.left.left = new TreeNode(9);
		first.left.right = new TreeNode(2);
		first.left.right.left = new TreeNode(4);
		first.left.right.right = new TreeNode(7);

		TreeNode second = new TreeNode(8);
		second.left = new TreeNode(8);
		second.right = new TreeNode(2);

		SubBinaryTree sub = new SubBinaryTree();
		logger.info("is sub tree: {}", sub.isSubBinaryTree(first, second));
	}
}

