package git.snippet.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// 求二叉树最宽的层有多少个节点 发现每一层的开始或者每一层的结束位置 可以用map实现，也可以不用map实现
// https://www.nowcoder.com/questionTerminal/e276c75bb92e4ac8b058b75157f09ba7
// 笔记：https://www.cnblogs.com/greyzeng/p/16860946.html
public class NowCoder_TreeMaxWidth {
    public static int getMaxWidthWithMap(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int getMaxWidth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int max = 1;
        queue.offer(head);
        TreeNode curEnd = head;
        TreeNode nextEnd = null;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            if (c.left != null) {
                queue.offer(c.left);
                nextEnd = c.left;
            }
            if (c.right != null) {
                queue.offer(c.right);
                nextEnd = c.right;
            }
            curLevelNodes++;
            // 当前节点已经到结束了
            if (c == curEnd) {
                max = Math.max(curLevelNodes, max);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (getMaxWidthWithMap(head) != getMaxWidth(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.value = data;
        }
    }
}
