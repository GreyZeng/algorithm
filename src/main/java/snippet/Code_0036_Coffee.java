/*arr中记录咖啡机制造一杯咖啡的时间，假设有m个人，都在0号时间点开始排队，返回一个数组
        最后一个得到咖啡的人的时间尽可能短
        tips:
        小根堆 时间点+制造咖啡的时间
        弹出，制造，修改咖啡机的开始时间，再加入小根堆*/
package snippet;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Young
 * @version 1.0
 * @date 2021/2/12 16:00
 */
public class Code_0036_Coffee {
    public static class CoffeeMachine {
        public int start;
        public int work;

        public CoffeeMachine(int s, int w) {
            start = s;
            work = w;
        }
    }

    public static class CoffeeMachineComparator implements Comparator<CoffeeMachine> {

        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return o1.start + o1.work - o2.start - o2.work;
        }
    }

    public static int[] bestChoices(int[] arr, int M) {
        int[] ans = new int[M];
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new CoffeeMachineComparator());
        for (int coffeeWork : arr) {
            // 制造咖啡最短时间的咖啡机在堆顶
            heap.add(new CoffeeMachine(0, coffeeWork));
        }
        for (int i = 0; i < M; i++) {
            CoffeeMachine cur = heap.poll();
            // 第i号人使用cur这个咖啡机，所以cur这个咖啡机的开始时间变为cur.start + cur.work
            ans[i] = cur.start + cur.work;
            cur.start = ans[i];
            heap.add(cur);
        }
        return ans;
    }
}
