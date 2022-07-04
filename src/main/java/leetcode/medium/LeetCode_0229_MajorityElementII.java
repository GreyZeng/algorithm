package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// n = 2 https://leetcode-cn.com/problems/majority-element
// n = 3 https://leetcode-cn.com/problems/majority-element-ii/
// n = k https://www.nowcoder.com/questionTerminal/4d448650c0324df08c40c614226026ad
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

    // 推广到k
    // https://www.nowcoder.com/questionTerminal/4d448650c0324df08c40c614226026ad
    public static List<Integer> major(int[] arr, int K) {
        List<Integer> ans = new ArrayList<>();
        if (K < 2) {
            return new ArrayList<>();
        }
        // 攒候选，cands，候选表，最多K-1条记录！ > N / K次的数字，最多有K-1个
        HashMap<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i != arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else { // arr[i] 不是候选
                if (cands.size() == K - 1) { // 当前数肯定不要！，每一个候选付出1点血量，血量变成0的候选，要删掉！
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        // 所有可能的候选，都在cands表中！遍历一遍arr，每个候选收集真实次数
        HashMap<Integer, Integer> reals = getReals(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : cands.entrySet()) {
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / K) {
                hasPrint = true;
                ans.add(key);
            }
        }
        return hasPrint ? ans : new ArrayList<>();
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }
        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }
}
