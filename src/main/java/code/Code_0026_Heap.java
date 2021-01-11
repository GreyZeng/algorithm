package code;

/**
 * 堆结构 1）堆结构就是用数组实现的完全二叉树结构 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆 4）堆结构的heapInsert与heapify操作 5）堆结构的增大和减少
 * 6）优先级队列结构，就是堆结构
 */
public class Code_0026_Heap {
    public static class MyMinHeap {
        public int[] values;
        public int limit;
        public int heapSize;

        public MyMinHeap(int limit) {
            this.limit = limit;
            values = new int[limit];
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int v) {
            values[heapSize] = v;
            heapInsert(heapSize++);
        }

        public int pop() {
            int res = values[0];
            swap(0, --heapSize);
            heapify(0);
            return res;
        }

        private void swap(int i, int j) {
            int t = values[i];
            values[i] = values[j];
            values[j] = t;
        }

        // 自顶向下
        private void heapify(int index) {
            int leftChild = index * 2 + 1;
            while (leftChild < heapSize) {
                int smallest = leftChild + 1 < heapSize ? Math.min(values[leftChild], values[leftChild + 1]) : values[leftChild];
                if (smallest >= values[index]) {
                    break;
                }
                smallest = smallest == values[leftChild] ? leftChild : leftChild + 1;
                swap(smallest, index);
                index = smallest;
                leftChild = index * 2 + 1;
            }
        }

        // 自底向上
        private void heapInsert(int index) {
            while (values[(index - 1) / 2] > values[index]) {
                swap((index - 1) / 2, index);
                index = (index - 1) / 2;
            }
        }
    }


    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            int max = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return max;
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[(index - 1) / 2] < arr[index]) {
                swap(arr, (index - 1) / 2, index);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = 2 * index + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize ? Math.max(arr[left], arr[left + 1]) : arr[left];
                if (arr[index] >= largest) {
                    break;
                }
                largest = (largest == arr[left]) ? left : left + 1;
                swap(arr, index, largest);
                index = largest;
                left = 2 * index + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        MyMinHeap heap = new MyMinHeap(5);
        heap.push(3);
        heap.push(4);
        heap.push(1);
        heap.push(7);
        heap.push(7);
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
    }


}
