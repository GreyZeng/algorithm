package git.snippet.list;

// 链表上实现荷兰国旗问题
// 笔记：https://www.cnblogs.com/greyzeng/p/16923068.html
public class Code_PartitionList {

    public static ListNode listPartition1(ListNode head, int pivot) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        ListNode[] listNodeArr = new ListNode[i];
        cur = head;
        for (i = 0; i != listNodeArr.length; i++) {
            listNodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(listNodeArr, pivot);
        for (i = 1; i != listNodeArr.length; i++) {
            listNodeArr[i - 1].next = listNodeArr[i];
        }
        listNodeArr[i - 1].next = null;
        return listNodeArr[0];
    }

    public static void arrPartition(ListNode[] listNodeArr, int pivot) {
        int small = -1;
        int big = listNodeArr.length;
        int index = 0;
        while (index != big) {
            if (listNodeArr[index].val < pivot) {
                swap(listNodeArr, ++small, index++);
            } else if (listNodeArr[index].val == pivot) {
                index++;
            } else {
                swap(listNodeArr, --big, index);
            }
        }
    }

    public static void swap(ListNode[] listNodeArr, int a, int b) {
        ListNode tmp = listNodeArr[a];
        listNodeArr[a] = listNodeArr[b];
        listNodeArr[b] = tmp;
    }

    public static ListNode listPartition2(ListNode head, int pivot) {
        ListNode sH = null; // small head
        ListNode sT = null; // small tail
        ListNode eH = null; // equal head
        ListNode eT = null; // equal tail
        ListNode mH = null; // big head
        ListNode mT = null; // big tail
        ListNode next; // save next node
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.val == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // all reconnect
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        // 如果小于区域有，小于区域的头就是最终链表的头
        // 如果小于区域没有，等于区域的头有，则等于区域的头就是最终链表的头
        // 如果小于和等于区域都没有，则大于区域的头就是最终链表的头
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(ListNode listNode) {
        System.out.print("Linked List: ");
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(7);
        head1.next = new ListNode(9);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(8);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next.next = new ListNode(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);
    }

    public static class ListNode {

        public int val;
        public ListNode next;

        public ListNode(int data) {
            this.val = data;
        }
    }
}
