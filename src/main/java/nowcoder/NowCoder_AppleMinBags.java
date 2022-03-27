//题目
//        小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
//        1）能装下6个苹果的袋子
//        2）能装下8个苹果的袋子
//        小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
//        给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
// 假设100个苹果

// 1. 最多需要100/8 = 12个 8号袋，剩下需要几个六号袋子，如果不满足，则2
// 2. 减少一个8号袋子，剩下能否被6号袋搞定

// 奇数返回-1
// 剩余大于等于24，就不需要试的
package nowcoder;

// https://www.nowcoder.com/questionTerminal/61cfbb2e62104bc8aa3da5d44d38a6ef
public class NowCoder_AppleMinBags {
    public static int minBags(int n) {
        if (n % 8 == 0) {
            return n / 8;
        }
        int use8 = n / 8;
        int rest = n % 8;
        while (rest != 0) {
            if (rest % 6 == 0) {
                return use8 + (rest / 6);
            } else {
                if (use8 > 0) {
                    use8--;
                    rest += 8;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    // 打表方式优化
    public static int minBags2(int n) {
        if (n <= 5 || n == 10 || (n & 1) == 1) {
            return -1;
        }
        if (n == 6 || n == 8) {
            return 1;
        }

        return n % 8 == 0 ? n / 8 : n / 8 + 1;
    }


    public static void main(String[] args) {
        for (int i = 1; i < 340000; i++) {
            int ans1 = minBags(i);
            int ans2 = minBags2(i);
            if (ans1 != ans2) {
                System.out.println(i + " ans1 = " + ans1 + " ans2=" + ans2);
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish");

    }

}
