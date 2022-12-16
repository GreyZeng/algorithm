package 数据结构.二叉树;

// 笔记：https://www.cnblogs.com/greyzeng/p/16948391.html
// 国内教程定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树。
// 国外(国际)定义:a binary tree T is full if each node is either a leaf or possesses exactly two
// childnodes.
public class Code_IsFull {
  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  // 第一种方法
  // 收集整棵树的高度h，和节点数n
  // 只有满二叉树满足 : 2 ^ h - 1 == n
  public static boolean isFull1(Node head) {
    if (head == null) {
      return true;
    }
    Info1 all = process1(head);
    return (1 << all.height) - 1 == all.nodes;
  }

  public static class Info1 {
    public int height;
    public int nodes;

    public Info1(int h, int n) {
      height = h;
      nodes = n;
    }
  }

  public static Info1 process1(Node head) {
    if (head == null) {
      return new Info1(0, 0);
    }
    Info1 leftInfo = process1(head.left);
    Info1 rightInfo = process1(head.right);
    int height = Math.max(leftInfo.height, rightInfo.height) + 1;
    int nodes = leftInfo.nodes + rightInfo.nodes + 1;
    return new Info1(height, nodes);
  }

  // 第二种方法
  // 收集子树是否是满二叉树
  // 收集子树的高度
  // 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
  public static boolean isFull2(Node head) {
    if (head == null) {
      return true;
    }
    return process2(head).isFull;
  }

  public static class Info2 {
    public boolean isFull;
    public int height;

    public Info2(boolean f, int h) {
      isFull = f;
      height = h;
    }
  }

  public static Info2 process2(Node h) {
    if (h == null) {
      return new Info2(true, 0);
    }
    Info2 leftInfo = process2(h.left);
    Info2 rightInfo = process2(h.right);
    boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
    int height = Math.max(leftInfo.height, rightInfo.height) + 1;
    return new Info2(isFull, height);
  }

  // for test
  public static Node generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  // for test
  public static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  public static void main(String[] args) {
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBST(maxLevel, maxValue);
      if (isFull1(head) != isFull2(head)) {
        System.out.println("出错了!");
      }
    }
    System.out.println("测试结束");
  }
}
