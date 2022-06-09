package leetcode.medium;


// 完全二叉树节点的个数 ，要求复杂度低于O(N)
// tips:
// 求总深度，左边扎到最深
// 右树的最左节点可以扎到最深处，则左树一定是满的
// 右树的最左节点不能扎到最深处，则右树一定是满的 
// O(h^2) -> O((logN)^2)
// https://leetcode.cn/problems/count-complete-tree-nodes/
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
        // 收集左右子树节点个数加上头节点，就是整棵树的节点个数
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