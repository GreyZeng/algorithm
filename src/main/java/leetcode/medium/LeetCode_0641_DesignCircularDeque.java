package leetcode.medium;

// TODO
// 用双链表实现双端队列
// https://leetcode-cn.com/problems/design-circular-deque/
public class LeetCode_0641_DesignCircularDeque {

    class MyCircularDeque {

        public MyCircularDeque(int k) {

        }


        public boolean insertFront(int value) {
            return false;
        }


        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            return false;
        }


        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            return false;
        }


        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            return false;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return -1;
        }


        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return -1;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isFull() {
            return false;
        }
    }
}
