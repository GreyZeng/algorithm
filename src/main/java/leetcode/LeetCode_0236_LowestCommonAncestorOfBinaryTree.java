package leetcode;

public class LeetCode_0236_LowestCommonAncestorOfBinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
	    return process(head,o1,o2).ancestor;
	}
	public static Info process(TreeNode h, TreeNode o1, TreeNode o2) {
        if (h == null) {
            return new Info(false,false,null);
        }
        Info left = process(h.left, o1, o2);
        Info right = process(h.right, o1, o2);
        boolean f1 = left.findO1 || right.findO1 || h== o1;
        boolean f2 = right.findO2 || left.findO2 || h == o2;
       	TreeNode a = null;
        if (f1 && f2 ) {
       		if (left.findO1 && left.findO2) {
       			a = left.ancestor;
			} else  if (right.findO1 && right.findO2) {
       			a = right.ancestor;
			} else {
				a = h;
			}
	   }
        return new Info(f1,f2,a);
    }
	public static  class Info {
	    public boolean findO1;
	    public boolean findO2;
	    public TreeNode ancestor;
	    public Info(boolean f1, boolean f2, TreeNode a) {
	        findO1 = f1;
	        findO2 = f2;
	        ancestor = a;
        }
    }
}
