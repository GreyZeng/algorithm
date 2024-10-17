package git.snippet.random;

// 不等概率随机函数变成等概率随机函数
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
public class Code_EqualProbabilityRandom {

    // 不等概率函数，80%的概率返回0，20%的概率返回1
    // 内部内容不可见
    public static int f() {
        return Math.random() < 0.8 ? 0 : 1;
    }

    // 该函数功能是等概率返回0和1
    // 由于 0 和 1 的概率是不等的
    // 但是随机两次， 0 1 或者 1 0 出现的概率一定是相等的
    public static int g() {
        int first;
        do {
            first = f(); // 0 1
        } while (first == f());
        return first;
    }

    public static void main(String[] args) {
        final int testTimes = 10000000;
        // count[0] 统计0出现的次数
        // count[1] 统计1出现的次数
        int[] count = new int[2];
        for (int i = 0; i < testTimes; i++) {
            count[g()]++;
        }
        System.out.println("0 出现的次数:" + count[0]);
        System.out.println("1 出现的次数:" + count[1]);
        System.out.println("两个数应该差不多大");
    }
}
