package lintcode;
//样例
//        样例 1：
//
//        输入：
//
//        列表 = [1]
//        输出：
//
//        [
//        [1]
//        ]
//        样例 2：
//
//        输入：
//
//        列表 = [1,2,3]
//        输出：
//
//        [
//        [1,2,3],
//        [1,3,2],
//        [2,1,3],
//        [2,3,1],
//        [3,1,2],
//        [3,2,1]
//        ]
//        挑战
//        使用递归和非递归分别解决。

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/8/19
 * @since
 */
public class LintCode_0015_Permutations {
    // 递归方法
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> r = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            ans.add(r);
            return ans;
        }
        process(nums, 0, ans);
        return ans;
    }

    // 所有index以后的数字都可以来到index位置进行替换
    private void process(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length - 1) {
            List<Integer> ans = new ArrayList<>();
            for (int num : nums) {
                ans.add(num);
            }
            result.add(ans);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            process(nums, index + 1, result);
            swap(nums, i, index);
        }
    }

    public void swap(int[] n, int i, int j) {
        if (i != j) {
            n[i] = n[i] ^ n[j];
            n[j] = n[i] ^ n[j];
            n[i] = n[i] ^ n[j];
        }
    }

    // TODO 非递归方法
    public List<List<Integer>> permute2(int[] nums) {
        //先判断数组的特殊情况
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }

        //插入第一个数
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        res.add(new ArrayList<>(list));

        //插入剩下的数
        for (int i = 1; i < nums.length; i++) {
            //获取当前res中list个数
            int num_res = res.size();
            //对res中每个list进行数字插入
            for (int j = 0; j < num_res; j++) {
                //获取每个list中当前数字个数，确定可插入方案数
                int num_list = res.get(0).size();
                for (int k = 0; k <= num_list; k++) {
                    //每次取第一个list进行插入，完成后删除掉第一个
                    ArrayList<Integer> temp = new ArrayList<>(res.get(0));
                    temp.add(k, nums[i]);
                    res.add(new ArrayList<>(temp));
                }
                res.remove(0);
            }
        }
        return res;
    }
}
