//当前是cur
//1. cur无左树,cur = cur.right
//2. cur有左树,找到左树最右节点mostRight
//	a. mostRight的右指针指向null, mostRight.right = cur, cur = cur.right
//	b. mostRight的右指针指向当前节点cur，mostRight.right = null, cur = cur.right
// 3. cur = null 停
package snippet;

// morris遍历
public class Code_0047_Morris {

    public static class Node {

        public Node left;
        public Node right;
        public int value;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostRight一定是cur左树上的最右节点
                // mostRight
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right != null -> mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
}
