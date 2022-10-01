package leetcode.medium;


//Given the head of a linked list, return the list after sorting it in ascending order.
//
//        Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
// 方法1： 改归并排序 时间 O(nlogn) 空间 O(1)
// 方法2： 转成数组  利用快排 然后再转成链表  时间 O(nlogn) 空间 O(N)
public class LeetCode_0148_SortList {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int v) {
            val = v;
        }
    }


    // 利用归并排序
    public static ListNode sortList(ListNode head) {
        int N = 0;
        ListNode cur = head;
        while (cur != null) {
            N++;
            cur = cur.next;
        }
        ListNode h = head;
        ListNode teamFirst = head;
        ListNode pre = null;
        int L = 1;
        while (L < N) {
            while (teamFirst != null) {
                ListNode[] hthtn = hthtn(teamFirst, L);
                ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
                if (h == teamFirst) {
                    h = mhmt[0];
                    pre = mhmt[1];
                } else {
                    pre.next = mhmt[0];
                    pre = mhmt[1];
                }
                teamFirst = hthtn[4];
            }
            teamFirst = h;
            pre = null;
            L <<= 1;
        }
        return h;
    }

    public static ListNode[] hthtn(ListNode teamFirst, int len) {
        ListNode ls = teamFirst;
        ListNode le = teamFirst;
        ListNode rs = null;
        ListNode re = null;
        ListNode next = null;
        int p = 0;
        while (teamFirst != null) {
            // 之所以这里是小于等于，是因为这里可能不满足分组的个数（不足个数）
            if (p <= len - 1) {
                le = teamFirst;
            }
            if (p == len) {
                rs = teamFirst;
            }
            if (p > len - 1) {
                re = teamFirst;
            }
            if (p == (len << 1) - 1) {
                break;
            }
            p++;
            teamFirst = teamFirst.next;
        }
        if (le != null) {
            le.next = null;
        }
        if (re != null) {
            next = re.next;
            re.next = null;
        }
        return new ListNode[]{ls, le, rs, re, next};
    }

    // 返回merge后的头和尾
    // 注意边界考虑
    public static ListNode[] merge(ListNode h1, ListNode t1, ListNode h2, ListNode t2) {
        if (h2 == null) {
            return new ListNode[]{h1, t1};
        }
        ListNode head = h1;
        ListNode tail = h1;
        ListNode c = null;
        ListNode pre = null;
        while (h1 != t1.next && h2 != t2.next) {
            if (h1.val > h2.val) {
                c = h2;
                h2 = h2.next;
            } else {
                c = h1;
                h1 = h1.next;
            }
            if (pre == null) {
                // 设置merge后的头节点,赋值给head
                // 后续就由pre去往下插入节点
                pre = c;

                head = c;
            } else {
                pre.next = c;
                pre = c;
            }
        }
        // h1节点没越界
        if (h1 != t1.next) {
            while (h1 != t1.next) {
                pre.next = h1;
                pre = pre.next;
                tail = h1;
                h1 = h1.next;
            }
        } else {
            while (h2 != t2.next) {
                pre.next = h2;
                pre = pre.next;
                tail = h2;
                h2 = h2.next;
            }
        }
        return new ListNode[]{head, tail};
    }


    // 转换成数组的做法
    public static ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        ListNode[] nodes = new ListNode[size];
        cur = head;
        int i = 0;
        while (cur != null) {
            nodes[i++] = cur;
            cur = cur.next;
        }
        sortArr(nodes);
        return arrToList(nodes);
    }

    private static void sortArr(ListNode[] nodes) {
        p(nodes, 0, nodes.length - 1);
    }

    private static void p(ListNode[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        p(arr, L, equalArea[0] - 1);
        p(arr, equalArea[1] + 1, R);
    }

    private static int[] netherlandsFlag(ListNode[] nodes, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        ListNode num = nodes[R];
        for (int i = L; i < more; i++) {
            if (nodes[i].val < num.val) {
                swap(nodes, ++less, i);
            } else if (nodes[i].val > num.val) {
                swap(nodes, i--, --more);
            }
        }
        swap(nodes, R, more);
        return new int[]{less + 1, more};
    }

    public static void swap(ListNode[] nodes, int i, int j) {
        if (i != j) {
            ListNode t = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = t;
        }
    }

    public static ListNode arrToList(ListNode[] nodes) {
        ListNode head = nodes[0];
        ListNode cur = head;
        for (int i = 1; i < nodes.length; i++) {
            cur.next = nodes[i];
            cur = nodes[i];
        }
        cur.next = null;
        return head;
    }
}
