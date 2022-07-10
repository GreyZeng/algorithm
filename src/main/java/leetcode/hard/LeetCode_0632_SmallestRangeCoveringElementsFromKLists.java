package leetcode.hard;

import java.util.*;
// 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

// 我们定义如果 b-a<d-c 或者在 b-a==d-c 时 a<c，则区间[a,b]比[c,d]小。

//  

// 示例 1：

// 输入：nums=[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
// 输出：[20,24]
// 解释：列表 1：[4,10,15,24,26]，24 在区间[20,24]中。列表 2：[0,9,12,20]，20 在区间[20,24]中。列表 3：[5,18,22,30]，22 在区间[20,24]中。

//示例 2：

// 输入：nums=[[1,2,3],[1,2,3],[1,2,3]]  
// 输出：[1,1] 

// 提示：

// nums.length==k 
// 1<=k<=3500 
// 1<=nums[i].length<=50
// -10^5<=nums[i][j]<=10^5 
// nums[i]按非递减顺序排列

// 来源：力扣（LeetCode）链接：https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 笔记：https://www.cnblogs.com/greyzeng/p/16463385.html
public class LeetCode_0632_SmallestRangeCoveringElementsFromKLists {
    public static class Node {
        public int value;// 值是多少
        public int position;// 在链表的哪个位置上
        public int bucket; // 在哪个链表上

        public Node(int v, int p, int b) {
            value = v;
            position = p;
            bucket = b;
        }
    }


    public static int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null) {
            return null;
        }
        if (nums.size() == 1) {
            if (nums.get(0).size() > 0) {
                return new int[]{nums.get(0).get(0), nums.get(0).get(0)};
            } else {
                return null;
            }
        }
        TreeSet<Node> set = new TreeSet<>((o1, o2) -> o1.value != o2.value ? o1.value - o2.value : o1.bucket - o2.bucket);
        int i = 0;
        for (List<Integer> list : nums) {
            set.add(new Node(list.get(0), 0, i));
            i++;
        }
        Node min = set.pollFirst();
        Node max = set.last();
        int[] result = {min.value, max.value};
        while (min.position + 1 < nums.get(min.bucket).size()) {
            set.add(new Node(nums.get(min.bucket).get(min.position + 1), min.position + 1, min.bucket));
            min = set.pollFirst();
            max = set.last();
            result = minRange(result, new int[]{min.value, max.value});
        }
        return result;
    }

    public static int[] minRange(int[] a, int[] b) {
        if (a[1] - a[0] > b[1] - b[0]) {
            return b;
        } else if (a[1] - a[0] < b[1] - b[0]) {
            return a;
        }
        return a[0] > b[0] ? b : a;
    }
}
