package 练习题.snippet;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。

// 思路：
// 第一步：先想办法得到一个数组，获取每个人喝咖啡的最少时间数组（小根堆 能用时间+多久制作的时间）
// 第二步：process(i,free) 从i号咖啡杯开始，一直到最后，最短时间是？其中free是咖啡杯可用的时间
public class Code_MakeCoffee {


    public static int minTime0(int[] arr, int n, int wash, int air) {
        int[] drinks = arrangeQueue(arr, n);
        return process(drinks, 0, wash, air, 0);
    }


    public static class CoffeeMaker {
        // 制作一杯咖啡的时间
        public int makeTime;
        // 什么时间点可以制作咖啡，初始状态都是0
        public int freeTime;

        public CoffeeMaker(int m, int f) {
            makeTime = m;
            freeTime = f;
        }
    }

    // 安排每个人的排队次序
    public static int[] arrangeQueue(int[] arr, int n) {
        PriorityQueue<CoffeeMaker> heap =
                new PriorityQueue<>(Comparator.comparingInt(o -> (o.freeTime + o.makeTime)));
        for (int makeTime : arr) {
            // 初始状态下，咖啡机都是有空的
            heap.add(new CoffeeMaker(makeTime, 0));
        }
        int[] queue = new int[n];
        for (int i = 0; i < n; i++) {
            CoffeeMaker maker = heap.poll();
            // 有空的时间点+制作咖啡的时间点，就是咖啡机下一次有空的时间点
            maker.freeTime += maker.makeTime;
            queue[i] = maker.freeTime;
            heap.offer(maker);
        }
        return queue;
    }


    // 从drinks[i....]都变干净的时间是
    // 其中free是咖啡杯可用的时间
    // wash，洗咖啡时间
    // air，挥发一杯咖啡时间
    // free咖啡机可用时间
    public static int process(int[] drinks, int i, int wash, int air, int free) {
        if (i == drinks.length) {
            return 0;
        }
        // 当前杯子决定洗
        int self = Math.max(drinks[i], free) + wash;
        int restTime = Math.max(self, process(drinks, i + 1, wash, air, self));
        int p1 = Math.max(self, restTime);
        self = drinks[i] + air;
        restTime = Math.max(self, process(drinks, i + 1, wash, air, free));
        int p2 = Math.max(self, restTime);
        return Math.min(p1, p2);
    }

    public static int minTimeDp(int[] arr, int n, int wash, int air) {
        int[] drinks = arrangeQueue(arr, n);
        // i 变化范围 0...drinks.length
        // free变化范围: 所有杯子都用洗咖啡机器洗的时间能冲到的最大时间
        int maxTime = washMaxTime(drinks, wash);
        int[][] dp = new int[drinks.length + 1][maxTime + 1];
        // 最后一行都是0
        for (int i = drinks.length - 1; i >= 0; i--) {
            for (int j = 0; j < maxTime + 1; j++) {
                int self = Math.max(drinks[i], j) + wash;
                if (self > maxTime) {
                    break;
                }
                int restTime = Math.max(self, dp[i + 1][self]);
                int p1 = Math.max(self, restTime);
                self = drinks[i] + air;
                restTime = Math.max(self, dp[i + 1][j]);
                int p2 = Math.max(self, restTime);
                dp[i][j] = Math.min(p1, p2);
            }
        }
        // 当前杯子决定洗

        return dp[0][0];
    }

    // 咖啡机可用的最大时间
    // 所有杯子都用洗咖啡机器洗的时间能冲到的最大时间
    private static int washMaxTime(int[] drinks, int wash) {
        int free = 0;
        for (int drink : drinks) {
            free = Math.max(free, drink) + wash;
        }
        return free;
    }

    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime0(arr, n, a, b);
            int ans3 = minTimeDp(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

}
