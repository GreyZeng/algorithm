package git.snippet.heap;

public class RightMaxHeap {
    private final int limit;
    private int[] arr;
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
