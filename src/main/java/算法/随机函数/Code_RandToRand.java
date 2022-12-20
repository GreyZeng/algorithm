package 算法.随机函数;

// TODO
// 加入笔记：https://www.cnblogs.com/greyzeng/p/16618329.html
// 随机函数构造新随机函数问题
// random 0-7 生成 random 0-9
// 生成0，1源，用二进制来构造0和9
//
//
// 0 P
// 1 1-P
// ---》生成0，1等概率
// 构造两个
// 01 ---> 返回0
// 10 ---> 返回1
// 11 ---> x
// 00 ---> x
// 这样就构造了一个0，1源
public class Code_RandToRand {
  // 底层依赖一个等概率返回a~b的随机函数f，
  // 如何加工出等概率返回c~d的随机函数
  public static int g(int a, int b, int c, int d) {
    int range = d - c; // 0～range c~d -> 0 ~ d-c
    int num = 1;

    while ((1 << num) - 1 < range) { // 求0～range需要几个2进制位
      num++;
    }
    int ans = 0; // 最终的累加和，首先+0位上是1还是0，1位上是1还是0，2位上是1还是0...
    do {
      ans = 0;
      for (int i = 0; i < num; i++) {
        ans += (rand01(a, b) << i);
      }
    } while (ans > range);
    return ans + c;
  }

  // 底层依赖一个等概率返回a~b的随机函数f，
  // 如何加工出等概率返回0和1的随机函数
  public static int rand01(int a, int b) {
    // a = 4, b = 8
    // size = 5
    int size = b - a + 1;
    // odd = true
    boolean odd = size % 2 != 0;
    // mid = 2
    int mid = size / 2;
    int ans = 0;
    do {
      // 如果是奇数，那么中点位置弃用，< 中点 位置 和 > 中点位置的数字返回概率一样，一个定为0，一个定为1
      // 如果是偶数，那么这里的中点是上中点，< 上中点 位置 和 >= 上中点位置的数字出现的概率一样，一个定为0，一个定为1即可
      ans = f(a, b) - a;
    } while (odd && ans == mid);
    // 来到这步的时候，
    // 如果是偶数，ans可能会等于mid
    // 如果是奇数，ans必不等于mid
    return ans < mid ? 0 : 1;
  }

  // 构造一个等概率返回a~b的随机函数
  public static int f(int a, int b) {
    // Math.random() -> [0, 1)
    // [a, b] -> a + [0,b-a] -> a + (int)(Math.random() * (b-a+1))
    return a + (int) (Math.random() * (b - a + 1));
  }

  // 底层依赖一个以p概率返回0，以1-p概率返回1的随机函数rand01p
  // 如何加工出等概率返回0和1的函数
  public static int rand01(double p) {
    int num;
    do {
      num = rand01p(p);
    } while (num == rand01p(p));
    return num;
  }

  public static int rand01p(double p) {
    return Math.random() < p ? 0 : 1;
  }

  // 也可以
  // 随机生成[1,5]
  public static int f() {
    return (int) (Math.random() * 5) + 1;
  }

  // (f() + f() + f() + f() + f() + f() + f()) % 7 --> 随机等概率生成 0-6
  // 所以g是随机生成等概率[1,7]
  public static int g() {
    return ((f() + f() + f() + f() + f() + f() + f()) % 7) + 1;
  }

  public static void main(String[] args) {
    int a = 5;
    int b = 170;
    int c = 3;
    int d = 20;
    int[] ans = new int[d + 1];
    int testTime1 = 1000000;
    for (int i = 0; i < testTime1; i++) {
      ans[g(a, b, c, d)]++;
    }
    for (int i = 0; i < ans.length; i++) {
      System.out.println(ans[i]);
    }

    int testTime2 = 10000000;
    int count2 = 0;
    // p是返回0的概率，1-p是返回1的概率，不管你怎么指定，都能调成0和1等概率返回
    double p = 0.88;
    for (int i = 0; i < testTime2; i++) {
      if (rand01(p) == 0) {
        count2++;
      }
    }
    System.out.println((double) count2 / (double) testTime2);

  }
}
