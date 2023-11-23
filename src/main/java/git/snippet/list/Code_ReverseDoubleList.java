package git.snippet.list;

// 反转双向链表
// 笔记：https://www.cnblogs.com/greyzeng/p/17852345.html
public class Code_ReverseDoubleList {

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode pre = null;
        DoubleNode cur = head;
        while (cur != null) {
            DoubleNode tmp = cur.next;
            cur.next = pre;
            cur.last = tmp;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }


    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }
}
