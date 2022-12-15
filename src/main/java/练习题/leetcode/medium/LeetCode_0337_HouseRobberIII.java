//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
//
//        除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
//        聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
//
//        给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode.cn/problems/house-robber-iii
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package 练习题.leetcode.medium;

// 类似员工最大快乐值问题
//https://leetcode.cn/problems/house-robber-iii/
// 笔记：https://www.cnblogs.com/greyzeng/p/16494637.html
public class LeetCode_0337_HouseRobberIII {
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

    public int rob(TreeNode root) {
        Info info = p(root);
        return Math.max(info.yes, info.no);
    }

    public class Info {
        // 选头节点
        public int yes;
        // 不选头节点
        public int no;

        public Info(int y, int n) {
            yes = y;
            no = n;
        }
    }

    public Info p(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info left = p(root.left);
        Info right = p(root.right);
        int yes = root.val + left.no + right.no;
        int no = Math.max(left.yes, left.no) + Math.max(right.yes, right.no);
        return new Info(yes, no);
    }
}
