/*描述
给定一系列的会议时间间隔intervals，
包括起始和结束时间[[s1,e1],[s2,e2],...] (si < ei)，找到所需的最小的会议室数量。


样例
样例1

输入: intervals = [(0,30),(5,10),(15,20)]
输出: 2
解释:
需要两个会议室
会议室1:(0,30)
会议室2:(5,10),(15,20)
样例2

输入: intervals = [(2,7)]
输出: 1
解释:
只需要1个会议室就够了*/
package lintcode.medium;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//leetcode加锁253
// lintcode：https://www.lintcode.com/problem/meeting-rooms-ii/description
public class LintCode_0919_MeetingRoomsII {
	public static class Interval {
		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class StartComp implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
	}

	public static class EndComp implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.end - o2.end;
		}
	}

	public static int minMeetingRooms(List<Interval> intervals) {
		Collections.sort(intervals, new StartComp());
		PriorityQueue<Interval> queue = new PriorityQueue<>(new EndComp());
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

}
