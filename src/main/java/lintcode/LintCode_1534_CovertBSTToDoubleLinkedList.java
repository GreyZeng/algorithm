/*双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是right的话。
        给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。

        tips:
        case1 中序遍历
        case2 二叉树的递归套路
        case3 morris遍历*/
package lintcode;

// https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/description
public class LintCode_1534_CovertBSTToDoubleLinkedList {
    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        // Write your code here.
        return null;
    }
}
