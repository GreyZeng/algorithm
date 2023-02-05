package 算法.约瑟夫环;

// TODO
// 约瑟夫环问题
// 给定一个链表头节点head，和一个正数m
// 从头开始，每次数到m就杀死当前节点
// 然后被杀节点的下一个节点从1开始重新数，
// 周而复始直到只剩一个节点，返回最后的节点
// Leetcode :
// https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
//
// 约瑟夫环问题
//
// 现在一共有i个节点，数到m就杀死节点，最终会活下来的节点，请返回它在有i个节点时候的编号
//
// 暴力方法:
// 如果总共有N个节点, 要杀死N-1个节点, 要杀死的每一个节点都要数到K个，所以暴力方法的复杂度是： O(N*K)
//
// 可以优化到：O(N)
//
// tips 考虑到最后情况，只剩一个节点，这一个节点在自己的小链表中的编号就是1号节点，当只剩1个节点的时候, 活着的编号就是1，
//
// 假设我有一个公式
//
// ```
// f(留下后的编号) = 杀之前的编号,
// ```
//
// 这个公式可以算出杀之前的编号 那最后一个节点1, 调一下这个函数, 就能算出2个节点时候，活着的编号是多少,再调一下这个函数, 就能算出3个节点时候，活着的编号是多少，依次往上调用,
// 直到N个节点, 一个节点也不少的时候,
// 算出来的就是最后活着的编号
//
// 启发： 剃刀函数 y = x % i
//
// 环形链表中，报号和数字之间的对应的关系 号= （数-1)%i + 1
//
// 旧：1234567 新：56X1234 然后画图找规律 旧 = （新+ S-1）%i+1
//
// S->原链表被杀的编号
// S = (M-1)%i+1
// i->杀之前的长度
//
// --> (新 + (m-1)) % i + 1
//
// LeetCodeCN_0062_Josephus.java
//
// 扩展
//
// 给参数N, 表示从1..N有这么多人, 坐成一个圈, 一起玩游戏. 每一次从一个数组中取一个数字, 当数到这个数字的人别杀掉. 比如:[3,1,5,2,7]
// 第一次数到3的人会被杀掉 下面从4开始取到1, 4被杀掉 最后只有1留下来
//
// 当数字不够用的时候,回到开头继续取数字, 问最后编号为多少的人能活下来.
public class LeetCodeCN_0062_Josephus {
    // 提交直接通过
    // 给定的编号是0~n-1的情况下，数到m就杀
    // 返回谁会活？
    public int lastRemaining1(int n, int m) {
        return getLive(n, m) - 1;
    }

    // 以下设定是：给定的编号是1~n的情况下，数到m就杀
    // 返回谁会活？
    public static int getLive(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (getLive(n - 1, m) + m - 1) % n + 1;
    }

    // 给定的编号是0~n-1的情况下，数到m就杀
    // 返回谁会活？
    // 这个版本是迭代版
    public int lastRemaining2(int n, int m) {
        int ans = 1;
        int r = 1;
        while (r <= n) {
            ans = (ans + m - 1) % (r++) + 1;
        }
        return ans - 1;
    }

    // 以下的code针对单链表，不要提交
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node josephusKill1(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    public static Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int size = 1; // tmp -> list size
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        int live = getLive(size, m); // tmp -> service node position
        while (--live != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = josephusKill1(head1, 3);
        printCircularList(head1);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKill2(head2, 3);
        printCircularList(head2);

    }

}
