package 练习题.leetcode.easy;

// 水王数问题
// 1. 找到数组中，严格大于一半的数
// 扩展
// 2. 找到数组中，严格大于1/3的数
// 3. 找到数组中，严格大于1/K的数
// 笔记： https://www.cnblogs.com/greyzeng/p/14410280.html
public class LeetCode_0169_MajorityElement {
    // tips:每次删除两个不同的数，这个数肯定会剩下来
    // 但是最后剩下来的数，不一定是水王数，比如:[1,2,3,4,5]
    // 所以还需要重新验证一下
    public static int majorityElement(int[] arr) {
        int n = arr.length;
        int ans = arr[0];
        int hp = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] == ans) {
                hp++;
                if (hp > n / 2) {
                    return arr[i];
                }
            } else {
                hp--;
                if (hp == 0) {
                    hp = 1;
                    ans = arr[i];
                }
            }
        }
        int count = 0;
        for (int e : arr) {
            if (e == ans) {
                count++;
            }
        }
        if (count > n / 2) {
            return ans;
        }
        // 根据题目设定来配置，LeetCode是不会走到这步
        return -1;
    }

}
