package grey.algorithm.code11_heap;

import java.io.*;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 1. 先让整个数组都变成大根堆结构，建立堆的过程:
// a. 从上到下的方法，时间复杂度为O(N*logN)
// b. 从下到上的方法，时间复杂度为O(N)
// 2. 把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 【扩两倍估算复杂度法】
// 3. 把堆的大小减小成0之后，排序完成
// 堆排序额外空间复杂度O(1)
// 测评：https://www.lintcode.com/problem/464
//测评链接：https://www.luogu.com.cn/problem/P1177
public class Code_0011_Luogu_P1177_HeapSort {
    public static int MAXN = 100001;
    public static int[] arr = new int[MAXN];
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 0; i < n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        // 用 heapInsert初始化
        heapSort1();
        // 用 heapify 初始化
        // heapSort2();
        for (int i = 0; i < n - 1; i++) {
            out.print(arr[i] + " ");
        }
        out.println(arr[n - 1]);
        out.flush();
        out.close();
        br.close();
    }

    public static void heapSort1() {
        // O(N*logN)
        for (int i = 0; i < n; i++) {
            heapInsert(i);
        }
        // 注意：这里要保存一个变量，因为n在循环里面会变化
        int size = n;
        while (size > 0) {
            heapify(0, size);
            swap(0, --size);
        }
    }


    public static void heapSort2() {
        // O(N)
        for (int i = n - 1; i >= 0; i--) {
            heapify(i, n);
        }
        // 注意：这里要保存一个变量，因为n在循环里面会变化
        int size = n;
        while (size > 0) {
            heapify(0, size);
            swap(0, --size);
        }
    }

    public static void heapInsert(int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int i, int size) {
        int l = 2 * i + 1;
        while (l < size) {
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            best = arr[i] > arr[best] ? i : best;
            if (best == i) break;
            swap(best, i);
            i = best;
            l = 2 * i + 1;
        }
    }

    public static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
