package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

// 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
// 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai
// 则 必须 先学习课程 bi 。
// 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
// 示例 1：
// 输入：numCourses = 2, prerequisites = [[1,0]]
// 输出：true
// 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
// 示例 2：
// 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
// 输出：false
// 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
// Leetcode题目 : https://leetcode.com/problems/course-schedule/
public class LeetCode_0207_CourseSchedule {
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
  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
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
    int s = 0;
    for (Node t : map.values()) {
      s++;
      if (t.in == 0) {
        zero.add(t);
      }
    }
    while (!zero.isEmpty()) {
      s--;
      for (Node i : zero.poll().nexts) {
        i.in--;
        if (i.in == 0) {
          zero.add(i);
        }
      }
    }
    return s == 0;
  }
}
