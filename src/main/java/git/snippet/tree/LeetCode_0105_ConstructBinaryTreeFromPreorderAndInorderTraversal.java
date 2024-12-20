package git.snippet.tree;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// 笔记：https://www.cnblogs.com/greyzeng/p/16715432.html
public class LeetCode_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int L = inorder.length - 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i <= L; i++) {
            m.put(inorder[i], i);
        }
        return f(preorder, 0, L, inorder, 0, L, m);
    }

    private TreeNode f(int[] preorder, int L1, int R1, int[] inorder, int L2, int R2, Map<Integer, Integer> m) {
        if (L1 > R1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[L1]);
        if (L1 == R1) {
            return root;
        }
        int index = m.get(preorder[L1]);
        root.left = f(preorder, L1 + 1, index - L2 + L1, inorder, L2, index - 1, m);
        root.right = f(preorder, index - L2 + L1 + 1, R1, inorder, index + 1, R2, m);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
