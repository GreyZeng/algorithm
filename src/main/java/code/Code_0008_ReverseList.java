package code;

 

// 单链表反转
// 扩展见：https://zhuanlan.zhihu.com/p/86745433?utm_source=ZHShareTargetIDMore
public class Code_0008_ReverseList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 非递归版本
    public ListNode reverseList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
   
        ListNode pre = null;
        while (head != null) {
            ListNode t = head.next;
            head.next = pre;
            pre = head;
            head = t;
        }
        return pre;
    }

    // 递归版本
    public ListNode reverseListRecursive(ListNode head) {
        return reverse(head);
    }
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode t = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return t;
    }
    
    // 反转链表的一部分
    public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) {
			return head;
		}

		ListNode cur = head;
		ListNode pre = null;
		while (m != 1) {
			m--;
			n--;
			cur = cur.next;
			pre = pre == null ? head : pre.next;
		}
		ListNode tCur = cur;
		ListNode tF = cur.next;
		ListNode tPre = pre;
		while (n != 1) {
			n--;
			tF = tF.next;
			ListNode t = tCur.next;
			tCur.next = tPre;
			tPre = tCur;
			tCur = t;
		} 
		
		tCur.next = tPre;
		if (pre != null) {
			pre.next = tCur;
		} else {
			head = tCur;
		}
		cur.next = tF; 
		return head; 
	}
    
    // TODO 递归方式 反转链表的一部分
    public static ListNode reverseBetweenRecursive(ListNode head, int m, int n) {
    	return null;
    }

	
    
}
