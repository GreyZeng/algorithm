package lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://www.lintcode.com/problem/1202/
public class LintCode_1202_IPO {
    public class Project {
        public int profit;
        public int capital;

        public Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        k = Math.min(k, Profits.length);
        PriorityQueue<Project> min = new PriorityQueue<>(Comparator.comparingInt(o -> o.capital));
        PriorityQueue<Project> max = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        for (int i = 0; i < Profits.length; i++) {
            min.offer(new Project(Profits[i], Capital[i]));
        }
        int maxProfits = W;
        while (k > 0) {
            while (!min.isEmpty() && min.peek().capital <= maxProfits) {
                // 所有可以做的项目都拿出来
                max.offer(min.poll());
            }
            if (!max.isEmpty()) {
                // 每次做一个项目收益的增加
                maxProfits += max.poll().profit;
                // 消费掉一个项目的名额
                k--;
            } else {
                break;
            }
        }
        return maxProfits;
    }
}
