package snippet;

public class Code_0004_RandToPow2 {
    // 将`[0,x)`中的数出现的的概率调整成`x^2`
    public static double randToPow2() {
        return Math.max(Math.random(), Math.random());
    }

    // 测试用例
    public static void main(String[] args) {
        int count = 0;
        int testTimes = 10000000;
        double x = 0.17;
        for (int i = 0; i < testTimes; i++) {
            if (randToPow2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));
    }
}
