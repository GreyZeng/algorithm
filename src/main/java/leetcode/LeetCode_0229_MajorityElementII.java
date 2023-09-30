package leetcode;

import java.util.ArrayList;
import java.util.List;

// n = 2 https://leetcode-cn.com/problems/majority-element
// n = 3 https://leetcode-cn.com/problems/majority-element-ii/
// n = k https://www.nowcoder.com/questionTerminal/4d448650c0324df08c40c614226026ad
// @see NowCoder_MajorK
// 笔记： https://www.cnblogs.com/greyzeng/p/14410280.html
public class LeetCode_0229_MajorityElementII {

  public static List<Integer> majorityElement(int[] arr) {
    List<Integer> ans = new ArrayList<>();
    // 处理边界
    if (arr == null || arr.length == 0) {
      return ans;
    }
    // 处理边界
    if (arr.length == 1) {
      ans.add(arr[0]);
      return ans;
    }
    // 处理边界
    if (arr.length == 2) {
      ans.add(arr[0]);
      if (arr[0] != arr[1]) {
        ans.add(arr[1]);
      }
      return ans;
    }
    int n = arr.length;
    int hp1 = 0;
    int hp2 = 0;
    int v1 = arr[0];
    int v2 = arr[0];
    for (int e : arr) {
      if (e == v1) {
        hp1++;
      } else if (e == v2) {
        hp2++;
      } else if (hp1 == 0) {
        v1 = e;
        hp1 = 1;
      } else if (hp2 == 0) {
        v2 = e;
        hp2 = 1;
      } else {
        hp1--;
        hp2--;
      }
    }
    // 最后验证一下
    int n1 = 0;
    int n2 = 0;
    for (int e : arr) {
      if (e == v1) {
        n1++;
      } else if (e == v2) {
        n2++;
      }
    }
    if (n1 > n / 3) {
      ans.add(v1);
    }
    if (n2 > n / 3) {
      ans.add(v2);
    }
    return ans;
  }
}
