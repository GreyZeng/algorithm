//题目
//        小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
//        1）能装下6个苹果的袋子
//        2）能装下8个苹果的袋子
//        小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
//        给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
package code;

import javax.sound.midi.Soundbank;

/**
 * @author: Grey
 * @date: 2020/12/17
 **/
public class Code_0060_AppleMinBags {
    public static int minBags(int apple) {
        if (apple < 6) {
            return -1;
        }
        if (apple == 7) {
            return -1;
        }
        if (apple % 8 == 0) {
            return apple / 8;
        }
        int max8 = apple / 8; // 最多需要几个8
        int min6;
        int rest = apple % 8;
        int min = max8;
        while (rest != 0) {
            if (rest % 6 == 0) {
                min6 = rest / 6;
                min = max8 + min6;
                return min;
            } else {
                if (max8 > 0) {
                    max8--;
                    rest += 8;
                } else {
                    break;
                }
            }
        }
        return rest == 0 ? min : -1;
    }

    // 打表方式优化

    public static int minBags2(int apple) {

        if ((apple & 1) == 1) {
            return -1;
        }
        if (apple <= 17) {
            if (apple == 6 || apple == 8) {
                return 1;
            }
            if (apple == 12 || apple == 14 || apple == 16) {
                return 2;
            }
            return -1;
        }
        if (apple % 8 == 0) {
            return apple / 8;
        }
        return apple / 8 + 1;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
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
