package lintcode;

/**
 * https://www.lintcode.com/problem/383/
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/4/16
 * @since
 */
public class LintCode_0383_ContainerWithMostWater {

    public static int maxArea(int[] heights) {
        if (null == heights || heights.length == 1) {
            return 0;
        }
        int l = 0;
        int r = heights.length - 1;
        int w;
        int max = 0;
        while (l < r) {
            if (heights[r] < heights[l]) {
                w = (r - l) * heights[r];
                r--;
            } else {
                w = (r - l) * heights[l];
                l++;
            }
            max = Math.max(max, w);
        }
        return max;
    }
}
