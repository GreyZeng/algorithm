package leetcode;

import java.util.ArrayList;
import java.util.List;

// 杨辉三角
public class LeetCode_0118_PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        if (numRows >= 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            ans.add(list);
        }
        int size;
        for (int i = 1; i < numRows; i++) {
            List<Integer> t = ans.get(i - 1);
            size = t.size();
            List<Integer> a = new ArrayList<>();
            a.add(1);
            for (int j = 1; j < size; j++) {
                a.add(t.get(j) + t.get(j - 1));
            }
            a.add(1);
            ans.add(a);
        }
        return ans;
    }

}
