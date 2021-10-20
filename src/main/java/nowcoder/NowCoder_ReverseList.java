// 链接：https://www.nowcoder.com/questionTerminal/b66a251dec8847f386bbe6cd96b7e9c8
// 来源：牛客网

// 实现反转单向链表和双向链表的函数。
// 如 1->2->3 反转后变成 3->2->1。

// 输入描述:
// 第一行一个整数 n，表示单链表的长度。

// 第二行 n 个整数 val 表示单链表的各个节点。

// 第三行一个整数 m，表示双链表的长度。

// 第四行 m 个整数 val 表示双链表的各个节点。

// 输出描述:
// 在给定的函数内返回相应链表的头指针。
// 示例1
// 输入
// 3
// 1 2 3
// 4
// 1 2 3 4
// 输出
// 3 2 1
// 4 3 2 1

// 备注:
// 1 \le n,m\le10000001≤n,m≤1000000
// -1000000 \le val \le 1000000−1000000≤val≤1000000
package nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NowCoder_ReverseList {
    // 单链表数据结构
    public static class ListNode {
        public int v;
        public ListNode next;

        public ListNode(int v) {
            this.v = v;
        }
    }

    // 双向链表数据结构
    public static class DoubleListNode {
        public int v;
        public DoubleListNode pre;
        public DoubleListNode next;

        public DoubleListNode(int v) {
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l1 = Integer.parseInt(br.readLine().trim());
        String[] sData = br.readLine().trim().split(" ");
        int l2 = Integer.parseInt(br.readLine().trim());
        String[] dData = br.readLine().trim().split(" ");

        // 构建单链表

        ListNode shead = new ListNode(Integer.parseInt(sData[0]));
        ListNode cur = shead;
        for (int i = 1; i < l1; i++) {
            cur.next = new ListNode(Integer.parseInt(sData[i]));
            cur = cur.next;
        }
        printList(reverseList(shead));
        // 构建双向链表
        DoubleListNode dHead = new DoubleListNode(Integer.parseInt(dData[0]));
        DoubleListNode dCur = dHead;
        for (int i = 1; i < l2; i++) {
            DoubleListNode newNode = new DoubleListNode(Integer.parseInt(dData[i]));
            dCur.next = newNode;
            newNode.pre = dCur;
            dCur = newNode;
        }
        printDoubleList(reverseDoubleList(dHead));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }

    public static DoubleListNode reverseDoubleList(DoubleListNode head) {

        DoubleListNode prev = head;
        DoubleListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            DoubleListNode temp = cur.next;
            cur.next = prev;
            prev.pre = cur;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur.next != null) {
            sb.append(cur.v).append(" ");
            cur = cur.next;
        }
        sb.append(cur.v);
        System.out.println(sb.toString());
    }

    public static void printDoubleList(DoubleListNode head) {
        if (head == null) {
            return;
        }
        DoubleListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur.next != null) {
            sb.append(cur.v).append(" ");
            cur = cur.next;
        }
        sb.append(cur.v);
        System.out.println(sb.toString());
    }
}
