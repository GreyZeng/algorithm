package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//There are a total of n courses you have to take labelled from 0 to n - 1.
//
//Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
//
//Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
//
// 
//
//Example 1:
//
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
//Example 2:
//
//Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
//Example 3:
//
//Input: numCourses = 1, prerequisites = []
//Output: [0]
// 
//
//Constraints:
//
//1 <= numCourses <= 2000
//0 <= prerequisites.length <= numCourses * (numCourses - 1)
//prerequisites[i].length == 2
//0 <= ai, bi < numCourses
//ai != bi
//All the pairs [ai, bi] are distinct.
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
