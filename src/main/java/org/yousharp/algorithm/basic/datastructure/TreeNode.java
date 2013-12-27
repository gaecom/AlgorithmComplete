package org.yousharp.algorithm.datastructure;

/**
 * node of a tree
 * left: reference to left child
 * right reference to right child
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午8:19
 */
public class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}
