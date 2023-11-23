package git.snippet.list;

// 反转双向链表
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class Code_ReverseDoubleList {

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode cur = head;
        DoubleNode pre = null;
        while (cur != null) {
            DoubleNode t = cur.next;
            cur.next = pre;
            cur.last = t;
            pre = cur;
            cur = t;
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
