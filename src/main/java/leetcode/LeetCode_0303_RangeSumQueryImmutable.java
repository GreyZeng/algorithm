//Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
//        Implement the NumArray class:
//
//        NumArray(int[] nums) Initializes the object with the integer array nums.
//        int sumRange(int i, int j) Return the sum of the elements of the nums array in the range [i, j] inclusive (i.e., sum(nums[i], nums[i + 1], ... , nums[j]))
//
//
//        Example 1:
//
//        Input
//        ["NumArray", "sumRange", "sumRange", "sumRange"]
//        [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//        Output
//        [null, 1, -1, -3]
//
//        Explanation
//        NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//        numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
//        numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
//        numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
//
//
//        Constraints:
//
//        0 <= nums.length <= 10^4
//        -10^5 <= nums[i] <= 10^5
//        0 <= i <= j < nums.length
//        At most 10^4 calls will be made to sumRange.
package leetcode;

// 线段树解法
public class LeetCode_0303_RangeSumQueryImmutable {

    class NumArray {
        int N;
        int[] sum;
        int[] arr;

        public NumArray(int[] nums) {
            N = nums.length + 1;
            arr = new int[N];
            System.arraycopy(nums, 0, arr, 1, N - 1);
            int v = N << 2;
            sum = new int[v];
            build(1, N - 1, 1);
        }

        private void build(int l, int r, int rt) {
            if (l > r) {
                return;
            }
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, (rt << 1) | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
        }

        public int sumRange(int l, int r) {
            return query(l + 1, r + 1, 1, N - 1, 1);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, (rt << 1) | 1);
            }
            return ans;
        }
    }

}
