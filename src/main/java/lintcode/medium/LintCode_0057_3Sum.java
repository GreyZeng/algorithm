package lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/57/
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @since
 */
public class LintCode_0057_3Sum {
    public List<List<Integer>> threeSum(int[] numbers) {
        if (null == numbers || numbers.length == 0) {
            return new ArrayList<>();
        }
        int size = numbers.length;
        List<List<Integer>> result = new ArrayList<>();
        // 先排序 O(N*logN)
        Arrays.sort(numbers);
        for (int i = size - 1; i >= 2; i--) {
            if (i == size - 1 || numbers[i] != numbers[i + 1]) {
                // 必须要移动到下一个不等于number[i]的数才不会出现重复
                List<List<Integer>> lists = twoSum(numbers, 0, i - 1, -numbers[i]);
                if (null != lists && 0 != lists.size()) {
                    for (List<Integer> list : lists) {
                        list.add(numbers[i]);
                        result.add(list);
                    }
                }
            }

        }
        return result;
    }

    // start ... end 之间是有序的
    public List<List<Integer>> twoSum(int[] numbers, int start, int end, int target) {
        List<List<Integer>> list = new ArrayList<>();
        while (start < end) {
            int startVal = numbers[start];
            int endVal = numbers[end];
            int sum = startVal + endVal;
            if (sum < target) {
                while (start < end && numbers[start] == startVal) {
                    // 必须要移动到下一个不为startVal的数才不会出现重复
                    start++;
                }
            } else if (sum > target) {
                while (end > start && numbers[end] == endVal) {
                    end--;
                }
            } else {
                // sum == target
                List<Integer> pair = new LinkedList<>();
                pair.add(startVal);
                pair.add(endVal);
                list.add(pair);
                while (start < end && numbers[start] == startVal) {
                    start++;
                }
            }
        }
        return list;
    }


}
