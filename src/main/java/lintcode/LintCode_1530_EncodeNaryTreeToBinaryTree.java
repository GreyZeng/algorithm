package lintcode;

import java.util.ArrayList;
import java.util.List;
// leetcode 431
// https://www.lintcode.com/problem/1530/
// tips: 每个子节点转换成自己左树的右边界 + 深度优先遍历
public class LintCode_1530_EncodeNaryTreeToBinaryTree {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public UndirectedGraphNode decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        UndirectedGraphNode node = new UndirectedGraphNode(root.val);
        node.neighbors = de(root.left);
        return node;
    }

    public ArrayList<UndirectedGraphNode> de(TreeNode root) {
        ArrayList<UndirectedGraphNode> children = new ArrayList<>();
        while (root != null) {
            UndirectedGraphNode cur = new UndirectedGraphNode(root.val);
            cur.neighbors = de(root.left);
            children.add(cur);
            root = root.right;
        }
        return children;
    }

    /**
     * @param root: N-ary tree
     * @return: binary tree
     */
    public TreeNode encode(UndirectedGraphNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.label);
        head.left = en(root.neighbors);
        return head;
    }

    private TreeNode en(List<UndirectedGraphNode> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (UndirectedGraphNode child : children) {
            TreeNode tNode = new TreeNode(child.label);
            if (head == null) {
                head = tNode;
            } else {
                cur.right = tNode;
            }
            cur = tNode;
            cur.left = en(child.neighbors);
        }
        return head;
    }
}
