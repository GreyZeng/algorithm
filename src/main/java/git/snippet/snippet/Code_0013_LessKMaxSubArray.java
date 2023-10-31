package git.snippet.snippet;

// https://www.nowcoder.com/questionTerminal/3473e545d6924077a4f7cbc850408ade
// minSum 必须以i开头的子数组获得的最小累加和是多少
// minSumEnd 必须以i开头的子数组获得最小累加和的时候，扩到的右边界是多少。
public class Code_0013_LessKMaxSubArray {
  public static int maxSubArray(int[] arr, int k) {
    int n = arr.length;
    int[] minSum = new int[n];
    int[] minSumEnd = new int[n];
    minSum[n - 1] = arr[n - 1];
    minSumEnd[n - 1] = n - 1;
    for (int i = n - 2; i >= 0; i--) {
      minSum[i] = arr[i];
      minSumEnd[i] = i;
      if (minSum[i + 1] <= 0) {
        // 有利可图
        minSum[i] += minSum[i + 1];
        minSumEnd[i] = minSumEnd[i + 1];
      }
    }
    int ans = 0;
    int end = 0;
    int sum = 0;
    for (int i = 0; i < n; i++) {
      while (end < n && sum + minSum[end] <= k) {
        sum += minSum[end];
        end = minSumEnd[end] + 1;
      }
      // 到这里，说明end位置是不满足条件的第一个位置
      ans = Math.max(ans, end - i);
      if (end > i) {
        // 模拟出窗口
        sum -= arr[i];
      } else {
        end = i + 1;
      }
    }

    return ans;
  }

  public static int maxLength(int[] arr, int k) {
    int[] h = new int[arr.length + 1];
    int sum = 0;
    h[0] = sum;
    for (int i = 0; i != arr.length; i++) {
      sum += arr[i];
      h[i + 1] = Math.max(sum, h[i]);
    }
    sum = 0;
    int res = 0;
    int pre;
    int len;
    for (int i = 0; i != arr.length; i++) {
      sum += arr[i];
      pre = getLessIndex(h, sum - k);
      len = pre == -1 ? 0 : i - pre + 1;
      res = Math.max(res, len);
    }
    return res;
  }

  public static int getLessIndex(int[] arr, int num) {
    int low = 0;
    int high = arr.length - 1;
    int mid;
    int res = -1;
    while (low <= high) {
      mid = (low + high) / 2;
      if (arr[mid] >= num) {
        res = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return res;
  }

  // for test
  public static int[] generateRandomArray(int len, int maxValue) {
    int[] res = new int[len];
    for (int i = 0; i != res.length; i++) {
      res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println("test begin");
    for (int i = 0; i < 10000000; i++) {
      int[] arr = generateRandomArray(10, 20);
      int k = (int) (Math.random() * 20) - 5;
      int ans1 = maxSubArray(arr, k);
      int ans2 = maxLength(arr, k);
      if (ans1 != ans2) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish");
  }
}
