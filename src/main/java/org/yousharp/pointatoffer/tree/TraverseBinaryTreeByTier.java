package org.yousharp.pointatoffer.tree;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yousharp.common.TreeNode;

/**
 * 分层遍历二叉树 * User: Daniel
 * Date: 13-12-22
 * Time: 下午12:26
 */
public class TraverseBinaryTreeByTier {
	private Logger logger = LoggerFactory.getLogger(TraverseBinaryTreeByTier.class);

	/**
	 * traverse a binary tree by tier
	 *
	 * @param head
	 */
	public void traverseBinaryTreeByTier(TreeNode head) {
		if (null == head) {
			return;
		}

		// the linked list is used as a queue
		LinkedList<TreeNode> treeNodes = new LinkedList<TreeNode>();
		treeNodes.add(head);
		while (!treeNodes.isEmpty()) {
			TreeNode node = treeNodes.pollFirst();
			logger.info("node: {}", node.value);
			if (null != node.left) {
				treeNodes.add(node.left);
			}
			if (null != node.right) {
				treeNodes.add(node.right);
			}
		}
	}
}
