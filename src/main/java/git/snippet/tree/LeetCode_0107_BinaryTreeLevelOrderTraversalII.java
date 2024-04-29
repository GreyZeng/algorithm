package git.snippet.tree;

import java.util.*;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// https://leetcode.com/problems/binary-tree-level-order-traversal-ii
// 按层遍历进阶：从底部开始遍历
// 只需要把按层遍历的队列结构改成栈就可以
public class LeetCode_0107_BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                // 一次搞定一层
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    curAns.add(curNode.val);
                    if (curNode.left != null) {
                        queue.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.add(curNode.right);
                    }
                }
            }
            // 每次把最新的那一层插入到链表的头部，模拟栈
            ans.add(0, curAns);
        }
        return ans;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Deque<List<Integer>> stack = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> levelRecords = new ArrayList<>();
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            levelRecords.add(poll.val);
            if (null != poll.left) {
                queue.offer(poll.left);
                nextEnd = poll.left;
            }
            if (null != poll.right) {
                queue.offer(poll.right);
                nextEnd = poll.right;
            }
            if (poll == curEnd) {
                curEnd = nextEnd;
                stack.push(levelRecords);
                levelRecords = new ArrayList<>();
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.poll());
        }
        return result;
    }

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
}
