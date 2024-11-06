package grey.algorithm;

import java.util.*;

// https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/description/
// 本质是线段的最大重合问题
// https://www.nowcoder.com/questionTerminal/f8fa4b67dd054892966d280790c42ba3
// 连接点算重合部分
public class Code_0035_LeetCode_2406_DivideIntervalsIntoMinimumNumberOfGroups {
    public int minGroups(int[][] intervals) {
        // 按开始位置排序
        Arrays.sort(intervals, Comparator.comparingInt(line -> line[0]));
        // 以结束位置建立小根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(line -> line[1]));
        int max = 0;
        for (int[] line : intervals) {
            while (!heap.isEmpty() && heap.peek()[1] < line[0]) {
                heap.poll();
            }
            heap.offer(line);
            max = Math.max(max, heap.size());
        }
        return max;
    }
}
