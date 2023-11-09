package git.snippet.tree;

import java.util.ArrayList;
import java.util.List;

// 笔记：https://www.cnblogs.com/greyzeng/p/16758370.html
// leetcode 431
// https://www.lintcode.com/problem/1530/
public class LintCode_1530_EncodeNaryTreeToBinaryTree {
    // 解码
    public UndirectedGraphNode decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        UndirectedGraphNode node = new UndirectedGraphNode(root.val);
        node.neighbors = de(root.right);
        return node;
    }

    public ArrayList<UndirectedGraphNode> de(TreeNode root) {
        ArrayList<UndirectedGraphNode> children = new ArrayList<>();
        while (root != null) {
            UndirectedGraphNode cur = new UndirectedGraphNode(root.val);
            cur.neighbors = de(root.right);
            children.add(cur);
            root = root.left;
        }
        return children;
    }

    // 每个子节点转换成自己左树的右边界或者右树的左边界 + 深度优先遍历
    // 编码
    public TreeNode encode(UndirectedGraphNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.label);
        // 右树的左边界
        head.right = en(root.neighbors);
        return head;
    }

    private TreeNode en(List<UndirectedGraphNode> neighbors) {
        TreeNode c = null;
        TreeNode head = null;
        for (UndirectedGraphNode neighbor : neighbors) {
            TreeNode node = new TreeNode(neighbor.label);
            if (head == null) {
                // 头节点为空，建出来
                head = node;
            } else {
                // 否则挂在当前节点的右树的左边界上
                c.left = node;
            }
            c = node;
            c.right = en(neighbor.neighbors);
        }
        return head;
    }

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
