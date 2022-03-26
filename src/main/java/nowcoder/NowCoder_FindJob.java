package nowcoder;

import java.util.*;

/**
 * @author GreyZeng
 * 给定数组hard和money，长度都为N
 * hard[i]表示i号的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力
 * 每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班
 * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 * tips
 * 排序
 * 难度一样，只保留最高报酬的
 * 难度更大，报酬却更小的也删掉
 * 然后加入有序表，找离自己最近的最大的值
 * https://www.nowcoder.com/questionTerminal/5e1b251c90ba4e6982cb349076ed4449
 */
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

    public static int[] findJob(Job[] jobs, int[] ability) {
        // 按报酬从高到低排序，如果报酬一样，难度从低到高排序
        Arrays.sort(jobs, (o1, o2) -> o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money);
        Job pre = jobs[0];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(pre.hard, pre.money);
        for (int i = 1; i < jobs.length; i++) {
            int preMoney = pre.money;
            int curHard = jobs[i].hard;
            int curMoney = jobs[i].money;
            if (curMoney > preMoney) {
                map.put(curHard, curMoney);
                pre = jobs[i];
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer value = map.floorKey(ability[i]);
            ans[i] = value == null ? 0 : map.get(value);
        }
        return ans;
    }
}
