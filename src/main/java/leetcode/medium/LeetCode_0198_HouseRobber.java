package leetcode.medium;


//如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
//		给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
//		来源：力扣（LeetCode）
//		链接：https://leetcode-cn.com/problems/house-robber
//		著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 本质：返回一个数组中，选择的数字不能相邻的情况下，最大子序列累加和
public class LeetCode_0198_HouseRobber {

    public static int rob(int[] arr) {
        if (arr.length == 1) {
            return Math.max(arr[0], 0);
        }
        int prePre = arr[0];
        int pre = Math.max(arr[0], arr[1]);
        int cur = Math.max(pre, prePre);
        for (int i = 2; i < arr.length; i++) {
            cur = Math.max(Math.max(arr[i], pre), prePre + arr[i]);
            prePre = pre;
            pre = cur;
        }
        return cur;
    }

}
