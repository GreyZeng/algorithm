

package d链表;

// TODO
// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0025_ReverseNodesInKGroup {

    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode end = getKGroupEnd(cur, k);
        if (end == null) {
            return head;
        }
        // 第一组反转后的结尾，就是整个反转链表的头
        head = end;
        // 第一次反转
        reverse(cur, end);
        ListNode newStart = cur;
        while (newStart.next != null) {
            // 是否存在下一次反转
            cur = newStart.next;
            end = getKGroupEnd(cur, k);
            if (end == null) {
                break;
            }
            reverse(cur, end);
            newStart.next = end;
            newStart = cur;
        }
        return head;
    }

    // 从start开始，数够k个，然后返回。
    // 例如：
    // start->b->c->d->e
    // k = 3
    // 从start开始，数够3个，所以返回c节点
    // start->b->c
    // k = 6
    // 不够6个，所以返回 null
    public static ListNode getKGroupEnd(ListNode start, int k) {
        if (start == null) {
            return null;
        }
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // ....->a->b->c->d->e....
    // 假设start = a, end = d
    // 经过如下方法，会变成
    // ...d->c->b->a->e.....
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        while (cur != end) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        start.next = end;
    }

}
