// Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture
// Capital,
// LeetCode would like to work on some projects to increase its capital before the IPO.
// Since it has limited resources, it can only finish at most k distinct projects before the IPO.
// Help LeetCode design the best way to maximize its total capital after finishing at most k
// distinct projects.
//
// You are given several projects. For each project i, it has a pure profit Pi and a minimum capital
// of Ci is needed to start the corresponding project.
// Initially, you have W capital. When you finish a project, you will obtain its pure profit and the
// profit will be added to your total capital.
//
// To sum up, pick a list of at most k distinct projects from given projects to maximize your final
// capital, and output your final maximized capital.
//
// Example 1:
// Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
//
// Output: 4
//
// Explanation: Since your initial capital is 0, you can only start the project indexed 0.
// After finishing it you will obtain profit 1 and your capital becomes 1.
// With capital 1, you can either start the project indexed 1 or the project indexed 2.
// Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the
// maximum capital.
// Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
// Note:
// You may assume all numbers in the input are non-negative integers.
// The length of Profits array and Capital array will not exceed 50,000.
// The answer is guaranteed to fit in a 32-bit signed integer.
package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://www.lintcode.com/problem/1202/
// 笔记：https://www.cnblogs.com/greyzeng/p/16704842.html
public class LeetCode_0502_IPO {
    // k项目个数
    // W初始资金
    // Profits收益
    // Capital花费
    // 所有花费可以cover的项目中，取最大收益的项目
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (k == 0) {
            return W;
        }
        if (Profits.length == 0) {
            return W;
        }
        k = Math.min(Profits.length, k); // 因为项目无法重复做，所以k最大只能是项目个数
        Project[] projects = initProjects(Profits, Capital);
        PriorityQueue<Project> min =
                new PriorityQueue<>(Comparator.comparingInt((Project o) -> o.capital));
        PriorityQueue<Project> max = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);

        for (Project project : projects) {
            min.offer(project);
        }
        int maxProfit = W;
        while (k > 0) {
            while (!min.isEmpty() && min.peek().capital <= W) {
                max.offer(min.poll());
            }
            if (!max.isEmpty()) {
                maxProfit += max.poll().profit;
                k--;
                W = maxProfit;
            } else {
                break;
            }
        }
        return maxProfit;
    }

    private static Project[] initProjects(int[] profits, int[] capital) {
        Project[] projects = new Project[profits.length];
        for (int i = 0; i < profits.length; i++) {
            projects[i] = new Project(profits[i], capital[i]);
        }
        return projects;
    }

    // 暴力方法
    // k: 可以做的项目数量
    // W: 初始资金
    // Profits: 每个项目的预期收益
    // Capital: 每个项目的花费
    public static int findMaximizedCapital0(int k, int W, int[] Profits, int[] Capital) {
        List<Integer> p = new ArrayList<>();
        process0(k, W, Profits, Capital, p);
        int max = 0;
        for (int i : p) {
            max = Math.max(max, i);
        }
        return max;
    }

    // 穷举每种做法获得的收益放在p中
    private static void process0(int k, int w, int[] profits, int[] capital, List<Integer> p) {
        if (k == 0) {
            p.add(w);
        } else {
            k = Math.min(capital.length, k); // k必须小于或者等于项目数量
            if (!enough(w, capital)) { // 如果w没办法cover任何一个项目，直接返回
                p.add(w);
                return;
            }
            for (int i = 0; i < capital.length; i++) {
                if (capital[i] <= w) {
                    process0(k - 1, w + profits[i], copyExcept(profits, i), copyExcept(capital, i), p);
                }
            }
        }
    }

    private static boolean enough(int w, int[] capital) {
        boolean enough = false;
        for (int i : capital) {
            if (w >= i) {
                enough = true;
                break;
            }
        }
        return enough;
    }

    private static int[] copyExcept(int[] profits, int i) {
        int[] n = new int[profits.length - 1];
        int inx = 0;
        for (int j = 0; j < profits.length; j++) {
            if (j != i) {
                n[inx++] = profits[j];
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int times = 10000;
        int maxSize = 8;
        int maxValue = 19;
        for (int i = 0; i < times; i++) {
            int[] profits = generateArr(maxSize, maxValue);
            int[] capital = generateConstantSizeArr(profits.length, maxValue);
            int k = (int) (Math.random() * (maxSize + 1));
            int W = (int) (Math.random() * (maxValue + maxValue + 1));
            if (findMaximizedCapital(k, W, profits, capital)
                    != findMaximizedCapital0(k, W, profits, capital)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish");
    }

    private static int[] generateConstantSizeArr(int length, int maxValue) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    private static int[] generateArr(int maxSize, int maxValue) {
        return generateConstantSizeArr((int) (Math.random() * (maxSize)) + 1, maxValue);
    }

    public static class Project {
        public int profit;
        public int capital;

        public Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }
}
