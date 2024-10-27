package grey.algorithm;

import java.util.Arrays;

//一开始有100个人，每个人都有100元
//在每一轮都做如下的事情 :
//每个人都必须拿出1元钱给除自己以外的其他人，给谁完全随机
//如果某个人在这一轮的钱数为0，那么他可以不给，但是可以接收
//发生很多很多轮之后，这100人的社会财富分布很均匀吗？
public class Code_0001_Gini {
    public static void main(String[] args) {
        System.out.println("一个社会的基尼系数是一个在0~1之间的小数");
        System.out.println("基尼系数为0代表所有人的财富完全一样");
        System.out.println("基尼系数为1代表有1个人掌握了全社会的财富");
        System.out.println("基尼系数越小，代表社会财富分布越均衡；越大则代表财富分布越不均衡");
        System.out.println("在2022年，世界各国的平均基尼系数为0.44");
        System.out.println("目前普遍认为，当基尼系数到达 0.5 时");
        System.out.println("就意味着社会贫富差距非常大，分布非常不均匀");
        System.out.println("社会可能会因此陷入危机，比如大量的犯罪或者经历社会动荡");
        System.out.println("测试开始");
        int num = 100;
        int round = 50000000;
        System.out.println("人数 "+num);
        System.out.println("轮数 "+round);
        experiment(num, round);
        System.out.println("测试结束");
    }

    public static void experiment(int num, int round) {
        double[] people = new double[num];
        // 每人初始化是100元
        Arrays.fill(people, 100d);
        for (int k = 0; k < round; k++) {
            for (int i = 0; i < num; i++) {
                if (people[i] == 0d) {
                    continue;
                }
                int giveTo;
                do {
                    giveTo = (int) (Math.random() * num);
                } while (giveTo == i);
                people[giveTo] += 1d;
                people[i] -= 1d;
            }
        }
        double gini = calculateGini(people);
        System.out.println("基尼系数是"+gini);
    }

    // 计算基尼系数
    public static double calculateGini(double[] wealth) {
        double sum = 0d;
        double sumAbs = 0d;
        for (double v : wealth) {
            sum += v;
            for (double value : wealth) {
                sumAbs += Math.abs(value - v);
            }
        }
        return sumAbs / (sum * 2 * wealth.length);
    }
}