package snippet;

// 将`[0,x)`中的数出现的的概率调整成`x^2`
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
public class Code_RandToPow2 {
    // 将`[0,x)`中的数出现的的概率调整成`x^2`
    public static double randToPow2() {
        return Math.max(Math.random(), Math.random());
    }

    // 测试用例
    public static void main(String[] args) {
        int count = 0;
        int testTimes = 30000000;
        double x = 0.17;
        for (int i = 0; i < testTimes; i++) {
            if (randToPow2() < x) {
                count++;
            }
        }
        // 以下两个数值应该大小一致
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));
    }
}
