package lintcode.easy;

import java.util.HashMap;
import java.util.Map;

// https://www.lintcode.com/problem/56/
public class LintCode_0056_TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{ map.get(target - numbers[i]),i};
            } else {
                map.put(numbers[i],i);
            }
        }
        return result;
    }
}
