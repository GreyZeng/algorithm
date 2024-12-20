package grey.algorithm;

import java.util.Objects;
import java.util.PriorityQueue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 什么是完全二叉树 如果一个树是满的，它是完全二叉树，即便不是满的，也是从左到右依次变满的
// 堆结构
// 1）堆结构就是用数组实现的完全二叉树结构
// 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
// 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
// 4）堆结构的heapInsert与heapify操作
// 5）堆结构的增大和减少
// 6）Java中的PriorityQueue，就是堆结构
// 用数组表示堆的两种情况
// 第一种情况：如果使用数组0位置，对于i位置来说，它的：
// 左孩子 2 * i + 1
// 右孩子 2 * i + 2
// 父节点 （i - 1）/ 2
// 第二种情况：如果不用0位置，对于i位置来说，它的：
// 左孩子 2 * i 即：i << 1
// 右孩子 2 * i + 1 即：i << 1 | 1
// 父节点 i / 2 即：i >> 1
// 大根堆：完全二叉树中，每棵树的最大值都是头节点的值
// heapify和heapInsert都是logN级别的复杂度，因为N个节点的二叉树高度是logN
public class Code_0029_MaxHeap {
    private final int[] heap;
    private int heapSize;

    public Code_0029_MaxHeap(int limit) {
        heap = new int[limit];
        heapSize = 0;
    }

    public void push(int value) {
        if (!isFull()) {
            heap[heapSize] = value;
            heapInsert();
            heapSize++;
        }
    }

    public int pop() {
        // heap[0] 就是堆顶
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapify();
        return ans;
    }

    private void heapInsert() {
        int i = heapSize;
        while (heap[i] > heap[(i - 1) / 2]) {
            swap(heap, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void heapify() {
        int index = 0;
        int left = 1; // index * 2 + 1;
        while (left < heapSize) {
            int largest // bigger index
                    = left + 1 < heapSize // right child exist
                    && heap[left + 1] > heap[left] // compare left child and right child
                    ? left + 1 : left;
            largest = heap[largest] > heap[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(heap, largest, index);
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
        if (i != j && arr != null && arr.length > 1) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    // for test
    public static void main(String[] args) {
        testHeap();
    }

    public static void testHeap() {
        int value = 10000;
        int limit = 100;
        int testTimes = 2000000;
        System.out.println("test start");
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            Code_0029_MaxHeap my = new Code_0029_MaxHeap(curLimit);
            PriorityQueue<Integer> test = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("出错了");
                    break;
                }
                if (my.isFull() != (test.size() == curLimit)) {
                    System.out.println("出错了");
                    break;
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.add(curValue);
                } else if (my.isFull()) {
                    if (!Objects.equals(my.pop(), test.poll())) {
                        System.out.println("出错了");
                        break;
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.add(curValue);
                    } else {
                        if (!Objects.equals(my.pop(), test.poll())) {
                            System.out.println("出错了");
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("test end");
    }
}
