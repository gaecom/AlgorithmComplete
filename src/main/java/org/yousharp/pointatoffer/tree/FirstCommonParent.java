package org.yousharp.pointatoffer.tree;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.TreeNode;

/**
 * find the first common parent node of given two nodes in a binary tree.
 * User: Daniel
 * Date: 14-1-12
 * Time: 下午8:28
 */
public class FirstCommonParent {
	private static Logger logger = LoggerFactory.getLogger(FirstCommonParent.class);

	/**
	 * if the tree is a binary search tree, find the first common parent
	 * @param root  root node of the tree
	 * @param first the first node
	 * @param second    the second node
	 * @return
	 */
	public TreeNode findFirstCommonInBinarySearchTree(TreeNode root, TreeNode first, TreeNode second) {
		// check params
		if (null == root || null == first || null == second) {
			return null;
		}

		while (null != root) {
			if (first.value < second.value) {
				// got it
				if ((root.value < second.value) && (root.value > first.value)) {
					return root;
				} else if (root.value > second.value) {     // find in the right branch
					root = root.left;
				} else if (root.value < first.value) {        // find in the left branch
					root = root.right;
				} else {
					return null;    // root equals to either of the nodes, illegal
				}
			} else if (first.value > second.value) {
				if ((root.value > second.value) && (root.value < first.value)) {    // got it
					return root;
				} else if (root.value < second.value) {     // find in the left branch
					root = root.right;
				} else if (root.value > first.value) {      // find in the right branch
					root = root.left;
				} else {    // illegal
					return null;
				}
			} else {        // illegal
				return null;
			}
		}
		return null;    // not found
	}

	/**
	 * if it is an ordinary binary tree, we first find out the path from the root to the
	 * two leaf node, then find the last common node of the two paths.
	 * @param root  the root of the binary tree
	 * @param first the first leaf node
	 * @param second    the second leaf node
	 * @return
	 */
	public TreeNode findFirstCommonParentInOrdinaryTree(TreeNode root, TreeNode first, TreeNode second) {
		if (null == root || null == first || null == second) {
			return null;
		}

		// paths to the first and second leaf from root
		LinkedList<TreeNode> firstPath = new LinkedList<TreeNode>();
		LinkedList<TreeNode> secondPath = new LinkedList<TreeNode>();

		// get paths
		boolean isLeftFound = getTreePath(root, first, firstPath);
		boolean isRightFound = getTreePath(root, second, secondPath);

		// if two path are found, find out the last common node
		if (isLeftFound && isRightFound) {
			for (int i = 0; i < firstPath.size() && i < secondPath.size(); i++) {
				if (firstPath.get(i) != secondPath.get(i)) {
					return firstPath.get(i-1);
				}
			}
		}
		return null;
	}

	/**
	 * in a binary tree, find the path from the root to a given leaf
	 * @param root  the root of the binary tree
	 * @param leaf  the leaf
	 * @param path  the path
	 * @return
	 */
	public boolean getTreePath(TreeNode root, TreeNode leaf, LinkedList<TreeNode> path) {
		// not found
		if (null == root) {
			return  false;
		}
		// found
		if (leaf == root) {
			return true;
		}

		// add the node to path
		boolean isFound = false;
		path.add(root);

		// find in children
		isFound = getTreePath(root.left, leaf, path);
		if (!isFound) {
			isFound = getTreePath(root.right, leaf, path);
		}

		// there is no path from the current node to the leaf
		if (!isFound) {
			path.remove(root);
		}
		return isFound;
	}


	public static void main(String[] args) {
		TreeNode root = new TreeNode(15);
		root.left = new TreeNode(10);
		root.right = new TreeNode(33);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(12);
		TreeNode first = root.left.left.left = new TreeNode(4);
		root.left.left.right = new TreeNode(8);
		root.left.right.left = new TreeNode(11);
		TreeNode second = root.left.right.right = new TreeNode(14);

		FirstCommonParent commonParent = new FirstCommonParent();
		TreeNode parentOne = commonParent.findFirstCommonInBinarySearchTree(root, first, second);
		TreeNode parentTwo = commonParent.findFirstCommonParentInOrdinaryTree(root, first, second);
		if (null != parentOne) {
			logger.info("parentOne: {}", parentOne.value);
		}
		if (null != parentTwo) {
			logger.info("parentTwo: {}", parentTwo.value);
		}
		return;
	}
}

/**
 *  思路：求树的任意两个节点的最低公共祖先，树的特点不同，具体的解法也不相同：
 *  1. 如果是二叉搜索树，找到一个节点N，如果两个节点分别为该节点N的左右孩子，则该节点N为两个节点的最低公共祖先。
 *  2.  如果是非二叉搜索树，但每一个节点都有指向父节点的指针，则这两个节点到根节点就是两个链表，则问题转化为求
 *       两个单链表的第一个公共节点。
 *  3.  如果知识普通的二叉树，则可以先求得根节点到着两个节点的路径，问题转化为求这两条路径的最后一个公共节点。
 *  4.  如果不是二叉树，而只是普通的树结构（多个孩子），同样使用情形3的思路，不同的是，在寻找路径的时候，需要遍历
 *       当前节点的所有孩子，而不是二叉树时的左右孩子。示例代码如：
 *       int i = 0;
 *       while (!isFound && i < root.childList.size()) {
 *           isFound = getTreePath(root.childList.get(i++);
 *       }
 *
 *
 */