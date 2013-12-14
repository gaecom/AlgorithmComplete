package org.algorithm.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重建二叉树：根据二叉树的前序和中序遍历序列，重建二叉树，并输出二叉树的后序遍历序列。
 * User: Daniel
 * Date: 13-12-9
 * Time: 下午10:51
 */
public class RebuiltBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(RebuiltBinaryTree.class);

	// pre order and in order sequence
	private static ArrayList<Integer> preOrder = new ArrayList<Integer>();
	private static ArrayList<Integer> inOrder = new ArrayList<Integer>();

	/**
	 * rebuilt binary tree recursively
	 * @param preOrderStart start index of the pre order sequence
	 * @param preOrderEnd   end index of the pre order sequence
	 * @param inOrderStart  start index of the in order sequence
	 * @param inOrderEnd    end index of the in order sequence
	 * @return
	 */
	private static TreeNode rebuiltBinaryTree(int preOrderStart, int preOrderEnd, int inOrderStart, int inOrderEnd) {
		// the first value of the pre order sequence is the root
		int value = preOrder.get(preOrderStart);
		TreeNode currentRoot = new TreeNode(value);

		// found the last node
		if ((preOrderStart == preOrderEnd))
			if ((inOrderStart == inOrderEnd) && preOrder.get(preOrderStart) == inOrder.get(inOrderStart)) {
				return currentRoot;
			} else {
				throw new UnsupportedOperationException("invalid input");
		}

		// find the root position in [in order] sequence, then the left part is the left child,
		// and the right part is the right child
		int index = inOrder.indexOf(value);
		if (-1 == index) {
			logger.info("error input");
			throw new UnsupportedOperationException("error input");
		}

		int leftLength = index - inOrderStart;
		// the left child is not empty, then rebuilt it recursively
		if (leftLength > 0) {
			currentRoot.left = rebuiltBinaryTree(preOrderStart + 1, preOrderStart + leftLength,
					inOrderStart, index - 1);
		}
		// the right child is not empty, the rebuilt it recursively
		if (index < inOrderEnd) {
			currentRoot.right = rebuiltBinaryTree(preOrderStart + leftLength + 1, preOrderEnd,
					index + 1, inOrderEnd);
		}
		return currentRoot;
	}

	/**
	 * post order sequence
	 * @param root
	 */
	private static void postOrderTraverse(TreeNode root) {
		if (null == root) {
			return;
		}
		postOrderTraverse(root.left);
		postOrderTraverse(root.right);
		logger.info("{} ", root.value);
	}

	public static void main(String[] args) {
		Integer[] preOrderArray = {1, 2, 4, 7, 3, 5, 6, 8};
		Integer[] inOrderArray = {4, 7, 2, 1, 5, 3, 8 , 6};
		preOrder.addAll(Arrays.asList(preOrderArray));
		inOrder.addAll(Arrays.asList(inOrderArray));
		int size = preOrder.size();
		TreeNode root = rebuiltBinaryTree(0, size - 1, 0, size - 1);
		logger.info("tree root: {}", root.value);
		postOrderTraverse(root);
	}
}

/**
 * tree node
 */
 class TreeNode {
	 int value;
	 TreeNode left;
	 TreeNode right;

	 public TreeNode(int value) {
		 this.value = value;
		 this.left = null;
		 this.right = null;
	 }
 }