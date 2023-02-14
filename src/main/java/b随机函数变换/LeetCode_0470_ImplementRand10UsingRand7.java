package b随机函数变换;

// https://leetcode.cn/problems/implement-rand10-using-rand7/
// 如何用a~b的随机函数加工出c~d的随机函数
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
public class LeetCode_0470_ImplementRand10UsingRand7 {
    // 第一种方法
    public int rand10x() {
        return (rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7()
                + rand7()) % 10 + 1;
    }

    // 第二种方法
    public int rand10() {
        return rand(10);
    }

    // 等概率返回[0,N]
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
            v += (m() << i);
        }
        return v;
    }

    // 核心：生成 0 和 1 等概率返回的随机函数
    public int m() {
        int i = rand7();
        while (i == 7) {
            i = rand7();
        }
        return (i == 1 || i == 2 || i == 3) ? 0 : 1;
    }

    // 不用复制到leetcode里面
    public int rand7() {
        return (int) Math.random() * 7 + 1;
    }
}
