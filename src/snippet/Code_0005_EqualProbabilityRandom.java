package snippet;

// 不等概率随机函数变成等概率随机函数
public class Code_0005_EqualProbabilityRandom {

    // 不等概率函数，
    // 内部内容不可见
    public static int f() {
        return Math.random() < 0.8 ? 0 : 1;
    }

    // 等概率返回0和1
    public static int g() {
        int first;
        do {
            first = f(); // 0 1
        } while (first == f());
        return first;
    }

}
