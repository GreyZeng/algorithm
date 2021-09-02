/*You are given an array people where people[i] is the weight of the ith person, 
and an infinite number of boats where each boat can carry a maximum weight of limit. 
Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

        Return the minimum number of boats to carry every given person.



        Example 1:

        Input: people = [1,2], limit = 3
        Output: 1
        Explanation: 1 boat (1, 2)
        Example 2:

        Input: people = [3,2,2,1], limit = 3
        Output: 3
        Explanation: 3 boats (1, 2), (2) and (3)
        Example 3:

        Input: people = [3,5,3,4], limit = 5
        Output: 4
        Explanation: 4 boats (3), (3), (4), (5)


        Constraints:

        1 <= people.length <= 5 * 10^4
        1 <= people[i] <= limit <= 3 * 10^4*/
package leetcode;

import java.util.Arrays;

/**
 * @author Young
 * @version 1.0
 * @date 2021/2/9 13:30
*/
// tips:
// 排序双指针
// 找到小于等于limit/2 的最右边的位置
// 双指针
// [1,3,3,3,4,5,5,5, | 6,6,6,7,7,7,8,9,9]
//                L    R
// L往左 搭配 R 往右 凑一艘船
// 考虑 L + R 和 limit的关系，左右先耗尽的情况具体分析
public class LeetCode_0881_BoatsToSavePeople {
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int pivot = limit / 2;
        int lessMostRight = getLessMostRight(people, pivot);
        int L = lessMostRight;
        int R = lessMostRight + 1;
        // 左边人的数量
        int leftRemain = lessMostRight + 1;
        // 右边人的数量
        int rightRemain = people.length - leftRemain;
        int merge = 0;
        while (L >= 0 && R < people.length) {
            if (people[L] + people[R] <= limit) {
                L--;
                R++;
                merge++;
                leftRemain--;
                rightRemain--;
            } else {
                L--;
            }
        }
        return (leftRemain / 2) // 左边剩余的都可以两个人一个船
                + 
                (leftRemain % 2) // 如果左边两两分完了，这里是0，如果没有，这里是1，这一个人单独一个船 
                + 
                rightRemain  // 右边剩余的只能一个人一个船
                + 
                merge // 中间部分表示左边一个人搭配右边一个人
                ;  
    }


    // 得到小于等于aim的最右位置
    public static int getLessMostRight(int[] arr, int aim) {
        int L = 0;
        int R = arr.length - 1;
        int r = L;
        while (L <= R) {
            int mid = (L + ((R - L) >> 1));
            if (arr[mid] > aim) {
                R = mid - 1;
            } else {
                r = mid;
                L = mid + 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        int aim = 3;
        System.out.println(numRescueBoats(arr, aim));
        int[] arr2 = {3, 2, 2, 1};

        System.out.println(numRescueBoats(arr2, aim));
        int[] arr3 = {3, 5, 3, 4};
        aim = 5;
        System.out.println(numRescueBoats(arr3, aim));
    }
}
