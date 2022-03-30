package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//数组为
//{3, 2, 2, 3, 1}，查询为(0, 3, 2)
//意思是在数组里下标0~3这个范围上，有几个2？答案返回2。
//假设给你一个数组arr，
//对这个数组的查询非常频繁，都给出来
//请返回所有查询的结果
//tips:
//预处理，
//某个数出现在哪些位置？
//二分
//
//大于某个数最左和最右
// https://leetcode-cn.com/problems/range-frequency-queries/
public class LeetCode_2080_RangeFrequencyQueries {
    class RangeFreqQuery {
        Map<Integer, ArrayList<Integer>> pos;

        public RangeFreqQuery(int[] arr) {
            pos = new HashMap<>(arr.length);
            for (int i = 0; i < arr.length; i++) {
                if (!pos.containsKey(arr[i])) {
                    pos.put(arr[i], new ArrayList<>());
                }
                pos.get(arr[i]).add(i);
            }
        }

        public int query(int l, int r, int value) {
            if (!pos.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> list = pos.get(value);
            int a = lessCount(list, l);
            int b = lessCount(list, r + 1);
            return b - a;
        }

        private int lessCount(ArrayList<Integer> list, int value) {
            int s = 0;
            int e = list.size() - 1;
            int mostRight = -1;
            while (s <= e) {
                int mid = s + ((e - s) >> 1);
                if (list.get(mid) < value) {
                    mostRight = mid;
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
            return mostRight + 1;
        }
    }
}
