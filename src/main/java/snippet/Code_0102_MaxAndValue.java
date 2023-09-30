package snippet;

// 给定一个非负数组成的数组，长度一定大于1
// 想知道数组中哪两个数&的结果最大
// 返回这个最大结果
// 时间复杂度O(N)，额外空间复杂度O(1)
// 30位上的的1有几个
// 1. <2个 放弃30位上是1的
// 2. ==2个 ，就是这两个==2的数&完的结果
// 3. >2个 假设有a个，就只考虑这a个数，不满足条件的就发货到垃圾区
// 给定一个正数组成的数组，长度一定大于1，求数组中哪两个数与的结果最大
public class Code_0102_MaxAndValue {

  // O(N^2)的暴力解
  public static int maxAndValue1(int[] arr) {
    int N = arr.length;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        max = Math.max(max, arr[i] & arr[j]);
      }
    }
    return max;
  }

  // O(N)的解
  // 判断30位上的的1有几个
  // 1. < 2个 放弃30位上是1的，考虑29位是1的...
  // 2. ==2个 ，就是这两个等于2的数与完的结果
  // 3. > 2个 假设有a个，就只考虑这a个数，不满足条件的就发货到垃圾区
  public static int maxAndValue2(int[] arr) {
    int ans = 0;
    int limit = arr.length;
    for (int bit = 30; bit >= 0; bit--) {
      int i = 0;
      int tmp = limit;
      while (i < limit) {
        if ((arr[i] & (1 << bit)) == 0) {
          swap(arr, i, --limit);
        } else {
          i++;
        }
      }
      if (limit == 2) {
        return arr[0] & arr[1];
      }
      if (limit < 2) {
        limit = tmp;
      } else {
        ans |= (1 << bit);
      }
    }
    return ans;
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static int[] randomArray(int size, int range) {
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = (int) (Math.random() * range) + 1;
    }
    return arr;
  }

  public static void main(String[] args) {
    int maxSize = 50;
    int range = 30;
    int testTime = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int size = (int) (Math.random() * maxSize) + 2;
      int[] arr = randomArray(size, range);
      int ans1 = maxAndValue1(arr);
      int ans2 = maxAndValue2(arr);
      if (ans1 != ans2) {
        System.out.println("Oops!");
      }
    }
    System.out.println("测试结束");
  }
}
