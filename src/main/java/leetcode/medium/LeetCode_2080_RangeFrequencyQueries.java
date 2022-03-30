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

        private Map<Integer, ArrayList<Integer>> map;

        public RangeFreqQuery(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> indexArr = map.get(value);
            // 查询 < L 的下标有几个
            int a = countLess(indexArr, L);
            // 查询 < R+1 的下标有几个
            int b = countLess(indexArr, R + 1);
            return b - a;
        }

        // 在有序数组arr中，用二分的方法数出<limit的数有几个
        // 也就是用二分法，找到<limit的数中最右的位置
        private int countLess(ArrayList<Integer> arr, int limit) {
            int L = 0;
            int R = arr.size() - 1;
            int mostRight = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (arr.get(mid) < limit) {
                    mostRight = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return mostRight + 1;
        }
    }
}
