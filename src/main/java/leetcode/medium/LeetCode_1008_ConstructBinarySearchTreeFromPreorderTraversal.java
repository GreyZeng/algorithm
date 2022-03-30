package leetcode.medium;

//已知一棵搜索二叉树上没有重复值的节点，
//        现在有一个数组arr，是这棵搜索二叉树先序遍历的结果
//        请根据arr生成整棵树并返回头节点
// https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class LeetCode_1008_ConstructBinarySearchTreeFromPreorderTraversal {

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
}
