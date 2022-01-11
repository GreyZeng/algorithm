package leetcode.hard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println(serialize3(root));
        System.out.println(serialize3(deserialize3(serialize3(root))));
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

    // 先序序列化
    public static String serialize2(TreeNode root) {
        Queue<Integer> ans = new LinkedList<>();
        preSerial(root, ans);
        return generate(ans);
    }

    private static Queue<Integer> generate(String data) {
        Queue<Integer> queue = new LinkedList<>();
        for (String e : data.split(",")) {
            queue.offer("null".equals(e) ? null : Integer.valueOf(e));
        }
        return queue;
    }

    private static String generate(Queue<Integer> ans) {
        int size = ans.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!ans.isEmpty()) {
            sb.append(ans.poll());
            size--;
            if (size != 0) {
                sb.append(",");
            } else {
                sb.append("]");
            }
        }
        return sb.toString();
    }

    private static void preSerial(TreeNode root, Queue<Integer> ans) {
        if (root == null) {
            ans.offer(null);
        } else {
            ans.offer(root.val);
            preSerial(root.left, ans);
            preSerial(root.right, ans);
        }
    }

    // 先序反序列化
    public static TreeNode deserialize2(String data) {
        if (null == data || data.length() < 1) {
            return null;
        }
        data = data.substring(1, data.length() - 1);
        return preDeserial(generate(data));
    }

    private static TreeNode preDeserial(Queue<Integer> elements) {
        Integer t = elements.poll();
        if (t == null) {
            return null;
        } else {
            TreeNode root = new TreeNode(t);
            root.left = preDeserial(elements);
            root.right = preDeserial(elements);
            return root;
        }
    }

}
