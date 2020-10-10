package note;

/**
 * 怎么用数组实现不超过固定大小的队列和栈？
 * 队列：环形数组
 */
public class RingToQueue {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10);
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            queue.push(i);
        };
        queue.push(222);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            int val=queue.pop();
            System.out.print(val + " ");
        }
    }

    public static final class MyQueue {
        private final int[] array;
        private int size;
        private final int limit;
        private int pushi;
        private int polli;

        public MyQueue(int limit) {
            this.limit = limit;
            array = new int[limit];
            this.size = 0;
            this.pushi = 0;
            this.polli = 0;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Queue is empty, can not pop");
            }
            size--;
            int data = array[polli];
            polli = nextIndex(polli);
            return data;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("Queue is full, can not push");
            }
            size++;
            array[pushi] = value;
            pushi = nextIndex(pushi);
        }

        private int nextIndex(int index) {
            return (index > limit - 1) ? 0 : index + 1;

        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

}
