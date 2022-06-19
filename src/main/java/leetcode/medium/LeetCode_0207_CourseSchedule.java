package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
//
//Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
// 
//
//Example 1:
//
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take. 
//             To take course 1 you should have finished course 0. So it is possible.
//Example 2:
//
//Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take. 
//             To take course 1 you should have finished course 0, and to take course 0 you should
//             also have finished course 1. So it is impossible.
// 
//
//Constraints:
//
//The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
//You may assume that there are no duplicate edges in the input prerequisites.
//1 <= numCourses <= 10^5
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
