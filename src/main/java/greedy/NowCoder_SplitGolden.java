package greedy;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。 比如长度为20的金条， 不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。 如果先把长度60的金条分成10和50，花费60;
 * 再把长度50的金条分成20和30，花费50; 一共花费110铜板。 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30; 一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 *
 * <p>
 *
 * <p>注：堆和排序是解决贪心问题的最常用的两种方案
 */
// https://www.nowcoder.com/questionTerminal/418d2fcdf7f24d6f8f4202e23951c0da
// https://www.lintcode.com/problem/minimum-cost-to-connect-sticks/description
// 笔记：https://www.cnblogs.com/greyzeng/p/16704842.html
public class NowCoder_SplitGolden {
  public static long lessMoney(long[] arr) {
    if (arr == null || arr.length <= 1) {
      return 0;
    }
    if (arr.length == 2) {
      return arr[0] + arr[1];
    }

    PriorityQueue<Long> queue = new PriorityQueue<>();
    for (long i : arr) {
      queue.add(i);
    }
    long cost = 0;
    while (queue.size() > 1) {
      long i = queue.poll();
      long j = queue.poll();
      cost += (i + j);
      queue.offer(i + j);
    }
    return cost;
  }

  // 暴力递归版本
  public static long lessMoney0(long[] arr) {
    if (arr == null || arr.length <= 1) {
      return 0;
    }
    return process0(arr, 0);
  }

  private static long process0(long[] arr, long s) {
    if (arr.length == 1) {
      return s;
    } else {
      long min = Long.MAX_VALUE;
      for (int i = 0; i < arr.length; i++) {
        for (int j = i + 1; j < arr.length; j++) {
          min = Math.min(process0(copyExcept(arr, i, j), s + (arr[i] + arr[j])), min);
        }
      }
      return min;
    }
  }

  private static long[] copyExcept(long[] arr, int i1, int i2) {
    int m = 0;
    long[] nArr = new long[arr.length - 1];
    long t = 0;
    for (int j = 0; j < arr.length; j++) {
      if (j != i1 && j != i2) {
        nArr[m++] = arr[j];
      } else {
        t += arr[j];
      }
    }
    nArr[arr.length - 2] = t;
    return nArr;
  }

  public static long[] generateRandomArr(int maxSize, long maxValue) {
    int size = (int) (Math.random() * maxSize) + 1;
    long[] arr = new long[size];
    for (int i = 0; i < size; i++) {
      arr[i] = (long) (Math.random() * (maxValue + 1)) - (long) (Math.random() * (maxValue + 1));
    }
    return arr;
  }

  public static void main(String[] args) {
    int times = 50000;
    long maxValue = 9;
    int maxSize = 7;
    for (int i = 0; i < times; i++) {
      long[] arr = generateRandomArr(maxSize, maxValue);
      if (lessMoney(arr) != lessMoney0(arr)) {
        System.out.println("Ops!");
      }
    }
    System.out.println("Nice!");

    // 以下为牛客输入
    /*
     * Scanner in = new Scanner(System.in); int count = in.nextInt(); long[] arr = new long[count];
     * for (int i = 0; i < count;i++) { arr[i] = in.nextLong(); }
     * System.out.println(lessMoney(arr));
     */
  }
}
