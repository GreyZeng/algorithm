package 练习题.leetcode.medium;

import java.util.Arrays;

//森林中有未知数量的兔子。提问其中若干只兔子 "还有多少只兔子与你（指被提问的兔子）颜色相同?" ，将答案收集到一个整数数组 answers 中，其中 answers[i] 是第 i 只兔子的回答。
//
//        给你数组 answers ，返回森林中兔子的最少数量。
//
//        
//
//        示例 1：
//
//        输入：answers = [1,1,2]
//        输出：5
//        解释：
//        两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
//        之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
//        设回答了 "2" 的兔子为蓝色。
//        此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
//        因此森林中兔子的最少数量是 5 只：3 只回答的和 2 只没有回答的。
//        示例 2：
//
//        输入：answers = [10,10,10]
//        输出：11
//        
//
//        提示：
//
//        1 <= answers.length <= 1000
//        0 <= answers[i] < 1000
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode.cn/problems/rabbits-in-forest
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// TODO
// 类似题目
//企鹅厂每年都会发文化衫，文化衫有很多种，厂庆的时候，企鹅们都需要穿文化衫来拍照
//        一次采访中，记者随机遇到的企鹅，企鹅会告诉记者还有多少企鹅跟他穿一种文化衫
//        我们将这些回答放在answers数组里，返回鹅厂中企鹅的最少数量
//        Leetcode题目：https://leetcode.com/problems/rabbits-in-forest/
//        tips：排序，说的一样的人，排在一起，自我消化，这样就一定是对的
//        向上取整
//
//        a/b -> (a + (b - 1)) / b
//
//
//
//        ((C+X)/(X+1))*(X+1)
public class LeetCode_0781_RabbitsInForest {
    public static int numRabbits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int x = arr[0];
        int c = 1;
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (x != arr[i]) {
                ans += ((c + x) / (x + 1)) * (x + 1);
                x = arr[i];
                c = 1;
            } else {
                c++;
            }
        }
        return ans + ((c + x) / (x + 1)) * (x + 1);
    }
}
