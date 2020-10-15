/*Given two arrays, write a function to compute their intersection.

        Example 1:

        Input: nums1 = [1,2,2,1], nums2 = [2,2]
        Output: [2,2]
        Example 2:

        Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        Output: [4,9]
        Note:

        Each element in the result should appear as many times as it shows in both arrays.
        The result can be in any order.
        Follow up:

        What if the given array is already sorted? How would you optimize your algorithm?
        What if nums1's size is small compared to nums2's size? Which algorithm is better?
        What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?*/
package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// 词频表
public class LeetCode_0350_IntersectionOfTwoArraysII {

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2) {
            return null;
        }
        if (0 == nums1.length || 0 == nums2.length) {
            return new int[]{};
        }
        HashMap<Integer, Integer> map1 = new HashMap<>();

        for (int j : nums1) {
            if (map1.containsKey(j)) {
                map1.put(j, map1.get(j) + 1);
            } else {
                map1.put(j, 1);
            }
        }
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int j : nums2) {
            if (map1.containsKey(j)) {
                int base = map1.get(j);
                if (map2.containsKey(j)) {
                    map2.put(j, Math.min(map2.get(j) + 1, base));
                } else {
                    map2.put(j, 1);
                }
            }
        }
        Set<Integer> set = map2.keySet();
        ArrayList<Integer> list = new ArrayList<>();
        int t;
        for (int i : set) {
            t = map2.get(i);
            while (t != 0) {
                list.add(i);
                t--;
            }
        }
        int[] res = new int[list.size()];
        for (t = 0; t < res.length; t++) {
            res[t] = list.get(t);
        }
        return res;
    }

}
