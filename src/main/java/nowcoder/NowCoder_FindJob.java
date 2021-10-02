//每种工作有难度和报酬，规定如下
//class Job {
//    public int money;// 该工作的报酬
//    public int hard; // 该工作的难度
//}
//给定一个Job类型的数组jobarr，表示所有岗位，每个岗位都可以提供任意份工作
//        选工作的标准是在难度不超过自身能力值的情况下，选择报酬最高的岗位
//        给定一个int类型的数组arr，表示所有人的能力
//        返回int类型的数组，表示每个人按照标准选工作后所能获得的最高报酬
// 链接：https://www.nowcoder.com/questionTerminal/5e1b251c90ba4e6982cb349076ed4449
//来源：牛客网
//
//为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。牛牛选工作的标准是在难度不超过自身能力值的情况下，牛牛选择报酬最高的工作。
// 在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，牛牛依然使用自己的标准来帮助小伙伴们。牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
//
//输入描述:
//每个输入包含一个测试用例。
//每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
//接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
//接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
//保证不存在两项工作的报酬相同。
//
//
//输出描述:
//对于每个小伙伴，在单独的一行输出一个整数表示他能得到的最高报酬。如果他找不到工作，则输出0。一个工作可以被多个人选择。
//示例1
//输入
//3 3
//1 100
//10 1000
//1000000000 1001
//9 10 1000000000
//输出
//100
//1000
//1001
//tips:
//        排序
//        难度一样，只保留最高报酬的
//        难度更大，报酬却更小的也删掉
//        然后加入有序表，找离自己最近的最大的值
package nowcoder;

import java.util.*;

// https://www.nowcoder.com/questionTerminal/5e1b251c90ba4e6982cb349076ed4449
public class NowCoder_FindJob {
    public static class Job {
        public int money;
        public int hard;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int jobNum = in.nextInt();
        int peopleNum = in.nextInt();
        Job[] jobs = new Job[jobNum];
        for (int i = 0; i < jobNum; i++) {
            int hard = in.nextInt();
            int money = in.nextInt();
            jobs[i] = new Job(hard, money);
        }
        int[] abilities = new int[peopleNum];
        for (int i = 0; i < peopleNum; i++) {
            int ability = in.nextInt();
            abilities[i] = ability;
        }
        int[] money = findJob(jobs, abilities);
        for (int j : money) {
            System.out.println(j);
        }
        in.close();
    }

    public static int[] findJob(Job[] jobs, int[] abilities) {
        int[] values = new int[abilities.length];
        // 难度低的排前面
        // 难度相同的时候收益高的排前面
        Arrays.sort(jobs, (o1, o2) -> {
            if (o1.hard == o2.hard) {
                return Integer.compare(o2.money, o1.money);
            } else {
                return o1.hard > o2.hard ? 1 : -1;
            }
        });
        // 经过上述排序，jobs[0]的数据一定是难度最低的，且收益相对高的
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        int preMoney = jobs[0].money;
        for (int i = 1; i < jobs.length; i++) {
            // 只有比这个收益高的才加入，否则没必要加入
            if (preMoney < jobs[i].money) {
                map.put(jobs[i].hard, jobs[i].money);
                preMoney = jobs[i].money;
            }
        }
        for (int i = 0; i < abilities.length; i++) {
            Integer m = map.floorKey(abilities[i]);
            if (m == null) {
                values[i] = 0;
            } else {
                values[i] = map.get(m);
            }
        }
        return values;
    }
}
