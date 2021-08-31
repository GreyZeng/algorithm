package lintcode;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/8/31
 * @since
 */
public class LintCode_0363_TrappingRainWater {
    public int trapRainWater(int[] heights) {
        if (null == heights || heights.length <= 2) {
            return 0;
        }
        int len = heights.length;
        int lMax = heights[0];
        int rMax = heights[len - 1];
        int l = 1;
        int r = len - 2;
        int water = 0;
        while (l <= r) {
            if (lMax < rMax) {
                // 左边是阈值
                if (heights[l] < lMax) {
                    water += (lMax - heights[l]);
                } else {
                    lMax = heights[l];
                }
                l++;
            } else {
                if (heights[r] < rMax) {
                    water += (rMax - heights[r]);
                } else {
                    rMax = heights[r];
                }
                r--;
            }
        }
        return water;
    }

}