package leetcode.hard;

import java.util.HashMap;

// TODO
//你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。
//
//        你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6的支架。
//
//        返回 广告牌的最大可能安装高度 。如果没法安装广告牌，请返回 0。
//
//        示例 1：
//
//        输入：[1,2,3,6]
//        输出：6
//        解释：我们有两个不相交的子集 {1,2,3} 和 {6}，它们具有相同的和 sum = 6。
//        示例 2：
//
//        输入：[1,2,3,4,5,6]
//        输出：10
//        解释：我们有两个不相交的子集 {2,3,5} 和 {4,6}，它们具有相同的和 sum = 10。
//        示例 3：
//
//        输入：[1,2]
//        输出：0
//        解释：没法安装广告牌，所以返回 0。
//        
//
//        提示：
//
//        0 <= rods.length <= 20
//        1 <= rods[i] <= 1000
//        sum(rods[i]) <= 5000
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode.cn/problems/tallest-billboard
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// tips:map记录差值
public class LeetCode_0956_TallestBillboard {
    public int tallestBillboard(int[] rods) {
        // key 集合对的某个差
        // value 满足差值为key的集合对中，最好的一对，较小集合的累加和
        // 较大 -> value + key
        HashMap<Integer, Integer> dp = new HashMap<>(), cur;
        dp.put(0, 0);// 空集 和 空集
        for (int num : rods) {
            if (num != 0) {
                // cur 内部数据完全和dp一样
                cur = new HashMap<>(dp); // 考虑x之前的集合差值状况拷贝下来
                for (int d : cur.keySet()) {
                    int diffMore = cur.get(d); // 最好的一对，较小集合的累加和
                    // x决定放入，比较大的那个
                    dp.put(d + num, Math.max(diffMore, dp.getOrDefault(num + d, 0)));
                    // x决定放入，比较小的那个
                    // 新的差值 Math.abs(x - d)
                    // 之前差值为Math.abs(x - d)，的那一对，就要和这一对，决策一下
                    // 之前那一对，较小集合的累加和diffXD
                    int diffXD = dp.getOrDefault(Math.abs(num - d), 0);
                    if (d >= num) { // x决定放入比较小的那个, 但是放入之后，没有超过这一对较大的那个
                        dp.put(d - num, Math.max(diffMore + num, diffXD));
                    } else { // x决定放入比较小的那个, 但是放入之后，没有超过这一对较大的那个
                        dp.put(num - d, Math.max(diffMore + d, diffXD));
                    }
                }
            }
        }
        return dp.get(0);
    }

}
