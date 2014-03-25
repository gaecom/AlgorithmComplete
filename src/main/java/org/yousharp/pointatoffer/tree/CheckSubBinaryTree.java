package org.yousharp.pointatoffer.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.TreeNode;

/**
 * 问题描述：
 * 两个二叉树，判断二叉树A是否包含二叉树B，
 * 即二叉树B是二叉树A的子结构。
 * Uer: Daniel
 * Date: 13-12-22
 * Time: 下午1:59
 */
public class CheckSubBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(CheckSubBinaryTree.class);

	/**
	 * 1. traverse the parent tree, find a sub root that is identical to the root of the child tree;
	 * 2. compare recursively to check if the parent tree contains the child tree.
	 *
	 * @param rootOfParent  the root of the parent tree
	 * @param rootOfChild   the root of the child tree
	 * @return  if the child tree is the sub-tree of the parent tree: true or false
	 */
	private boolean check(TreeNode rootOfParent, TreeNode rootOfChild) {
		// if any one of them is null, no sub tree
		if (null == rootOfParent || null == rootOfChild) {
			return false;
		}

		boolean result = false;
		// check if sub-tree is valid
		if (rootOfParent.value == rootOfChild.value) {
			result = isValid(rootOfParent, rootOfChild);
		}
		// check failed, go left side
		if (false == result) {
			result = check(rootOfParent.left, rootOfChild);
		}
		// check failed, go right side
		if (false == result) {
			result = check(rootOfParent.right, rootOfChild);
		}
		return result;
	}

	/**
	 * check if the parent tree contains the child tree by recursion
	 *
	 * @param nodeOfParent
	 * @param nodeOfChild
	 * @return
	 */
	private boolean isValid(TreeNode nodeOfParent, TreeNode nodeOfChild) {
		// child node is null, ok
		if (null == nodeOfChild) {
			return true;
		}
		// child node is not null, parent node is null, wrong
		if (null == nodeOfParent) {
			return false;
		}

		// current node is ok, compare left and right children recursively
		boolean result = false;
		if (nodeOfParent.value == nodeOfChild.value) {
			result = (isValid(nodeOfParent.left, nodeOfChild.left) && isValid(nodeOfParent.right, nodeOfChild.right));
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

		CheckSubBinaryTree sub = new CheckSubBinaryTree();
		logger.info("is sub tree: {}", sub.check(first, second));
	}
}

