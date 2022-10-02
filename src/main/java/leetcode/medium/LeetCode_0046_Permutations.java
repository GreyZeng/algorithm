package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 打印数组的全部排列(无重复)
 * <a href="https://leetcode-cn.com/problems/permutations/">打印数组的全部排列(无重复）</a>
 * 笔记见：https://www.cnblogs.com/greyzeng/p/16749313.html
 * LintCode要求使用递归和非递归两种方式实现
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since 1.8
 */
public class LeetCode_0046_Permutations {
    public static List<List<Integer>> permute(int[] arr) {
        if (arr == null) {
            return Collections.emptyList();
        }
        if (arr.length == 0) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }
        List<List<Integer>> result = new LinkedList<>();
        p(arr, 0, result);
        return result;
    }

    private static void p(int[] arr, int i, List<List<Integer>> result) {
        if (i == arr.length - 1) {
            // 来到最后一个位置，收集答案
            result.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
            return;
        }
        for (int j = i; j < arr.length; j++) {
            swap(arr, i, j);
            p(arr, i + 1, result);
            swap(arr, i, j);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
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
