package git.snippet.leetcode;

import java.util.Stack;

// 已知一棵搜索二叉树上没有重复值的节点，
// 现在有一个数组arr，是这棵搜索二叉树先序遍历的结果
// 请根据arr生成整棵树并返回头节点
// https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class LeetCode_1008_ConstructBinarySearchTreeFromPreorderTraversal {

    public TreeNode bstFromPreorder(int[] preorder) {
        return f(preorder, 0, preorder.length - 1);
    }

    public TreeNode f(int[] arr, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return new TreeNode(arr[i]);
        }
        TreeNode root = new TreeNode(arr[i]);
        int moreNum = -1;
        for (int index = i + 1; index <= j; index++) {
            if (arr[index] > arr[i]) {
                moreNum = index;
                break;
            }
        }
        if (moreNum == -1) {
            root.left = f(arr, i + 1, j);
            return root;
        }
        if (moreNum == i + 1) {
            root.right = f(arr, i + 1, j);
            return root;
        }
        root.left = f(arr, i + 1, moreNum - 1);
        root.right = f(arr, moreNum, j);
        return root;
    }

    // 单调栈优化
    public TreeNode bstFromPreorder2(int[] preorder) {
        int[] mono = monoStack(preorder);
        return f(preorder, 0, preorder.length - 1, mono);
    }

    private TreeNode f(int[] arr, int i, int j, int[] mono) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return new TreeNode(arr[i]);
        }
        TreeNode root = new TreeNode(arr[i]);
        int moreNum = mono[i];
        if (moreNum == -1) {
            root.left = f(arr, i + 1, j, mono);
            return root;
        }
        if (moreNum == i + 1) {
            root.right = f(arr, i + 1, j, mono);
            return root;
        }
        root.left = f(arr, i + 1, moreNum - 1, mono);
        root.right = f(arr, moreNum, j, mono);
        return root;
    }

    private int[] monoStack(int[] arr) {
        int[] mono = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int pop = stack.pop();
                mono[pop] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            mono[stack.pop()] = -1;
        }
        return mono;
    }

    // 最优解，用数组替代stack结构，可以节省常数时间
    public TreeNode bstFromPreorder3(int[] preorder) {
        int[] mono = monoStack3(preorder);
        return f(preorder, 0, preorder.length - 1, mono);
    }

    private int[] monoStack3(int[] arr) {
        int[] mono = new int[arr.length];
        int[] stack = new int[mono.length];
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            while (size != 0 && arr[stack[size - 1]] < arr[i]) {
                int pop = stack[--size];
                mono[pop] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            mono[stack[--size]] = -1;
        }
        return mono;
    }

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
}
