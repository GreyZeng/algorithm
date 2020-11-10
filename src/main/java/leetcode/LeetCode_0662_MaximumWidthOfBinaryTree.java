/*Given a binary tree, write a function to get the maximum width of the given tree. The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 

Constraints:

The given binary tree will have between 1 and 3000 nodes.*/
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode_0662_MaximumWidthOfBinaryTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public int widthOfBinaryTree(TreeNode root) {
		Queue<AnnotateNode> queue = new LinkedList<>();
		queue.offer(new AnnotateNode(root, 0, 0));
		int currdepth = 0;
		int left = 0;
		int res = 0;
		while (!queue.isEmpty()) {
			AnnotateNode a = queue.poll();
			if (a.node != null) {
				queue.offer(new AnnotateNode(a.node.left, a.depth + 1, a.pos * 2));
				queue.offer(new AnnotateNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
				if (currdepth != a.depth) {
					currdepth = a.depth;
					left = a.pos;
				}
				res = Math.max(res, a.pos - left + 1);
			}

		}

		return res;
	}

	static class AnnotateNode {
		TreeNode node;
		int depth;
		int pos;

		public AnnotateNode(TreeNode node, int depth, int pos) {
			this.node = node;
			this.depth = depth;
			this.pos = pos;
		}
	}
}
