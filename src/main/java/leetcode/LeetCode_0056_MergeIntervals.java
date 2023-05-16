package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Given a collection of intervals, merge all overlapping intervals.
//
// Example 1:
//
// Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
// Output: [[1,6],[8,10],[15,18]]
// Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// Example 2:
//
// Input: intervals = [[1,4],[4,5]]
// Output: [[1,5]]
// Explanation: Intervals [1,4] and [4,5] are considered overlapping.
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to
// get new method signature.
//
//
//
// Constraints:
//
// intervals[i][0] <= intervals[i][1]
public class LeetCode_0056_MergeIntervals {
    public static class Range {
        public int start;
        public int end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 开始位置排序
    // intervals N * 2
    public static int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return null;
        }
        int N = intervals.length;
        if (N == 0 || N == 1) {
            return intervals;
        }
        Range[] ranges = arrToRange(intervals, N);
        Arrays.sort(ranges, Comparator.comparingInt(o -> o.start));
        List<Range> t = new ArrayList<>();
        Range index = ranges[0];
        for (int i = 1; i < N; i++) {
            if (ranges[i].start <= index.end) {
                index = merge(index, ranges[i]);
            } else {
                t.add(index);
                index = ranges[i];
            }
        }
        t.add(index);
        return rangeToArr(t);
    }

    private static int[][] rangeToArr(List<Range> t) {
        int size = t.size();
        int[][] a = new int[size][2];
        for (int i = 0; i < size; i++) {
            a[i][0] = t.get(i).start;
            a[i][1] = t.get(i).end;
        }
        return a;
    }

    public static Range merge(Range r1, Range r2) {
        return new Range(Math.min(r1.start, r2.start), Math.max(r1.end, r2.end));
    }

    private static Range[] arrToRange(int[][] arr, int N) {
        Range[] ranges = new Range[N];
        for (int i = 0; i < N; i++) {
            ranges[i] = new Range(arr[i][0], arr[i][1]);
        }
        return ranges;
    }
}
