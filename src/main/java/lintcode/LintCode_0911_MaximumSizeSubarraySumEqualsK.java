package lintcode;

import java.util.HashMap;

public class LintCode_0911_MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>(N);
        int cur = 0;
        int max = 0;
        int sum = 0;
        map.put(0, -1);
        for (; cur < arr.length; cur++) {
            sum += arr[cur];
            if (!map.containsKey(sum)) {
                map.put(sum, cur);
            }
            if (map.containsKey(sum - k)) {
                max = Math.max(max, cur - map.get(sum - k));
            }
        }
        return max;
    }

}
