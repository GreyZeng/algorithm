/*
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make
 * the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2],[2,3],[3,4],[1,3]] Output: 1 Explanation: [1,3] can be removed and the rest of
 * intervals are non-overlapping. Example 2:
 *
 * Input: [[1,2],[1,2],[1,2]] Output: 2 Explanation: You need to remove two [1,2] to make the rest
 * of intervals non-overlapping. Example 3:
 *
 * Input: [[1,2],[2,3]] Output: 0 Explanation: You don't need to remove any of the intervals since
 * they're already non-overlapping.
 *
 *
 * Note:
 *
 * You may assume the interval's end point is always bigger than its start point. Intervals like
 * [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */
package git.snippet.leetcode;

import java.util.Arrays;
import java.util.Comparator;

// 至少移除多少个区间后可以保证线段不重合
// 转换成非重叠区域的个数
public class LeetCode_0435_NonOverlappingIntervals {
  public static int eraseOverlapIntervals(int[][] intervals) {
    if (null == intervals || intervals.length <= 1) {
      return 0;
    }
    Interval[] arr = new Interval[intervals.length];
    int i = 0;
    for (int[] interval : intervals) {
      arr[i++] = new Interval(interval[0], interval[1]);
    }
    Arrays.sort(arr, Comparator.comparingInt((Interval o) -> o.end));
    // 非重叠区域个数
    int notOverlap = 1;
    int b = arr[0].end;
    for (i = 1; i < arr.length; i++) {
      if (arr[i].start >= b) {
        notOverlap++;
        b = arr[i].end;
      }
    }
    return arr.length - notOverlap;
  }

  public static class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
