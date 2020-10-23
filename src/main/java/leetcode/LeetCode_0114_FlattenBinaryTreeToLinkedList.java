// Given a binary tree, flatten it to a linked list in-place.

// For example, given the following tree:

//     1
//    / \
//   2   5
//  / \   \
// 3   4   6
// The flattened tree should look like:

// 1
//  \
//   2
//    \
//     3
//      \
//       4
//        \
//         5
//          \
//           6
package leetcode;
 
import java.util.LinkedList; 

public class LeetCode_0114_FlattenBinaryTreeToLinkedList {
    public static class TreeNode {
        public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
    }
    // TODO
    // 省空间做法 利用morris遍历

    // 普通做法：先序遍历+List
    public static void flatten(TreeNode root) {
        if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
        LinkedList<TreeNode> list = new LinkedList<>();
        pre(list, root);
        TreeNode c = root;
        
        
        while (!list.isEmpty()) {
            c.right = list.pollFirst();
            c.left = null;
            if (!list.isEmpty()) {
                c = c.right;
            } else {
                break;
            }
        }
    }

     
    public static void pre(LinkedList<TreeNode> list, TreeNode root) {
        if (root == null) { 
            return;
        }
        
        list.addLast(root);
        pre(list, root.left);
        pre(list, root.right);
    }
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.left.left = new TreeNode(3);
		head.left.right = new TreeNode(4);
		head.right = new TreeNode(5);
		head.right.left = new TreeNode(6);
		head.right.right = new TreeNode(7);

		flatten(head);

		while (head != null) {
			System.out.println(head.val);
			System.out.println(head.left);
			head = head.right;
		}
    }
    
    
}
