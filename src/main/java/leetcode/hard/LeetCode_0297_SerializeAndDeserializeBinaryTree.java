package leetcode.hard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 笔记：https://www.cnblogs.com/greyzeng/p/16789819.html
// 序列化和反序列化二叉树
// 中序遍历无法序列化 比如：
//		1
//		  1
//		1
//---
//		1
//	  1
//		1
// 中序遍历的结果都是： [null,1,1,1,null]
// https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
public class LeetCode_0297_SerializeAndDeserializeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



    // 按层序列化
    // 空节点补充为#
    public static String serialize(TreeNode head) {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            sb.append(node == null ? "#" : String.valueOf(node.val)).append(",");
            if (node != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 按层反序列化
    public static TreeNode deserialize(String data) {
        if ("[]".equals(data)) {
            return null;
        }
        data = data.substring(1, data.length() - 2);
        String[] values = data.split(",");
        TreeNode head = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        int size = 1;
        while (!queue.isEmpty() && size < values.length) {
            TreeNode c = queue.poll();
            c.left = "#".equals(values[size]) ? null : new TreeNode(Integer.valueOf(values[size]));
            size++;
            if (size < values.length) {
                c.right = "#".equals(values[size]) ? null : new TreeNode(Integer.valueOf(values[size]));
                size++;
            }
            if (c.left != null) {
                queue.offer(c.left);
            }
            if (c.right != null) {
                queue.offer(c.right);
            }
        }
        return head;
    }

    // 后序方式序列化 迭代方法
    public static String serialize3(TreeNode head) {
        if (head == null) {
            return "[]";
        }
        // 后序遍历的结果加入栈（可以用递归也可以用迭代）
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode c = stack1.pop();
            stack2.push(c);
            if (c != null) {
                stack1.push(c.left);
                stack1.push(c.right);
            }
        }
        // 栈->字符串
        StringBuilder sb = new StringBuilder("[");
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            sb.append(node == null ? "#" : node.val).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    // 后序方式反序列化 迭代方式
    public static TreeNode deserialize3(String data) {
        if ("[]".equals(data)) {
            return null;
        }
        String[] values = data.substring(1, data.length() - 2).split(",");
        Stack<String> stack = new Stack<>();
        for (String value : values) {
            stack.push(value);
        }
        return posDerial(stack);
    }

    private static TreeNode posDerial(Stack<String> stack) {
        String s = stack.pop();
        if ("#".equals(s)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.right = posDerial(stack);
        root.left = posDerial(stack);
        return root;
    }

    // 先序方式序列化 迭代做法
    // 头 左 右
    public static String serialize2(TreeNode head) {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Stack<TreeNode> queue = new Stack<>();
        queue.push(head);
        while (!queue.isEmpty()) {
            TreeNode c = queue.pop();
            sb.append(c == null ? "#" : c.val).append(",");
            if (c != null) {
                queue.push(c.right);
                queue.push(c.left);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 先序反序列化
    public static TreeNode deserialize2(String data) {
        if ("[]".equals(data)) {
            return null;
        }
        String[] values = data.substring(1, data.length() - 2).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer("#".equals(value) ? null : new TreeNode(Integer.valueOf(value)));
        }
        return preDesrial(queue);
    }

    private static TreeNode preDesrial(Queue<TreeNode> queue) {
        TreeNode node = queue.poll();
        if (node == null) {
            return null;
        }
        node.left = preDesrial(queue);
        node.right = preDesrial(queue);
        return node;
    }

}
