package code;

// 双向链表反转
// TODO 补对数器
public class Code_0009_ReverseDoubleList {
    public static class DoubleNode {
        public DoubleNode next;
        public DoubleNode last;
        public int value; 
        public DoubleNode(int v ) {
            value = v;   
        } 
    }
    
    /**
     * 双向链表反转
     * @param head 原链表的头节点
     * @return 新的头部节点
     */
    public static DoubleNode reverse(DoubleNode head) {
        DoubleNode next = null;
        DoubleNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    } 
}
