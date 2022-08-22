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
package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode_0662_MaximumWidthOfBinaryTree {

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 1;
        Queue<AnnotateNode> queue = new LinkedList<>();
        queue.offer(new AnnotateNode(root, 0, 0));
        int curDepth = 0;
        int left = 0;
        while (!queue.isEmpty()) {
            AnnotateNode node = queue.poll();
            if (node.node != null) {
                // 当作一个满二叉树来对待
                // 所以一个节点的左孩子等于2 * i
                // 一个节点右孩子的位置是：2*i+1
                queue.offer(new AnnotateNode(node.node.left, node.depth + 1, node.pos * 2));
                queue.offer(new AnnotateNode(node.node.right, node.depth + 1, node.pos * 2 + 1));
                if (curDepth != node.depth) {
                    // 下一层开始结算上一层的结果
                    curDepth = node.depth;
                    left = node.pos;
                }
                max = Math.max(max, node.pos - left + 1);
            }
        }
        return max;
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
