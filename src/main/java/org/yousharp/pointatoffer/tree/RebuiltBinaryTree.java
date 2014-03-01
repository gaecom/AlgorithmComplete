package org.yousharp.pointatoffer.tree;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.TreeNode;

/**
 * 重建二叉树：根据二叉树的前序和中序遍历序列，重建二叉树，并输出二叉树的后序遍历序列。
 * User: Daniel
 * Date: 13-12-9
 * Time: 下午10:51
 */
public class RebuiltBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(RebuiltBinaryTree.class);

	// global status
	private static boolean status = true;

	/**
	 * rebuilt binary tree recursively
	 * @param preOrder  the pre order sequence of the binary tree
	 * @param preStart  the start index of the pre order sequence
	 * @param preEnd    the end index of the pre order sequence
	 * @param inOrder   the post order sequence of the binary tree
	 * @param inStart   the start index of the in order sequence
	 * @param inEnd     the end index of the in order sequence
	 * @return
	 */
	public static TreeNode rebuilt(int[] preOrder, int preStart, int preEnd,
	                           int[] inOrder, int inStart, int inEnd) {
		// the root
		TreeNode root = new TreeNode(preOrder[preStart]);

		if ((preStart == preEnd) && (inStart == inEnd)) {
			if (preOrder[preStart] == inOrder[inStart]) {
				return root;
			} else {
				logger.info("error input");
				status = false;
				return null;
			}
		}

		int index = indexOf(inOrder, preOrder[preStart]);
		if (index == -1) {
			logger.info("error input");
			status = false;
			return null;
		}
		int leftLength = index - inStart;
		if (index > inStart) {
			root.left = rebuilt(preOrder, preStart+1, preStart+leftLength,
					inOrder, inStart, inStart+leftLength-1);
		}
		if (index < inEnd) {
			root.right = rebuilt(preOrder, preStart+leftLength+1, preEnd,
					inOrder, inStart+leftLength+1, inEnd);
		}

		return root;
	}

	/**
	 * return the index of an element in an array
	 * @param data  the array to search
	 * @param key   the key to search for
	 * @return
	 */
	public static int indexOf(int[] data, int key) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * post order sequence of the binary tree
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

	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
		int[] in = {4, 7, 2, 1, 5, 3, 8, 8};

		TreeNode r = rebuilt(pre, 0, pre.length-1, in, 0, in.length-1);
		if (status) {
			postOrderTraverse(r);
		} else{
			logger.info("input error, rebuilt failed.");
		}
	}
}
/**
 * 思路：前序遍历的第一个节点总是二叉树的根节点，在中序遍历中查找该节点，左半部分为二叉树的
 * 左子树，右半部分为右子树，递归即可构建二叉树；可使用图来辅助理解和实现。
 */