package leetcode;

import java.util.LinkedList;

//There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
//        You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
//
//        Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
//
//        Note:
//
//        If there exists a solution, it is guaranteed to be unique.
//        Both input arrays are non-empty and have the same length.
//        Each element in the input arrays is a non-negative integer.
//        Example 1:
//
//        Input:
//        gas  = [1,2,3,4,5]
//        cost = [3,4,5,1,2]
//
//        Output: 3
//
//        Explanation:
//        Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//        Travel to station 4. Your tank = 4 - 1 + 5 = 8
//        Travel to station 0. Your tank = 8 - 2 + 1 = 7
//        Travel to station 1. Your tank = 7 - 3 + 2 = 6
//        Travel to station 2. Your tank = 6 - 4 + 3 = 5
//        Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
//        Therefore, return 3 as the starting index.
//        Example 2:
//
//        Input:
//        gas  = [2,3,4]
//        cost = [3,4,3]
//
//        Output: -1
//
//        Explanation:
//        You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
//        Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//        Travel to station 0. Your tank = 4 - 3 + 2 = 3
//        Travel to station 1. Your tank = 3 - 3 + 3 = 3
//        You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
//        Therefore, you can't travel around the circuit once no matter where you start.
public class LeetCode_0134_GasStation {

    // 滑动窗口内的最大值和，最小值（双端队列，存下标，不要存值）
    /*
     * 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
     */
    // tips, 纯能值数组（本身加油站扣去距离，还剩多少?） 累加没有小于0的，就是良好出发点
    // 纯能值数组的前缀和数组
    // 2倍的前缀和数组（考察窗口最小值是不是小于0）
    // 生成h(i) 的累加和数组
    //纯能职数组[1,-1,0,3,-1]
    //--> 累加和数组 [1,0,0,3,2]
    //---> 再累加一次 [1,0,0,3,2,3,2,2,5,4]
    //然后滑动窗口最小值，减去L-1位置的数，如果<0,则L不是良好出发点
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int doubleLen = len << 1;
        int[] h = new int[doubleLen];
        h[0] = gas[0] - cost[0];
        for (int i = 1; i < doubleLen; i++) {
            if (i < len) {
                h[i] = gas[i] - cost[i];
                h[i] += h[i - 1];
            }
            if (i >= len) {
                h[i] = h[len - 1] + h[i - len];
            }
        }
        LinkedList<Integer> qMin = new LinkedList<>();
        int r = 0;
        int index = 0;
        while (r < doubleLen) {
            while (!qMin.isEmpty() && h[qMin.peekLast()] >= h[r]) {
                qMin.pollLast();
            }
            qMin.addLast(r);
            if (qMin.peekFirst() == r - len) {
                qMin.pollFirst();
            }
            if (r >= len - 1) {
                if (r == len - 1) {
                    if (h[qMin.peekFirst()] >= 0) {
                        return index;
                    }
                } else {
                    if (h[qMin.peekFirst()] - h[r - len] >= 0) {
                        return index;
                    }
                }
                index++;
            }
            r++;
        }
        return -1;
    }

    /*
     * TODO
     *  这个方法的时间复杂度O(N)，额外空间复杂度O(1）
     */
    public  static boolean[] stations(int[] cost, int[] gas) {
        if (cost == null || gas == null || cost.length < 2
                || cost.length != gas.length) {
            return null;
        }
        int init = changeDisArrayGetInit(cost, gas);
        return init == -1 ? new boolean[cost.length] : enlargeArea(cost, init);
    }

    public static int changeDisArrayGetInit(int[] dis, int[] oil) {
        int init = -1;
        for (int i = 0; i < dis.length; i++) {
            dis[i] = oil[i] - dis[i];
            if (dis[i] >= 0) {
                init = i;
            }
        }
        return init;
    }

    public static boolean[] enlargeArea(int[] dis, int init) {
        boolean[] res = new boolean[dis.length];
        int start = init;
        int end = nextIndex(init, dis.length);
        int need = 0;
        int rest = 0;
        do {
            // 当前来到的start已经在连通区域中，可以确定后续的开始点一定无法转完一圈
            if (start != init && start == lastIndex(end, dis.length)) {
                break;
            }
            // 当前来到的start不在连通区域中，就扩充连通区域
            if (dis[start] < need) { // 当前start无法接到连通区的头部
                need -= dis[start];
            } else { // 当前start可以接到连通区的头部，开始扩充连通区域的尾巴
                rest += dis[start] - need;
                need = 0;
                while (rest >= 0 && end != start) {
                    rest += dis[end];
                    end = nextIndex(end, dis.length);
                }
                // 如果连通区域已经覆盖整个环，当前的start是良好出发点，进入2阶段
                if (rest >= 0) {
                    res[start] = true;
                    connectGood(dis, lastIndex(start, dis.length), init, res);
                    break;
                }
            }
            start = lastIndex(start, dis.length);
        } while (start != init);
        return res;
    }

    // 已知start的next方向上有一个良好出发点
    // start如果可以达到这个良好出发点，那么从start出发一定可以转一圈
    public static void connectGood(int[] dis, int start, int init, boolean[] res) {
        int need = 0;
        while (start != init) {
            if (dis[start] < need) {
                need -= dis[start];
            } else {
                res[start] = true;
                need = 0;
            }
            start = lastIndex(start, dis.length);
        }
    }

    public static int lastIndex(int index, int size) {
        return index == 0 ? (size - 1) : index - 1;
    }

    public static int nextIndex(int index, int size) {
        return index == size - 1 ? 0 : (index + 1);
    }

    // 暴力解法 O(N^2)
    public static int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length;

        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = gas[i] - cost[i];
        }
        // 标记良好出发点的位置，开始是-1，说明没有找到良好出发点
        int good = -1;
        // h[i] 一直往后累加，累加和记录在preSum中，回到本身，如果不出现负数，i位置就是良好出发点
        int preSum;
        for (int i = 0; i < n; i++) {
            preSum = h[i];
            for (int j = i + 1; j < n + i + 1; j++) {
                if (preSum < 0) {
                    break;
                }
                // int index = j % n
                int index = j > n - 1 ? j - n : j;
                preSum += h[index];
            }
            if (preSum >= 0) {
                good = i;
            }
        }
        return good;
    }


    // 返回所有位置是不是良好出发点
    public static boolean[] canCompleteCircuitOfAllPositions(int[] gas, int[] cost) {
        int N = gas.length;
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            h[i] = gas[i] - cost[i];
        }
        int R = N << 1;
        int[] doubleLenOfHelper = new int[R];
        doubleLenOfHelper[0] = h[0];
        for (int i = 1; i < N; i++) {
            doubleLenOfHelper[i] = h[i] + doubleLenOfHelper[i - 1];
        }
        for (int i = 0; i < N; i++) {
            doubleLenOfHelper[i + N] = h[i] + doubleLenOfHelper[i + N - 1];
        }
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] res = new boolean[R - N + 1];
        int index = 0;
        for (int i = 0; i < R; i++) {
            while (!q.isEmpty() && doubleLenOfHelper[i] <= doubleLenOfHelper[q.peekLast()]) {
                q.pollLast();
            }
            q.addLast(i);
            if (q.peekFirst() == (i - N)) {
                q.pollFirst();
            }
            // 窗口已经形成了
            if (i >= N - 1) {
                if (i == N - 1) {
                    res[index++] = (doubleLenOfHelper[q.peekFirst()] >= 0);
                } else {
                    res[index++] = ((doubleLenOfHelper[q.peekFirst()] - doubleLenOfHelper[i - N]) >= 0);
                }
            }
        }
        // res[i]位置存着每个位置是否为良好出发点
        return res;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
//        System.out.println(canCompleteCircuit3(gas, cost));
//        System.out.println(canCompleteCircuit(gas, cost));


        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println(canCompleteCircuit3(gas2, cost2));
        // System.out.println(canCompleteCircuitOfAllPositions(gas2, cost2));
        System.out.println(canCompleteCircuit(gas2, cost2));
    }

}
