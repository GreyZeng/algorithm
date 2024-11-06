/*
 * 描述 给定一系列的会议时间间隔intervals， 包括起始和结束时间[[s1,e1],[s2,e2],...] (si < ei)，找到所需的最小的会议室数量。
 *
 *
 * 样例 样例1
 *
 * 输入: intervals = [(0,30),(5,10),(15,20)] 输出: 2 解释: 需要两个会议室 会议室1:(0,30) 会议室2:(5,10),(15,20) 样例2
 *
 * 输入: intervals = [(2,7)] 输出: 1 解释: 只需要1个会议室就够了
 */
package grey.algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// leetcode加锁253
// lintcode：https://www.lintcode.com/problem/meeting-rooms-ii/description
// 本质是线段最大重合区域问题（交接点不算重合区域）
// https://www.nowcoder.com/questionTerminal/1ae8d0b6bb4e4bcdbf64ec491f63fc37
public class Code_0033_LintCode_0919_MeetingRoomsII {
    public static int minMeetingRooms(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        PriorityQueue<Interval> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
        int max = 0;
        for (Interval i : intervals) {
            while (!queue.isEmpty() && queue.peek().end <= i.start) {
                queue.poll();
            }
            queue.offer(i);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    // 此类不要提交
    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
