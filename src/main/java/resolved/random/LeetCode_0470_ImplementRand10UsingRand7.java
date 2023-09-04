package resolved.random;

// https://leetcode.com/problems/implement-rand10-using-rand7/
// 如何用a~b的随机函数加工出c~d的随机函数
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
// 随机 [1, 7] 生成 随机[1, 10]
public class LeetCode_0470_ImplementRand10UsingRand7 {

    // 更好的一种解法
    public int rand10() {
        return rand09() + 1;
    }

    // 随机生成[0,9]的函数
    // 生成0~9，至少需要4个二进制位
    public int rand09() {
        int rand;
        do {
            rand = (rand01() << 3) + (rand01() << 2) + (rand01() << 1) + rand01();
            // 大于9的数直接重试
        } while (rand > 9);
        return rand;
    }

    // 等概率生成0和1的种子函数
    //
    public int rand01() {
        int rand;
        do {
            rand = rand7();
            // [1, 2, 3] ---> 1
            // [5, 6, 7] ---> 0
        } while (rand == 4);
        return rand > 4 ? 0 : 1;
    }

    public int rand7() {
        return ((int) (Math.random() * 7)) + 1;
    }


    // 常规解法
    public int rand102() {
        return (rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7()) % 10 + 1;
    }
}
