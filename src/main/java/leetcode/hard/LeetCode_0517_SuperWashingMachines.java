package leetcode.hard;

//有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打包机上，放到每个机器上的这些物品数量有多有少，
//        由于物品数量不相同，需要工人 将每个机器上的物品进行移动从而到达物品数量相等才能打包。
//        每个物品重量太大、 每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。请计算在搬动最小轮数的前提下，使每个机器上的物品数量相等。
//        如果不能使每个机器上的物品相同， 返回-1。
//        例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后:
//        第一轮:1    0 <- 5 => 1 1 4
//        第二轮:1 <- 1 <- 4 => 2 1 3
//        第三轮:2    1 <- 3 => 2 2 2
//        移动了3轮，每个机器上的物品相等，所以返回3
//        例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品， 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1
//        tips:
//        所有数的累加和 % N != 0, 怎么都无法做到
//        考虑i位置，
//        左有 ，左余
//        右有，右余
//        预处理数组，累加和
// https://leetcode-cn.com/problems/super-washing-machines/
public class LeetCode_0517_SuperWashingMachines {
    public static int findMinMoves(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return 0;
        }
        int sum = 0;
        int size = arr.length;
        for (int item : arr) {
            sum += item;
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            // 左侧还差多少
            int leftRest = leftSum - i * avg;
            // 右侧还差多少
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                // 左侧右侧都差一些才到平均值
                // 此时就需要中间位置向左边和右边都丢一些衣服
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                // 左侧多，右侧少，多的通过中间丢一些到左侧
                // 右侧少，左侧多，多的通过中间丢一些到右侧
                // 左右侧都多，则可以**同时**向中间丢衣服
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
