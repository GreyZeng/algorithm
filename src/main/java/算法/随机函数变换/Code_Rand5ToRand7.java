package 算法.随机函数变换;

// 如何用1~5的随机函数加工出1~7的随机函数
// 笔记见：https://www.cnblogs.com/greyzeng/p/16618329.html
public class Code_Rand5ToRand7 {

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

  // 方法1：等概率返回1~7
  public static int g() {
    return x() + 1;
  }

  // 方法2：等概率返回1~7
  public static int g2() {
    return (f() + f() + f() + f() + f() + f() + f()) % 7 + 1;
  }

  public static void main(String[] args) {
    final int testTimes = 100000000;
    // count[i] 存 i 出现的次数

    int[] count = new int[8];
    int[] count2 = new int[8];
    for (int i = 0; i < testTimes; i++) {
      count[g()]++;
      count2[g2()]++;
    }
    for (int i = 0; i < count.length; i++) {
      System.out.println(i + " 出现的次数是：" + count[i]);
    }
    System.out.println("======");
    for (int i = 0; i < count2.length; i++) {
      System.out.println(i + " 出现的次数是：" + count2[i]);
    }
  }
}
