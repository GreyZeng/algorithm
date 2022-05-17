package leetcode.hard;

import java.util.*;
// 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

// 我们定义如果 b-a<d-c 或者在 b-a==d-c 时 a<c，则区间[a,b]比[c,d]小。

//  

// 示例 1：

// 输入：nums=[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
// 输出：[20,24]解释：列表 1：[4,10,15,24,26]，24 在区间[20,24]中。列表 2：[0,9,12,20]，20 在区间[20,24]中。列表 3：[5,18,22,30]，22 在区间[20,24]中。示例 2：

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
public class LeetCode_0632_SmallestRangeCoveringElementsFromKLists {
    public static class Node {
        public int value;
        public int position;
        public int bucket;

        public Node(int v, int p, int b) {
            value = v;
            position = p;
            bucket = b;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
      
        // TODO
        return null;
    }

    public int[] minRange(int[] a, int[] b) {
        if (a[1] - a[0] > b[1] - b[0]) {
            return b;
        } else if (a[1] - a[0] < b[1] - b[0]) {
            return a;
        }
        return a[0] > b[0] ? b : a;
    }
}
