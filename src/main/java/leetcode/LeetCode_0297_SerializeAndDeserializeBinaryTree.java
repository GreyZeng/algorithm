package leetcode;

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
        //   System.out.print(serialize(root));
        System.out.print(serialize(deserialize(serialize(root))));
    }

    // 按层序列化
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);
        StringBuilder sb = new StringBuilder("[");
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (; size > 0; size--) {
                TreeNode s = queue.poll();
                sb.append(s == null ? "#" : s.val).append(",");
                if (s != null) {
                    if (s.left != null) {
                        queue.offer(s.left);
                    } else {
                        queue.offer(null);
                    }
                    if (s.right != null) {
                        queue.offer(s.right);
                    } else {
                        queue.offer(null);
                    }
                }
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
        String strs = data.substring(1, data.length() - 1);
        String[] split = strs.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        int N = split.length;
        while (!queue.isEmpty() && size < N) {
            TreeNode cur = queue.poll();
            cur.left = "#".equals(split[size]) ? null : new TreeNode(Integer.parseInt(split[size]));
            size++;
            if (size < N) {
                cur.right = "#".equals(split[size]) ? null : new TreeNode(Integer.parseInt(split[size]));
                size++;
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return root;
    }

    // 后序方式序列化
    public static String serialize3(TreeNode root) {
        Stack<Integer> stack = new Stack<>();
        posSerial(root, stack);
        return generate(stack);
    }

    private static String generate(Stack<Integer> ans) {
        int size = ans.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!ans.isEmpty()) {
            sb.append(ans.pop());
            size--;
            if (size != 0) {
                sb.append(",");
            } else {
                sb.append("]");
            }
        }
        return sb.toString();
    }

    private static void posSerial(TreeNode root, Stack<Integer> stack) {
        if (null == root) {
            stack.push(null);
        } else {
            stack.push(root.val);
            posSerial(root.right, stack);
            posSerial(root.left, stack);
        }

    }

    // 后序方式反序列化
    public static TreeNode deserialize3(String data) {
        if (null == data || data.length() < 1) {
            return null;
        }
        data = data.substring(1, data.length() - 1);
        Stack<Integer> stack = new Stack<>();
        for (String e : data.split(",")) {
            stack.push("null".equals(e) ? null : Integer.valueOf(e));
        }
        return posSerial(stack);
    }

    private static TreeNode posSerial(Stack<Integer> stack) {
        Integer i = stack.pop();
        if (null == i) {
            return null;
        } else {
            TreeNode root = new TreeNode(i);
            root.right = posSerial(stack);
            root.left = posSerial(stack);
            return root;
        }
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
