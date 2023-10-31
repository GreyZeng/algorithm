package git.snippet.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 笔记：https://www.cnblogs.com/greyzeng/p/16742656.html 深度优先遍历
 *
 * <p>1，利用栈实现
 *
 * <p>2，从源节点开始把节点按照深度放入栈，然后弹出
 *
 * <p>3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 *
 * <p>4，直到栈变空
 *
 * <p>
 */
public class Code_DFS {
  // 迭代版本
  public static List<Node> dfs(Node node) {
    if (node == null) {
      return new ArrayList<>();
    }
    List<Node> ans = new ArrayList<>();
    Deque<Node> stack = new ArrayDeque<>();
    HashSet<Node> set = new HashSet<>();
    stack.add(node);
    set.add(node);
    ans.add(node);
    while (!stack.isEmpty()) {
      Node cur = stack.pop();
      for (Node next : cur.nexts) {
        if (!set.contains(next)) {
          stack.push(cur);
          stack.push(next);
          set.add(next);
          ans.add(next);
          break;
        }
      }
    }
    return ans;
  }

  // 递归版本
  public static List<Node> dfs2(Node node) {
    if (node == null) {
      return new ArrayList<>();
    }
    List<Node> ans = new ArrayList<>();
    Set<Node> set = new HashSet<>();
    dfs(node, ans, set);
    return ans;
  }

  private static void dfs(Node node, List<Node> ans, Set<Node> set) {
    ans.add(node);
    set.add(node);
    if (node.nexts != null && !node.nexts.isEmpty()) {
      for (Node n : node.nexts) {
        if (!set.contains(n)) {
          dfs(n, ans, set);
        }
      }
    }
  }

  public static void main(String[] args) {
    test1();
    test2();
  }

  private static void test1() {
    Node t3 = new Node(3);
    Node t4 = new Node(4);
    Node t5 = new Node(5);
    Node t6 = new Node(6);
    Node t7 = new Node(7);
    Node t8 = new Node(8);
    Node t9 = new Node(9);
    Node t10 = new Node(10);
    t3.nexts.add(t6);
    t3.nexts.add(t10);
    t3.nexts.add(t5);
    t4.nexts.add(t7);
    t4.nexts.add(t9);
    t5.nexts.add(t8);
    t5.nexts.add(t7);
    t6.nexts.add(t4);
    t7.nexts.add(t8);
    t7.nexts.add(t9);
    t9.nexts.add(t10);
    t5.nexts.add(t10);
    List<Node> dfs = dfs(t3);
    for (Node n : dfs) {
      System.out.print(n.value + " ");
    }
    System.out.println();
  }

  private static void test2() {
    Node t3 = new Node(3);
    Node t4 = new Node(4);
    Node t5 = new Node(5);
    Node t6 = new Node(6);
    Node t7 = new Node(7);
    Node t8 = new Node(8);
    Node t9 = new Node(9);
    Node t10 = new Node(10);
    t3.nexts.add(t6);
    t3.nexts.add(t10);
    t3.nexts.add(t5);
    t4.nexts.add(t7);
    t4.nexts.add(t9);
    t5.nexts.add(t8);
    t5.nexts.add(t7);
    t6.nexts.add(t4);
    t7.nexts.add(t8);
    t7.nexts.add(t9);
    t9.nexts.add(t10);
    t5.nexts.add(t10);
    List<Node> dfs = dfs2(t3);
    for (Node n : dfs) {
      System.out.print(n.value + " ");
    }
    System.out.println();
  }
}
