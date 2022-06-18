package leetcode.medium;


// https://leetcode.cn/problems/recover-binary-search-tree/
public class LeetCode_0099_RecoverBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // Morris遍历，最优解，空间复杂度O(1)
    // 中序遍历
    // 第一个错误节点：第一次降序的头节点
    // 第二个错误节点：第二次降序的尾节点
    // 这里只需要交换两个节点的val就可以AC，
    // 实际上更好的处理方式应该是节点真实的进行交换
    public static void recoverTree(TreeNode root) {
        if (null == root) {
            return;
        }
        TreeNode[] twoErrNodes = new TreeNode[2];
        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    if (pre != null) {
                        if (pre.val > cur.val) {
                            // 第一次降序的头节点
                            twoErrNodes[0] = twoErrNodes[0] == null ? pre : twoErrNodes[0];
                            // 第二次降序的尾节点
                            twoErrNodes[1] = cur;
                        }
                    }
                    pre = cur;
                    mostRight.right = null;
                }
            } else {
                if (pre != null) {
                    if (pre.val > cur.val) {
                        // 第一次降序的头节点
                        twoErrNodes[0] = twoErrNodes[0] == null ? pre : twoErrNodes[0];
                        // 第二次降序的尾节点
                        twoErrNodes[1] = cur;
                    }
                }
                pre = cur;
            }
            cur = cur.right;
        }
        twoErrNodes[0].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
        twoErrNodes[1].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
        twoErrNodes[0].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
    }

}
