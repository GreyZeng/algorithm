
package 练习题.leetcode.easy;

import java.util.*;

/*
 * 写一个程序，输出从 1 到 n 数字的字符串表示。 1. 如果n是3的倍数，输出“Fizz”； 2. 如果n是5的倍数，输出“Buzz”； 3.如果n同时是3和5的倍数，输出
 * “FizzBuzz”。 示例： n = 15, 返回: [ "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
 * "11", "Fizz", "13", "14", "FizzBuzz" ] Leetcode题目 : https://leetcode.cn/problems/fizz-buzz/
 */
@Deprecated
public class LeetCode_0412_FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }

}
