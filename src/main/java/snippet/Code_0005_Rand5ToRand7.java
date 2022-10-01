package snippet;

// 如何用1~5的随机函数加工出1~7的随机函数
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
public class Code_0005_Rand5ToRand7 {

    // 此函数只能用，不能修改
    // 等概率返回1~5
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    // 通过[0,5]等概率返回的随机函数f()
// 加工出等概率得到0和1
// 1,2,3,4,5 五个数
// 得到1，2的时候，返回0
// 得到4，5的时候，返回1
// 得到3的时候，弃而不用，再次尝试
    public static int m() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 等概率返回0~6
    public static int x() {
        int ans = 0;
        do {
            ans = (m() << 2) + (m() << 1) + m();
        } while (ans == 7);
        return ans;
    }

    // 等概率返回1~7
    public static int g() {
        return x() + 1;
    }

    public static void main(String[] args) {
        final int testTimes = 100000000;
        // count[0] 存 0 出现的次数
        // count[1] 存 1 出现的次数
        // count[2] 存 2 出现的次数
        // ...
        // count[6] 存 6 出现的次数
        // count[7] 存 7 出现的次数

        int[] count = new int[8];
        for (int i = 0; i < testTimes; i++) {
            count[g()]++;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println(i + " 出现的次数是：" + count[i]);
        }
    }
}
