package git.snippet.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

// 笔记：https://www.cnblogs.com/greyzeng/p/16789819.html
// 序列化和反序列化二叉树
/*
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *
 * */
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class LeetCode_0297_SerializeAndDeserializeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Codec {

        // 按层序列化
        // 空节点补充为#
        // 两侧用[]符号框住
        public String serialize(TreeNode head) {
            if (head == null) {
                return "[]";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            StringBuffer sb = new StringBuffer("[");
            queue.offer(head);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                sb.append(node == null ? "#" : node.val);
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        // 按层反序列化
        public TreeNode deserialize(String data) {
            if (Objects.equals(data, "[]")) {
                return null;
            }
            // 范围只取 1 ~ strs.length - 2;
            // 因为要过滤掉开头的[, 末尾的,和]
            String[] strs = data.substring(1, data.length() - 1).split(",");
            // 0 号位置一定是头节点（无需判空）
            TreeNode head = new TreeNode(Integer.parseInt(strs[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(head);
            int i = 1; // 遍历 strs 用
            while (i <= strs.length - 1) {
                TreeNode node = queue.poll();
                //                if (node != null) {
                //                    TreeNode left = Objects.equals(strs[i], "#") ? null : new
                // TreeNode(Integer.parseInt(strs[i]));
                //                    node.left = left;
                //                    queue.offer(left);
                //                    i++;
                //                    if (i <= strs.length - 1) {
                //                        TreeNode right = Objects.equals(strs[i], "#") ? null : new
                // TreeNode(Integer.parseInt(strs[i]));
                //                        node.right = right;
                //                        queue.offer(right);
                //                    }
                //                    i++;
                //                }
                if (node != null) {
                    // 可以代替上述注释部分，因为在序列化的时候，每个非空节点已经补齐了两个儿子节点，所以一旦遇到非空节点，其必有两个儿子节点。
                    TreeNode left =
                            Objects.equals(strs[i], "#") ? null : new TreeNode(Integer.parseInt(strs[i]));
                    node.left = left;
                    queue.offer(left);
                    i++;
                    TreeNode right =
                            Objects.equals(strs[i], "#") ? null : new TreeNode(Integer.parseInt(strs[i]));
                    node.right = right;
                    queue.offer(right);
                    i++;
                }
            }
            return head;
        }

        // 后序方式序列化 迭代方法
        public String serialize3(TreeNode head) {
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
        public TreeNode deserialize3(String data) {
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

        private TreeNode posDerial(Stack<String> stack) {
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
        public String serialize2(TreeNode head) {
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
        public TreeNode deserialize2(String data) {
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

        private TreeNode preDesrial(Queue<TreeNode> queue) {
            TreeNode node = queue.poll();
            if (node == null) {
                return null;
            }
            node.left = preDesrial(queue);
            node.right = preDesrial(queue);
            return node;
        }
    }
}
