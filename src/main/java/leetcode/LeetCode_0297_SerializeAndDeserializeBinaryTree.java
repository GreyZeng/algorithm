package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 序列化和反序列化二叉树
public class LeetCode_0297_SerializeAndDeserializeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (null == root) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> ans = new LinkedList<>();
        queue.offer(root);
        int size = 0; // 用于记录结果，也用于最后生成字符串判断最后一个数后面要加逗号
        while (!queue.isEmpty()) {
            TreeNode r = queue.poll();
            if (r != null) {
                ans.offer(r);
                queue.offer(r.left == null ? null : r.left);
                queue.offer(r.right == null ? null : r.right);
            } else {
                ans.offer(null);
            }
            size++;
        }
        // 处理后面无效的null值
        while (!ans.isEmpty() && ans.peekLast() == null) {
            ans.pollLast();
            size--;
        }
        if (size == 0) {
            return "[]";
        }
        for (TreeNode n : ans) {
            builder.append(n == null ? "null" : n.val);
            size--;
            if (size != 0) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if ("[]".equals(data)) {
            return null;
        }
        int index = 0;
        String[] valid = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(valid[index++]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int N = valid.length;
        while (!queue.isEmpty() && index < N) {
            TreeNode node = queue.poll();
            node.left = "null".equals(valid[index]) ? null : new TreeNode(Integer.parseInt(valid[index]));
            index++;
            if (index < N) {
                node.right = "null".equals(valid[index]) ? null : new TreeNode(Integer.parseInt(valid[index]));
            }
            index++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return root;

    }

    public static void main(String[] args) {
        String str = "[1,2,3,null,null,4,5]";
        System.out.println(serialize(deserialize(str)));
    }

}
