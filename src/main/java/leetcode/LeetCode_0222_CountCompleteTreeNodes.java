// Given a complete binary tree, count the number of nodes.

// Note:

// Definition of a complete binary tree from Wikipedia:
// In a complete binary tree every level, except possibly the last, 
// is completely filled, and all nodes in the last level are as far left as possible. 
// It can have between 1 and 2h nodes inclusive at the last level h.

// Example:

// Input: 
//     1
//    / \
//   2   3
//  / \  /
// 4  5 6

// Output: 6
package leetcode;


// 完全二叉树节点的个数 ，要求复杂度低于O(N)
// tips:
// 求总深度，左边扎到最深
// 右树的最左节点可以扎到最深处，则左树一定是满的
// 右树的最左节点不能扎到最深处，则右树一定是满的 
// O(h^2) -> O((logN)^2)
public class LeetCode_0222_CountCompleteTreeNodes {
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
    // TODO
    public static int countNodes(TreeNode head) {
        return -1;
	}
   

    
}