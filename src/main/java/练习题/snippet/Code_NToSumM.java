package 练习题.snippet;

//定义一种数：可以表示成若干（数量>1）连续正数和的数
//        比如:
//        5 = 2+3，5就是这样的数
//        12 = 3+4+5，12就是这样的数
//        1不是这样的数，因为要求数量大于1个、连续正数和
//        2 = 1 + 1，2也不是，因为等号右边不是连续正数
//        给定一个参数N，返回是不是可以表示成若干连续正数和的数
// 笔记：https://www.cnblogs.com/greyzeng/p/16651527.html
public class Code_NToSumM {

    public static boolean isMSum1(int num) {
        if (num <= 2) {
            return false;
        }
        int sum = 1;

        for (; sum < num; sum++) {
            int o = sum;
            int i = sum + 1;
            sum += i;
            if (sum > num) {
                return false;
            }
            while (sum < num) {
                i++;
                sum += i;
            }
            if (sum == num) {
                return true;
            }
            sum = o;
        }
        return false;
    }

    // 如何判断一个数是不是2的某次方？只需要判断这个数的二进制是否只有一个1.
    // num == (num & (-num))
    // num == (num & (~num + 1))
    // num & (num - 1) != 0
    public static boolean isMSum2(int num) {
        if (num == 1 || num == 2) {
            return false;
        }
        return ((num - 1) & num) != 0;
    }

    public static void main(String[] args) {
       /* for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum1(num));
        }*/
        System.out.println("test begin");
        for (int num = 1; num < 5000; num++) {
            if (isMSum1(num) != isMSum2(num)) {
                System.out.println("Oops!");
            }
        }


    }

}
