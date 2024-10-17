package git.snippet.snippet;

import java.util.PriorityQueue;

/**
 * 笔记：https://www.cnblogs.com/greyzeng/p/16826549.html arr中记录咖啡机制造一杯咖啡的时间，假设有m个人，都在0号时间点开始排队，返回一个数组
 * 最后一个得到咖啡的人的时间尽可能短 tips: 小根堆 时间点+制造咖啡的时间 弹出，制造，修改咖啡机的开始时间，再加入小根堆
 *
 * @author Young
 * @version 1.0
 * @date 2021/2/12 16:00
 */
public class Code_Coffee {
    public static int[] bestChoices(int[] arr, int m) {
        int[] ans = new int[m];
        PriorityQueue<CoffeeMachine> heap =
                new PriorityQueue<>((o1, o2) -> o1.start + o1.work - o2.start - o2.work);
        for (int coffeeWork : arr) {
            // 制造咖啡最短时间的咖啡机在堆顶
            heap.add(new CoffeeMachine(0, coffeeWork));
        }
        for (int i = 0; i < m; i++) {
            CoffeeMachine cur = heap.poll();
            // 第i号人使用cur这个咖啡机，所以cur这个咖啡机的开始时间变为cur.start + cur.work
            System.out.println(i + " 号人使用 " + cur + "咖啡机");
            ans[i] = cur.start + cur.work;
            System.out.println(i + " 号人在 [" + cur.start + "] 时刻搞定完一杯咖啡");
            cur.start = ans[i];
            heap.add(cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        int m = 5;
        int[] arr = {2, 3, 5};
        bestChoices(arr, m);
    }

    public static class CoffeeMachine {
        public int start;
        public int work;
        public CoffeeMachine(int s, int w) {
            start = s;
            work = w;
        }

        @Override
        public String toString() {
            return "CoffeeMachine{" + "start=" + start + ", work=" + work + '}';
        }
    }
}
