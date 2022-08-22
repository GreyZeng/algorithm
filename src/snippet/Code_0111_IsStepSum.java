package snippet;

import java.util.HashMap;

//定义何为step sum？
//		比如680，680 + 68 + 6 = 754，680的step sum叫754
//		给定一个正数num，判断它是不是某个数的step sum
//		二分法，如果
//		x--->甲
//		y--->乙
//		如果x大于y，则甲一定大于乙
//		求中点，然后验证
// https://www.cnblogs.com/greyzeng/p/15690136.html
public class Code_0111_IsStepSum {
    public static boolean isStepSum(int stepSum) {
        int i = 0;
        int j = stepSum;
        while (i <= j) {
            int mid = i + ((j - i) >> 1);
            int value = stepSumOf(mid);
            if (value == stepSum) {
                return true;
            } else if (value < stepSum) {
                i = mid + 1;
            } else {
                // value > stepSum
                j = mid - 1;
            }
        }
        return false;
    }

    public static int stepSumOf(int num) {
        int v = 0;
        while (num != 0) {
            v += num;
            num = num / 10;
        }
        return v;
    }

    public static int stepSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }

    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(stepSum(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 10000000;
        int maxStepSum = stepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}
