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
package leetcode.medium;


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
    }

    // O((logN)^2)
    public static int countNodes(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = maxLenOfLeft(head, 1);
        return count(head, 1, h);
    }

    private static int count(TreeNode head, int level, int h) {
        if (level == h) {
            return 1;
        }
        if (maxLenOfLeft(head.right, level + 1) == h) {
            // 左树一定是满的
            return (1 << (h - level)) + count(head.right, level + 1, h);
        } else {
            // 右数一定是满的，注意高度是 h - level - 1
            return (1 << (h - level - 1)) + count(head.left, level + 1, h);
        }
    }

    public static int maxLenOfLeft(TreeNode root, int level) {
        while (root != null) {
            root = root.left;
            level++;
        }
        return level - 1;
    }

    // O(N)解法
    public static int countNodes1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return p(head).all;
    }

    public static Info p(TreeNode head) {
        if (head == null) {
            return new Info(0);
        }
        Info leftInfo = p(head.left);
        Info rightInfo = p(head.right);
        int all = leftInfo.all + rightInfo.all + 1;
        return new Info(all);
    }

    public static class Info {
        public int all;

        public Info(int a) {
            all = a;
        }
    }
}