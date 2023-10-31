package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

// 现在你总共有 n 门课需要选，记为0到n-1。
// 在选修某些课程之前需要一些先修课程。例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示他们: [0,1]
// 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
// 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
// 示例1:
// 输入: 2, [[1,0]]
// 输出: [0,1]
// 解释:总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 示例2:
// 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
// 输出: [0,1,2,3] or [0,2,1,3]
// 解释:总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
// 因此，一个正确的课程顺序是[0,1,2,3] 。另一个正确的排序是[0,2,1,3] 。
// Leetcode题目 : https://leetcode.com/problems/course-schedule-ii/
public class LeetCode_0210_CourseScheduleII {

  public static class Node {
    public int name; // 节点名称
    public int in; // 入度的个数
    public ArrayList<Node> nexts; // 邻居节点

    public Node(int n) {
      name = n;
      in = 0;
      nexts = new ArrayList<>();
    }
  }

  // 找入度为0的点，删掉后，继续找入度为0的点..依次调用下去
  // 如果最后所有的点删光了，则说明可以完成，如果出现循环无法删，则无法完成
  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] res = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      res[i] = i;
    }
    if (prerequisites == null || prerequisites.length == 0) {
      return res;
    }
    HashMap<Integer, Node> map = new HashMap<>();
    for (int[] prerequisite : prerequisites) {
      Node f;
      Node t;
      if (map.containsKey(prerequisite[1])) {
        f = map.get(prerequisite[1]);
      } else {
        f = new Node(prerequisite[1]);
      }
      if (map.containsKey(prerequisite[0])) {
        t = map.get(prerequisite[0]);
      } else {
        t = new Node(prerequisite[0]);
      }
      t.in++;
      f.nexts.add(t);
      map.put(prerequisite[1], f);
      map.put(prerequisite[0], t);
    }
    LinkedList<Node> zero = new LinkedList<>();
    // int s = 0;
    int x = 0;
    for (int i = 0; i < numCourses; i++) {
      if (!map.containsKey(i)) {
        res[x++] = i;
      } else {
        Node t = map.get(i);
        if (t.in == 0) {
          zero.add(t);
        }
      }
    }
    int s = map.size();
    while (!zero.isEmpty()) {
      s--;
      Node t = zero.poll();
      res[x++] = t.name;
      for (Node i : t.nexts) {
        i.in--;
        if (i.in == 0) {
          zero.add(i);
        }
      }
    }
    return s == 0 ? res : new int[] {};
  }
}
