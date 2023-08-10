package resolved.random;

// https://leetcode.com/problems/implement-rand10-using-rand7/
// 如何用a~b的随机函数加工出c~d的随机函数
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
// 随机 [1, 7] 生成 随机[1, 10]
public class LeetCode_0470_ImplementRand10UsingRand7 {
    // 第一种方法
    public int rand10() {
        return (rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7()) % 10 + 1;
    }

    // 第二种方法
    public int rand102() {
        return rand09() + 1;
    }

    // 生成0~9，需要几个二进制位
    public int rand09() {
        int rand;
        do {
            rand = (rand01() << 3) + (rand01() << 2) + (rand01() << 1) + rand01();
        } while (rand > 9);
        return rand;
    }

    // 随机生成0和1的种子函数
    public int rand01() {
        int rand;
        do {
            rand = rand7();
        } while (rand == 7);
        return rand <= 3 ? 0 : 1;
    }

    // 不用复制到leetcode里面
    public int rand7() {
        return ((int) (Math.random() * 7)) + 1;
    }
}
