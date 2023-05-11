package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 笔记：https://www.cnblogs.com/greyzeng/p/16748043.html 派对的最大快乐值
 * <p>
 * 员工信息的定义如下:
 *
 * <pre>
 * class Employee {
 *   public int happy; // 这名员工可以带来的快乐值
 *   List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 * </pre>
 * <p>
 * 派对的最大快乐值 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
 * <p>
 * 树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 * <p>
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * <p>
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，
 * <p>
 * 规则：
 * <p>
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * <p>
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * <p>
 * 3.你的目标是让派对的整体快乐值尽量大 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
// https://ac.nowcoder.com/acm/problem/51178
public class NowCoder_MaxHappy {
    public static class Employee {
        public int happy;
        public List<Employee> subordinates;

        public Employee(int h) {
            happy = h;
            subordinates = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.subordinates) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.subordinates) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    public static int maxHappy2(Employee head) {
        if (head == null) {
            return 0;
        }
        Info p = p(head);
        return Math.max(p.yes, p.no);
    }

    public static Info p(Employee head) {
        if (head == null) {
            return new Info(0, 0);
        }
        int yes = head.happy;
        int no = 0;
        for (Employee n : head.subordinates) {
            Info next = p(n);
            no += Math.max(next.no, next.yes);
            yes += next.no;
        }
        return new Info(yes, no);
    }

    public static class Info {
        private int no;
        private int yes;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts,
                                     int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
