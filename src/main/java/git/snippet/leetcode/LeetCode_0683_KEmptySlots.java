package git.snippet.leetcode;

// TODO
// 给定一个数组bulbs，bulds[i] = j，表示第i天亮起的灯是j。任何一天只会亮一盏灯，如果天数有1~i天，那么灯也一定只有1~i号。
// 给定一个正数k，如果两盏亮起的灯之间有正好k个不亮的灯，那么说这个是达标情况。
// 返回最早在哪一天，会出现达标情况，如果不存在达标情况，返回-1
// leetcode题目：https://leetcode.cn/problems/k-empty-slots/
// tips:
// 0位置代表1号灯泡什么时候亮
// 1位置代表2号灯泡什么时候亮
// ...
public class LeetCode_0683_KEmptySlots {

    public static int kEmptySlots1(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = i + 1;
        }
        int ans = Integer.MAX_VALUE;
        if (k == 0) {
            for (int i = 1; i < n; i++) {
                ans = Math.min(ans, Math.max(days[i - 1], days[i]));
            }
        } else {
            int[] minq = new int[n];
            int l = 0;
            int r = -1;
            for (int i = 1; i < n && i < k; i++) {
                while (l <= r && days[minq[r]] >= days[i]) {
                    r--;
                }
                minq[++r] = i;
            }
            for (int i = 1, j = k; j < n - 1; i++, j++) {
                while (l <= r && days[minq[r]] >= days[j]) {
                    r--;
                }
                minq[++r] = j;
                int cur = Math.max(days[i - 1], days[j + 1]);
                if (days[minq[l]] > cur) {
                    ans = Math.min(ans, cur);
                }
                if (i == minq[l]) {
                    l++;
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    public static int kEmptySlots2(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = i + 1;
        }
        int ans = Integer.MAX_VALUE;
        for (int left = 0, mid = 1, right = k + 1; right < n; mid++) {
            // 验证指针mid
            // mid 永远不和left撞上的！
            // 1) mid在left和right中间验证的时候，没通过！
            // 2) mid是撞上right的时候
            if (days[mid] <= Math.max(days[left], days[right])) {
                // if(mid == right) { // left...right 达标的！
                // int cur = Math.max(days[left], days[right]);
                // ans = Math.min(ans, cur);
                // left = mid;
                // right = mid + k + 1;
                // } else { // 验证不通过！
                // left = mid;
                // right = mid + k + 1;
                // }
                if (mid == right) { // 收答案！
                    ans = Math.min(ans, Math.max(days[left], days[right]));
                }
                left = mid;
                right = mid + k + 1;
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}
