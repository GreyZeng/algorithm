package git.snippet.candy;

// 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分
// 你需要按照以下要求，帮助老师给这些孩子分发糖果：
// 每个孩子至少分配到 1 个糖果。
// 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
// 那么这样下来，返回老师至少需要准备多少颗糖果
// 进阶：在原来要求的基础上，增加一个要求，相邻的孩子间如果分数一样，分的糖果数必须一样，返回至少需要准备多少颗糖果
// 分糖果问题
// https://leetcode-cn.com/problems/candy/
// 进阶问题：@see NowCoder_CandyII
// https://www.nowcoder.com/questionTerminal/de342201576e44548685b6c10734716e
// 环形分糖果 @see Code_0112_CircleCandy
public class LeetCode_0135_Candy {

    public static int candy(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
        }
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = arr[i] > arr[i + 1] ? right[i + 1] + 1 : 1;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
}
