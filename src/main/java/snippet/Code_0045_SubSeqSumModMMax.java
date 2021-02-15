package snippet;

import java.util.HashSet;

//给定一个非负数组arr，和一个正数m
//        返回arr的所有子序列中累加和%m之后的最大值。
//        tips:
//        方法1：
//        二维表(n*sum)
//        方法2：
//        二维表（n*m）：列是数组下标，行0...m-1
//        0到i上随意组合，在i%m后能否组成j
//        方法3（分治）：
//        二维表(n*m（真实的余数）):如果n很小，可以分解两半，暴力求每一半的所有可能子数组的累加和，放入一个List中，List加工出每个值模m后的List‘
//        左边部分的数量 + 右边部分的数量 + 左边和右边整合的List
public class Code_0045_SubSeqSumModMMax {
    // 暴力解法
    // 穷举所有子序列的和然后%m取最大值
    public static int max(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        int i = 0;
        int sum = 0;
        process(arr, i, sum, set);
        int max = 0;
        for (int s : set) {
            max = Math.max(max, s % m);
        }
        return max;
    }

    // 0...i-1任意组合，sum收集子序列的累加和
    private static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
        } else {
            process(arr, i + 1, sum + arr[i], set);
            process(arr, i + 1, sum, set);
        }
    }

    // TODO
    public static int max2(int[] arr, int m) {

        return -1;
    }


}
