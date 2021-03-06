/*A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.



        Example 1:

        Input: S = "ababcbacadefegdehijhklij"
        Output: [9,7,8]
        Explanation:
        The partition is "ababcbaca", "defegde", "hijhklij".
        This is a partition so that each letter appears in at most one part.
        A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.


        Note:

        S will have length in range [1, 500].
        S will consist of lowercase English letters ('a' to 'z') only.*/
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_0763_PartitionLabels {
//    统计每一个字符最后出现的位置
//    从头遍历字符，如果找到之前字符最大出现位置下标和当前下标相等，则找到了分割点

    public static List<Integer> partitionLabels(String S) {
        char[] str = S.toCharArray();
        int[] lastPosition = new int[26];
        for (int i = 0; i < str.length; i++) {
            lastPosition[str[i] - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int right = 0;
        int s = 0;
        for (int i = 0; i < str.length; i++) {
            right = Math.max(lastPosition[str[i] - 'a'], right);
            if (right == i) {
                ans.add(i - s + 1);
                s = right + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }
}
