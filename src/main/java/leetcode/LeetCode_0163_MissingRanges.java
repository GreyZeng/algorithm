/*描述
        给定一个排序整数数组，其中元素的取值范围为[lower，upper] (包括边界)，返回其缺少的范围。


        样例
        样例1

        输入：
        nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
        输出：
        ["2", "4->49", "51->74", "76->99"]
        解释：
        在区间[0,99]中，缺失的区间有：[2,2]，[4,49]，[51,74]和[76,99]
        样例2

        输入：
        nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
        输出：
        ["4->6"]
        解释：
        在区间[0,7]中，缺失的区间有：[4,6]*/
package leetcode;

import java.util.ArrayList;
import java.util.List;

// leetcode 加锁
// lintcode 链接：https://www.lintcode.com/problem/missing-ranges/description
public class LeetCode_0163_MissingRanges {

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            if (num > lower) {
                list.add(build(lower, num - 1));
            }
            if (num == upper) {
                return list;
            }
            lower = num + 1;
        }
        if (lower <= upper) {
            list.add(build(lower, upper));
        }
        return list;
    }

    public static String build(int s, int e) {
        if (s == e) {
            return s + "";
        }
        return s + "->" + e;
    }

}
