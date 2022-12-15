package 练习题.leetcode.medium;

//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
//        请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
//        示例 1:
//        输入: 1->2->3->4->5->NULL
//        输出: 1->3->5->2->4->NULL
//        示例 2:
//        输入: 2->1->3->5->6->4->7->NULL
//        输出: 2->3->6->7->1->5->4->NULL
//        说明:
//        应当保持奇数节点和偶数节点的相对顺序。
//        链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
//        Leetcode题目 : https://leetcode.cn/problems/odd-even-linked-list/
// 笔记：https://www.cnblogs.com/greyzeng/p/16916683.html
public class LeetCode_0328_OddEvenLinkedList {

    public static class ListNode {
        ListNode next;
    }

    // 奇数节点和偶数节点放在一起
    // 所有偶数下标的数一定要在奇数下标数之后（注意：是下标而非值）
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddStart = null;
        ListNode oddEnd = null;
        ListNode evenStart = null;
        ListNode evenEnd = null;
        ListNode cur = head;
        int count = 1;
        while (cur != null) {
            if ((count & 1) == 1) {
                // 奇数
                if (oddStart == null) {
                    oddStart = cur;
                } else {
                    oddEnd.next = cur;
                }
                oddEnd = cur;
            } else {
                // 偶数
                if (evenStart == null) {
                    evenStart = cur;
                } else {
                    evenEnd.next = cur;
                }
                evenEnd = cur;
            }
            count++;
            cur = cur.next;
        }
        if (evenEnd != null) {
            evenEnd.next = null;
        }
        oddEnd.next = evenStart;
        return oddStart;
    }

}
