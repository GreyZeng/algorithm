//Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that generates a uniform random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call any other API. Please do not use a language's built-in random API.
//
//        Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing. Note that this is not an argument passed to rand10().
//
//        Follow up:
//
//        What is the expected value for the number of calls to rand7() function?
//        Could you minimize the number of calls to rand7()?
//
//
//        Example 1:
//
//        Input: n = 1
//        Output: [2]
//        Example 2:
//
//        Input: n = 2
//        Output: [2,8]
//        Example 3:
//
//        Input: n = 3
//        Output: [3,8,10]
//
//
//        Constraints:
//
//        1 <= n <= 10^5
package leetcode.medium;

public class LeetCode_0470_ImplementRand10UsingRand7 {
    public int rand10() {
        return rand(10);
    }

    public int rand(int N) {
        int bit = 1;
        int base = 2;
        while (base <= N) {
            base = 2 << bit;
            bit++;
        }
        int v = build(bit);
        while (v < 1 || v > N) {
            v = build(bit);
        }
        return v;
    }

    private int build(int bit) {
        int v = 0;
        for (int i = 0; i < bit; i++) {
            v += (zeroOne() << i);
        }
        return v;
    }


    public int zeroOne() {
        int i = rand7();
        while (i == 7) {
            i = rand7();
        }
        return (i == 1 || i == 2 || i == 3) ? 0 : 1;
    }

    public int rand7() {
        return (int) Math.random() * 7 + 1;
    }
}
