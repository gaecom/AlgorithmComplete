package practice.algorithm.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Daniel
 * Date: 13-12-9
 * Time: 下午10:51
 */
public class RebuiltBinaryTree {
	private static Logger logger = LoggerFactory.getLogger(RebuiltBinaryTree.class);

//	private static Integer[] preOrderArray = {1, 2, 4, 7, 3, 5, 6, 8};
//	private static Integer[] inOrderArray = {4, 7, 2, 1, 5, 3, 8 , 6};
//	private static ArrayList<Integer> preOrder = new ArrayList<Integer>(Arrays.asList(preOrderArray));
//	private static ArrayList<Integer> inOrder = new ArrayList<Integer>(Arrays.asList(inOrderArray));
	private static ArrayList<Integer> preOrder = new ArrayList<Integer>();
	private static ArrayList<Integer> inOrder = new ArrayList<Integer>();

	private static TreeNode rebuiltBinaryTree(int preOrderStart, int preOrderEnd, int inOrderStart, int inOrderEnd) {
		int value = preOrder.get(preOrderStart);
		TreeNode currentRoot = new TreeNode(value);

		if ((preOrderStart == preOrderEnd) && (inOrderStart == inOrderEnd) &&
				preOrder.get(preOrderStart) == inOrder.get(inOrderStart)) {
			return currentRoot;
		}

		int index = inOrder.indexOf(value);
		if (-1 == index) {
			logger.info("error input");
			throw new UnsupportedOperationException("error input");
		}

		int leftLength = index - preOrderStart;
		if (leftLength > 0) {
			currentRoot.left = rebuiltBinaryTree(preOrderStart + 1, preOrderStart + leftLength,
					inOrderStart, inOrderStart + leftLength - 1);
		}
		if (inOrderEnd > index) {
			currentRoot.right = rebuiltBinaryTree(preOrderStart + leftLength + 1, preOrderEnd,
					index + 1, inOrderEnd);
		}
		return currentRoot;
	}

	static int i = 0;
	private static void postOrderTraverse(TreeNode root) {
		if (null == root) {
			return;
		}
		i++;
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
		logger.info("i: {}", i);
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