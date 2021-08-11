package nowcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 派对的最大快乐值
 * <p>
 * 员工信息的定义如下: class Employee { public int happy; // 这名员工可以带来的快乐值 List<Employee>
 * subordinates; // 这名员工有哪些直接下级 }
 * <p>
 * 派对的最大快乐值 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、
 * 没有环的多叉树。树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * <p>
 * 派对的最大快乐值 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则： 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加 3.你的目标是让派对的整体快乐值尽量大 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
// https://ac.nowcoder.com/acm/problem/51178
public class NowCoder_MaxHappy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        Map<Integer, Employee> map = new HashMap<>();
        List<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= count; i++) {
            int happy = sc.nextInt();
            map.put(i, new Employee(happy));
            tmp.add(i);
        }
        Set<Integer> notBoss = new HashSet<>();
        for (int i = 1; i <= count; i++) {
            if (i != count) {
                int child = sc.nextInt();
                int father = sc.nextInt();
                notBoss.add(child);
                Employee f = map.get(father);
                Employee c = map.get(child);
                f.subordinates.add(c);
            }
        }
        int bossIndex = 0;
        for (Integer it : tmp) {
            if (!notBoss.contains(it)) {
                bossIndex = it;
                break;
            }
        }
        Employee boss = map.get(bossIndex);
        System.out.println(maxHappy(boss));
        sc.close();
    }

    public static class Employee {
        public int happy;
        public List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            this.subordinates = new ArrayList<>();
        }
    }


    public static class Info {
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int maxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info info = p(boss);
        return Math.max(info.yes, info.no);
    }

    public static Info p(Employee boss) {
        if (boss.subordinates == null || boss.subordinates.isEmpty()) {
            return new Info(boss.happy, 0);
        }
        List<Employee> subordinates = boss.subordinates;
        int yes = boss.happy;
        int no = 0;
        for (Employee e : subordinates) {
            Info info = p(e);
            // boss参加了,下属可以不参加
            yes += info.no;
            // boss没有参加,下属可以参加也可以不参加
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes, no);
    }


}
