package heap;

import java.util.PriorityQueue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 什么是完全二叉树 如果一个树是满的，它是完全二叉树，即便不是满的，也是从左到右依次变满的
// 堆结构
//1）堆结构就是用数组实现的完全二叉树结构
//2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
//3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
//4）堆结构的heapInsert与heapify操作
//5）堆结构的增大和减少
//6）Java中的PriorityQueue，就是堆结构
// 用数组表示堆的两种情况
// 第一种情况：如果使用数组0位置，对于i位置来说，它的：
// 左孩子 2 * i + 1
//右孩子 2 * i + 2
//父节点 （i - 1）/ 2
// 第二种情况：如果不用0位置，对于i位置来说，它的：
//左孩子 2 * i 即：i << 1
//右孩子 2 * i + 1 即：i << 1 | 1
//父节点 i / 2 即：i >> 1
// 大根堆：完全二叉树中，每棵树的最大值都是头节点的值
// heapify和heapInsert都是logN级别的复杂度，因为N个节点的二叉树高度是logN
public class Code_Heap {

    public static class MyMaxHeap {
        private final int[] heap;
        // private final int limit; limit == heap.length
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            //  this.limit = limit;
            heapSize = 0;
        }


        public void push(int value) {
            if (!isFull()) {
                heap[heapSize] = value;
                // value heapSize
                heapInsert(heap, heapSize++);
            }
        }

        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;

        }

        private void heapInsert(int[] arr, int i) {
            while (arr[i] > arr[(i - 1) / 2]) {
                swap(arr, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == heap.length;
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }
    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        // 5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
