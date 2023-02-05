package 练习题.leetcode.medium;

// 给定一个二叉树的root，返回最长的路径的长度 ，这个路径中的每个节点具有相同值。
// 这条路径可以经过也可以不经过根节点。
//
// 两个节点之间的路径长度由它们之间的边数表示。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/longest-univalue-path
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0687_LongestUnivaluePath {
    public static class TreeNode {
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

    public static int longestUnivaluePath(TreeNode root) {
        return p(root).max;
    }

    public static Info p(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new Info(0, 0);
        }
        if (root.left == null) {
            // root.right != null
            Info right = p(root.right);
            int len = 0;
            if (root.val == root.right.val) {
                len = (right.len + 1);
            }
            int max = Math.max(right.max, Math.max(len, right.len));
            return new Info(max, len);
        }
        if (root.right == null) {
            Info left = p(root.left);
            int len = 0;
            if (root.val == root.left.val) {
                len = (left.len + 1);
            }
            int max = Math.max(left.max, Math.max(len, left.len));
            return new Info(max, len);
        }
        Info left = p(root.left);
        Info right = p(root.right);

        if (root.val == root.left.val && root.val == root.right.val) {
            int len = Math.max(left.len, right.len) + 1;
            int max = Math.max(Math.max(left.max, right.max), (left.len + right.len + 2));
            return new Info(max, len);
        }
        if (root.val == root.right.val) {
            int len = right.len + 1;
            int max = Math.max(len, Math.max(right.max, left.max));
            return new Info(max, len);
        }
        if (root.val == root.left.val) {
            int len = left.len + 1;
            int max = Math.max(len, Math.max(left.max, right.max));
            return new Info(max, len);
        }
        return new Info(Math.max(Math.max(left.max, right.max), Math.max(left.len, right.len)), 0);
    }

    public static class Info {
        // 全局最大值
        public int max;
        // 头节点左右可以扎到多深
        public int len;

        public Info(int m, int n) {
            max = m;
            len = n;
        }
    }
}
