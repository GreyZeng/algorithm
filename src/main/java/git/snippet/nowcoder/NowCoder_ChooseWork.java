package git.snippet.nowcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;

// *
// * 给定数组hard和money，长度都为N
// * hard[i]表示i号的难度， money[i]表示i号工作的收入
// * 给定数组ability，长度都为M，ability[j]表示j号人的能力
// * 每一号工作，都可以提供无数的岗位，难度和收入都一样
// * 但是人的能力必须>=这份工作的难度，才能上班
// * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
// * tips
// * 排序
// * 难度一样，只保留最高报酬的
// * 难度更大，报酬却更小的也删掉
// * 然后加入有序表，找离自己最近的最大的值
// * https://www.nowcoder.com/questionTerminal/5e1b251c90ba4e6982cb349076ed4449
public class NowCoder_ChooseWork {
    public static int[] getMoneys(Job[] job, int[] ability) {
        Arrays.sort(job, new JobComparator());
        // key : 难度 value：报酬
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hard, job[0].money);
        // pre : 上一份进入map的工作
        Job pre = job[0];
        for (int i = 1; i < job.length; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                map.put(pre.hard, pre.money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // ability[i] 当前人的能力 <= ability[i] 且离它最近的
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int jobNum = in.nextInt();
        int peopleNum = in.nextInt();
        Job[] jobs = new Job[jobNum];
        for (int i = 0; i < jobNum; i++) {
            int hard = in.nextInt();
            int money = in.nextInt();
            jobs[i] = new Job(money, hard);
        }
        int[] abilities = new int[peopleNum];
        for (int i = 0; i < peopleNum; i++) {
            int ability = in.nextInt();
            abilities[i] = ability;
        }
        int[] money = getMoneys(jobs, abilities);
        for (int j : money) {
            System.out.println(j);
        }
        in.close();
    }

    public static class Job {
        public int money;
        public int hard;

        public Job(int m, int h) {
            money = m;
            hard = h;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }
}
