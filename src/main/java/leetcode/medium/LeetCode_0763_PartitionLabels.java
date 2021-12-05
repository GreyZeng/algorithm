/*A string S of lowercase English letters is given. 

We want to partition this string into as many parts as possible so that each letter appears in at most one part, 

and return a list of integers representing the size of these parts.



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
package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

// 字符串尽可能分割成多个片段，每个字符最多出现在一个片段中
public class LeetCode_0763_PartitionLabels {

    // 统计每一个字符最后出现的位置
    // 从头遍历字符，如果找到之前字符最大出现位置下标和当前下标相等，则找到了分割点
    public static List<Integer> partitionLabels(String s) {
        char[] str = s.toCharArray();
        // help收集每个字符最右位置
        int[] help = new int[26];
        for (int i = 0; i < str.length; i++) {
            help[str[i] - 'a'] = i;
        }
        int right = 0;
        int pre = right;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            // 是否可以将right位置扩散
            right = Math.max(right, help[str[i] - 'a']);
            if (i == right) {
                // 当前位置已经是能扩散的最右位置了
                // 收集答案
                ans.add(right - pre + 1);
                pre = right + 1;
            }
        }
        return ans;
    }
}
