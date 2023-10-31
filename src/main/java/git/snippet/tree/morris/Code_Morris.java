package git.snippet.tree.morris;

// morris遍历
// 笔记：https://www.cnblogs.com/greyzeng/articles/15941957.html
public class Code_Morris {

  // 当前是cur
  // 1. cur无左树,cur = cur.right
  // 2. cur有左树,找到左树最右节点mostRight
  // a. mostRight的右指针指向null, mostRight.right = cur, cur = cur.right
  // b. mostRight的右指针指向当前节点cur，mostRight.right = null, cur = cur.right
  // 3. cur = null 停
  public static void morrisPrint(TreeNode head) {
    if (head == null) {
      return;
    }
    System.out.println("....morris order....");
    TreeNode cur = head;
    System.out.print(cur.val + "-->");
    TreeNode mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }
        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          System.out.print(cur.val + "-->");
          continue;
        } else {
          mostRight.right = null;
        }
      }
      cur = cur.right;
      if (cur != null) {
        System.out.print(cur.val + "-->");
      }
    }
  }

  public static void morrisPre(TreeNode head) {
    if (head == null) {
      return;
    }
    TreeNode cur = head;
    TreeNode mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }
        if (mostRight.right == null) {
          System.out.print(cur.val + " ");
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
        }
      } else {
        System.out.print(cur.val + " ");
      }
      cur = cur.right;
    }
    System.out.println();
  }

  public static void morrisIn(TreeNode head) {
    if (head == null) {
      return;
    }
    TreeNode cur = head;
    TreeNode mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }
        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
        }
      }
      System.out.print(cur.val + " ");
      cur = cur.right;
    }
    System.out.println();
  }

  // 处理时机放在能回到自己两次的点，且第二次回到自己的时刻
  // 第二次回到他自己的时候，不打印他自己，而是逆序打印他左树的右边界
  // 整个遍历结束后，单独逆序打印整棵树的右边界
  public static void morrisPos(TreeNode head) {
    if (head == null) {
      return;
    }
    TreeNode cur = head;
    TreeNode mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }
        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
          printEdge(cur.left);
        }
      }
      cur = cur.right;
    }
    printEdge(head);
    System.out.println();
  }

  public static void printEdge(TreeNode head) {
    TreeNode tail = reverseEdge(head);
    TreeNode cur = tail;
    while (cur != null) {
      System.out.print(cur.val + " ");
      cur = cur.right;
    }
    reverseEdge(tail);
  }

  public static TreeNode reverseEdge(TreeNode from) {
    TreeNode pre = null;
    TreeNode next;
    while (from != null) {
      next = from.right;
      from.right = pre;
      pre = from;
      from = next;
    }
    return pre;
  }

  // for test -- print tree
  public static void printTree(TreeNode head) {
    System.out.println("Binary Tree:");
    printInOrder(head, 0, "H", 17);
    System.out.println();
  }

  public static void printInOrder(TreeNode head, int height, String to, int len) {
    if (head == null) {
      return;
    }
    printInOrder(head.right, height + 1, "v", len);
    String val = to + head.val + to;
    int lenM = val.length();
    int lenL = (len - lenM) / 2;
    int lenR = len - lenM - lenL;
    val = getSpace(lenL) + val + getSpace(lenR);
    System.out.println(getSpace(height * len) + val);
    printInOrder(head.left, height + 1, "^", len);
  }

  public static String getSpace(int num) {
    String space = " ";
    StringBuffer buf = new StringBuffer("");
    for (int i = 0; i < num; i++) {
      buf.append(space);
    }
    return buf.toString();
  }

  public static boolean isBST(TreeNode head) {
    if (head == null) {
      return true;
    }
    TreeNode cur = head;
    TreeNode mostRight = null;
    Integer pre = null;
    boolean ans = true;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }
        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
        }
      }
      if (pre != null && pre >= cur.val) {
        ans = false;
      }
      pre = cur.val;
      cur = cur.right;
    }
    return ans;
  }

  public static class TreeNode {
    public int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static TreeNode buildTree2() {
    TreeNode N1 = new TreeNode(1);
    TreeNode N2 = new TreeNode(2);
    TreeNode N3 = new TreeNode(3);
    TreeNode N4 = new TreeNode(4);
    TreeNode N5 = new TreeNode(5);
    TreeNode N6 = new TreeNode(6);
    TreeNode N7 = new TreeNode(7);
    N1.left = N2;
    N1.right = N3;
    N2.left = N4;
    N2.right = N5;
    N3.left = N6;
    N3.right = N7;
    // TreeNode N8 = new TreeNode(8);
    // TreeNode N9 = new TreeNode(9);
    // TreeNode N10 = new TreeNode(10);
    // TreeNode N11 = new TreeNode(11);
    // TreeNode N12 = new TreeNode(12);
    // TreeNode N13 = new TreeNode(13);
    // N1.left = N2;
    // N1.right = N3;
    // N2.right = N4;
    // N3.left = N5;
    // N3.right = N6;
    // N4.left = N7;
    // N4.right = N8;
    // N6.left = N9;
    // N6.right = N10;
    // N7.left = N11;
    // N8.left = N12;
    // N9.right = N13;
    return N1;
  }

  public static TreeNode buildTree() {
    TreeNode N1 = new TreeNode(1);
    TreeNode N2 = new TreeNode(2);
    TreeNode N3 = new TreeNode(3);
    TreeNode N4 = new TreeNode(4);
    TreeNode N5 = new TreeNode(5);
    TreeNode N6 = new TreeNode(6);
    TreeNode N7 = new TreeNode(7);

    TreeNode N8 = new TreeNode(8);
    TreeNode N9 = new TreeNode(9);
    TreeNode N10 = new TreeNode(10);
    N1.left = N2;

    N2.right = N3;
    N3.right = N4;
    N4.left = N5;
    N5.right = N6;
    N1.right = N7;
    N7.left = N8;
    N7.right = N9;
    N9.left = N10;
    return N1;
  }

  public static void main(String[] args) {
    TreeNode root = buildTree();
    morrisPrint(root);
  }
}
