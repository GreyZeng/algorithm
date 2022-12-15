/*Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

        Example 1:
        Given tree s:

        3
        / \
        4   5
        / \
        1   2
        Given tree t:
        4
        / \
        1   2
        Return true, because t has the same structure and node values with a subtree of s.


        Example 2:
        Given tree s:

        3
        / \
        4   5
        / \
        1   2
        /
        0
        Given tree t:
        4
        / \
        1   2
        Return false.*/
package 练习题.leetcode.easy;

import java.util.ArrayList;

// 使用kmp算法来解：https://www.cnblogs.com/greyzeng/p/15317466.html
public class LeetCode_0572_SubtreeOfAnotherTree {
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


    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        ArrayList<Integer> origin = new ArrayList<>();
        pre(s, origin);
        ArrayList<Integer> target = new ArrayList<>();
        pre(t, target);
        if (origin.size() < target.size()) {
            return false;
        }
        String[] oStrs = new String[origin.size()];
        String[] tStrs = new String[target.size()];
        for (int i = 0; i < oStrs.length; i++) {
            oStrs[i] = String.valueOf(origin.get(i));
        }
        for (int i = 0; i < tStrs.length; i++) {
            tStrs[i] = String.valueOf(target.get(i));
        }
        return indexOf(oStrs, tStrs) != -1;
    }

    // 先序遍历
    private static void pre(TreeNode head, ArrayList<Integer> list) {
        if (head == null) {
            list.add(null);
            return;
        }
        list.add(head.val);
        pre(head.left, list);
        pre(head.right, list);
    }


    // kmp算法判断字数
    private static int indexOf(String[] s, String[] t) {
        if (s == null || t == null || s.length < t.length) {
            return -1;
        }
        if (t.length == 0) {
            return 0;
        }
        int[] next = getNextArr(t);
        int x = 0;
        int y = 0;
        while (x < s.length && y < t.length) {
            if (s[x].equals(t[y]) || (s[x] == null && t[x] == null)) {
                x++;
                y++;
            } else if (y != 0) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == t.length ? x - y : -1;
    }

    private static int[] getNextArr(String[] m) {
        if (m.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[m.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < m.length) {
            if (m[i - 1].equals(m[cn]) || (m[i - 1] == null && m[cn] == null)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

}