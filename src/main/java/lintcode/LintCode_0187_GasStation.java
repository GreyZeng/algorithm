package lintcode;
// 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油gas[i]，并且从第_i_个加油站前往第_i_+1个加油站需要消耗汽油cost[i]。

// 你有一辆油箱容量无限大的汽车，现在要从某一个加油站出发绕环路一周，一开始油箱为空。

// 求可环绕环路一周时出发的加油站的编号，若不存在环绕一周的方案，则返回-1。

// 数据保证答案唯一。
// https://www.lintcode.com/problem/187/
public class LintCode_0187_GasStation {
    // 暴力解法
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = cost.length;
        int[] helper = new int[len];
        for (int i = 0; i < helper.length; i++) {
            helper[i] = gas[i] - cost[i];
        }
        int pre = 0;
        for (int i = 0; i < len; i++) {
            pre = helper[i];
            if (pre < 0) {
                continue;
            }
            for (int j = i + 1; j < len + i + 1; j++) {
                pre += helper[j < len ? j : (j - len)];
                if (pre < 0) {
                    break;
                }
            }
            if (pre >= 0) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] gas = { 0,2,0,100};
        int[] cost = { 20,1,20,0 };
        int f = new LintCode_0187_GasStation().canCompleteCircuit(gas, cost);
       System.out.println(f);
    }
}
