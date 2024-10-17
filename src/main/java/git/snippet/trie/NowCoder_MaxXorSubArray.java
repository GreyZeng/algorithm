package git.snippet.trie;

// 数组中所有数都异或起来的结果，叫做异或和。给定一个数组arr，返回arr的最大子数组异或和
// 给定一个整型数组arr，其中可能有正、有负，有零，求其中子数组的最大异或和。
// 备注:
// 时间复杂度O(nlog2n)，额外空间复杂度O(nlog2n)。
// https://www.nowcoder.com/questionTerminal/43f62c52fbac47feaeabe40ac1ab9091
// 方法1：暴力解O(N^3)
// 方法2：O(N^2) 前缀异或和 辅助数组
// 方法3：前缀树
// [11,1,15,10,13,4]
// e[-1] = 0000
// e[0..0] = 11 = 1011
// e[0..1] = 11^1 = 1010
// e[0..2] = 0101
// e[0..3] = 1111
// e[0..4] = 0010
// e[0..5] = 0110
// 这些数构造成前缀树
// 最高位（符合位）期待一样，紧着高位要期待不一样的
// 笔记：https://www.cnblogs.com/greyzeng/p/17007011.html
public class NowCoder_MaxXorSubArray {

    // O(N)
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = arr[0];
        // 0~i整体异或和
        int xor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; // 0 ~ i
            max = Math.max(max, numTrie.maxXor(xor));
            numTrie.add(xor);
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxEor1(arr, arr.length);
            int res = maxXorSubarray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    // 暴力解法，超时，利用前缀异或和数组
    public static int maxEor1(int[] arr, int n) {
        if (n == 0) {
            return 0;
        }
        int[] eor = new int[arr.length];
        int max = arr[0];
        eor[0] = arr[0];
        for (int i = 1; i < n; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        for (int i = 1; i < n; i++) {
            max = Math.max(max, eor[i]);
            for (int j = i; j < n; j++) {
                max = Math.max(max, eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    public static class Node {
        // 前缀树的Node结构
        // next[0] -> 0方向的路
        // next[1] -> 1方向的路
        // next[x] == null 表示 x 方向没有路
        public Node[] next = new Node[2];
    }

    // 基于本题，定制前缀树的实现
    public static class NumTrie {
        // 头节点
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = ((newNum >> move) & 1);
                cur.next[path] = cur.next[path] == null ? new Node() : cur.next[path];
                cur = cur.next[path];
            }
        }

        // num和 谁 ^ 最大的结果（把结果返回）
        public int maxXor(int num) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 取出num中第move位的状态，path只有两种值0就1，整数
                int path = (num >> move) & 1;
                // 期待遇到的东西
                int best = move == 31 ? path : (path ^ 1);
                // 实际遇到的东西
                best = cur.next[best] != null ? best : (best ^ 1);
                // (path ^ best) 当前位位异或完的结果
                ans |= (path ^ best) << move;
                cur = cur.next[best];
            }
            return ans;
        }
    }
}
