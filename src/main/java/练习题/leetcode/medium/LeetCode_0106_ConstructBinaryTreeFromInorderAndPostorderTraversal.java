package 练习题.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static TreeNode buildTree(int[] inorder, int[] postorder) {
    if (null == postorder || inorder == null || postorder.length != inorder.length) {
      return null;
    }
    int L = inorder.length - 1;
    Map<Integer, Integer> m = new HashMap<>();
    for (int i = 0; i <= L; i++) {
      m.put(inorder[i], i);
    }
    return f(inorder, 0, L, postorder, 0, L, m);
  }

  private static TreeNode f(int[] inorder, int L1, int R1, int[] postorder, int L2, int R2,
      Map<Integer, Integer> m) {
    if (L2 > R2) {
      return null;
    }
    TreeNode root = new TreeNode(postorder[R2]);
    if (L2 == R2) {
      return root;
    }
    int index = m.get(postorder[R2]);
    root.left = f(inorder, L1, index - 1, postorder, L2, L2 + index - L1 - 1, m);
    root.right = f(inorder, index + 1, R1, postorder, L2 + index - L1, R2 - 1, m);
    return root;
  }

}
