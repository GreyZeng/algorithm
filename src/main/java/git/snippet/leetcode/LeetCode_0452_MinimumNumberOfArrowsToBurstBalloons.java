// There are some spherical balloons spread in two-dimensional space. For each balloon,
// provided input is the start and end coordinates of the horizontal diameter.
// Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end
// of the diameter suffice.
// The start is always smaller than the end.
//
// An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with
// xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the
// number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.
//
// Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that
// must be shot to burst all balloons.
//
//
//
// Example 1:
//
// Input: points = [[10,16],[2,8],[1,6],[7,12]]
// Output: 2
// Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and
// [1,6]) and another arrow at x = 11 (bursting the other two balloons).
// Example 2:
//
// Input: points = [[1,2],[3,4],[5,6],[7,8]]
// Output: 4
// Example 3:
//
// Input: points = [[1,2],[2,3],[3,4],[4,5]]
// Output: 2
// Example 4:
//
// Input: points = [[1,2]]
// Output: 1
// Example 5:
//
// Input: points = [[2,3],[2,3]]
// Output: 1
//
//
// Constraints:
//
// 0 <= points.length <= 10^4
// points[i].length == 2
// -2^31 <= xstart < xend <= 2^31 - 1
package git.snippet.leetcode;

import java.util.Arrays;

public class LeetCode_0452_MinimumNumberOfArrowsToBurstBalloons {

    public static int findMinArrowShots(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(
                lines,
                (o1, o2) -> {
                    if (o1.end > o2.end) {
                        return 1;
                    } else if (o1.end < o2.end) {
                        return -1;
                    }
                    return 0;
                });
        int min = 1;
        int b = lines[0].end;
        for (int i = 1; i < lines.length; i++) {
            if (b >= lines[i].start) {
                continue;
            }
            min++;
            b = lines[i].end;
        }
        return min;
    }

    public static void main(String[] args) {
        // [[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]]
        int[][] p = {{9, 12}, {1, 10}, {4, 11}, {8, 12}, {3, 9}, {6, 9}, {6, 7}};
        System.out.println(findMinArrowShots(p));
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }
}
