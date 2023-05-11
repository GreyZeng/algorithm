package tree;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 笔记：https://www.cnblogs.com/greyzeng/p/16753978.html
 * https://www.nowcoder.com/questionTerminal/88331be6da0d40749b068586dc0a2a8b
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离(经过的节点数！）， 返回整棵二叉树的最大距离
 */
public class NowCoder_MaxDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] s = firstLine.split(" ");
        int n = Integer.valueOf(s[0]);
        int rootVal = Integer.valueOf(s[1]);
        HashMap<Integer, TreeNode> map = new HashMap<>();
        TreeNode root = new TreeNode();
        map.put(rootVal, root);

        // 构建二叉树
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] str = line.split(" ");
            int faVal = Integer.valueOf(str[0]);
            int lchVal = Integer.valueOf(str[1]);
            int rchVal = Integer.valueOf(str[2]);

            TreeNode curNode = map.get(faVal);

            if (lchVal != 0) {
                TreeNode lch = new TreeNode();
                curNode.left = lch;
                map.put(lchVal, lch);
            }
            if (rchVal != 0) {
                TreeNode rch = new TreeNode();
                curNode.right = rch;
                map.put(rchVal, rch);
            }
        }

        Info info = process(root);
        System.out.println(info.max);
        sc.close();
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int max = Math.max(left.maxHeight + right.maxHeight + 1, Math.max(left.max, right.max));
        int maxHeight = Math.max(left.maxHeight, right.maxHeight) + 1;
        return new Info(max, maxHeight);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private static class Info {
        int maxHeight;
        int max;

        public Info(int max, int maxHeight) {
            this.max = max;
            this.maxHeight = maxHeight;
        }
    }

}
