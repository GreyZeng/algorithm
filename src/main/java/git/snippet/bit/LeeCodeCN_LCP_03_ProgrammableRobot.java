package bit;

import java.util.Arrays;
import java.util.HashSet;

// TODO

// 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
// U: 向y轴正方向移动一格
// R: 向x轴正方向移动一格。
// 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
// 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
// 示例 1：
// 输入：command = "URR", obstacles = [], x = 3, y = 2
// 输出：true
// 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
// 示例 2：
// 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
// 输出：false
// 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
// 示例 3：
// 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
// 输出：true
// 解释：到达终点后，再碰到障碍物也不影响返回结果。
// 限制：
// 2 <= command的长度 <= 1000
// command由U，R构成，且至少有一个U，至少有一个R
// 0 <= x <= 1e9, 0 <= y <= 1e9
// 0 <= obstacles的长度 <= 1000
// obstacles[i]不为原点或者终点
// 链接：https://leetcode-cn.com/problems/programmable-robot/
// TIPS:
// bool f(x,y), ==> 机器人是否会撞到x,y
// 某个指令下，第一轮能到达的点是哪些，记录到map里面
// 假设得到x,y在一轮可以到的位置是3,2
// 目标位置是29，32
// 32 / 2 = 16 29/3=9
// 看9，16是否在map里面，如果在，则可以，如果不在，则不可能到目标位置
// 除了可以用A_B + Set来确定这个map，但是可以根据command的数量，用整型来替换map
// 用位图优化set
public class LeeCodeCN_LCP_03_ProgrammableRobot {

  public static boolean robot1(String command, int[][] obstacles, int x, int y) {
    int X = 0;
    int Y = 0;
    HashSet<Integer> set = new HashSet<>();
    set.add(0);
    for (char c : command.toCharArray()) {
      X += c == 'R' ? 1 : 0;
      Y += c == 'U' ? 1 : 0;
      set.add((X << 10) | Y);
    }
    // 不考虑任何额外的点，机器人能不能到达，(x，y)
    if (!meet1(x, y, X, Y, set)) {
      return false;
    }
    for (int[] ob : obstacles) { // ob[0] ob[1]
      if (ob[0] <= x && ob[1] <= y && meet1(ob[0], ob[1], X, Y, set)) {
        return false;
      }
    }
    return true;
  }

  // 一轮以内，X，往右一共有几个单位
  // Y, 往上一共有几个单位
  // set, 一轮以内的所有可能性
  // (x,y)要去的点
  // 机器人从(0,0)位置，能不能走到(x,y)
  public static boolean meet1(int x, int y, int X, int Y, HashSet<Integer> set) {
    if (X == 0) { // Y != 0 往上肯定走了！
      return x == 0;
    }
    if (Y == 0) {
      return y == 0;
    }
    // 至少几轮？
    int atLeast = Math.min(x / X, y / Y);
    // 经历过最少轮数后，x剩多少？
    int rx = x - atLeast * X;
    // 经历过最少轮数后，y剩多少？
    int ry = y - atLeast * Y;
    return set.contains((rx << 10) | ry);
  }

  // 此处为一轮以内，x和y最大能移动的步数，对应的2的几次方
  // 比如本题，x和y最大能移动1000步，就对应2的10次方
  // 如果换一个数据量，x和y最大能移动5000步，就对应2的13次方
  // 只需要根据数据量修改这一个变量，剩下的代码不需要调整
  public static final int bit = 10;
  // 如果，x和y最大能移动的步数，对应2的bit次方
  // 那么一个坐标(x,y)，所有的可能性就是：(2 ^ bit) ^ 2 = 2 ^ (bit * 2)
  // 也就是，(1 << (bit << 1))个状态，记为bits
  public static int bits = (1 << (bit << 1));
  // 为了表示下bits个状态，需要几个整数？
  // 32位只需要一个整数，所以bits个状态，需要bits / 32 个整数
  // 即整型长度需要 : bits >> 5
  public static int[] set = new int[bits >> 5];

  public static boolean robot2(String command, int[][] obstacles, int x, int y) {
    Arrays.fill(set, 0);
    set[0] = 1;
    int X = 0;
    int Y = 0;
    for (char c : command.toCharArray()) {
      X += c == 'R' ? 1 : 0;
      Y += c == 'U' ? 1 : 0;
      add((X << 10) | Y);
    }
    if (!meet2(x, y, X, Y)) {
      return false;
    }
    for (int[] ob : obstacles) {
      if (ob[0] <= x && ob[1] <= y && meet2(ob[0], ob[1], X, Y)) {
        return false;
      }
    }
    return true;
  }

  public static boolean meet2(int x, int y, int X, int Y) {
    if (X == 0) {
      return x == 0;
    }
    if (Y == 0) {
      return y == 0;
    }
    int atLeast = Math.min(x / X, y / Y);
    int rx = x - atLeast * X;
    int ry = y - atLeast * Y;
    return contains((rx << 10) | ry);
  }

  public static void add(int status) {
    set[status >> 5] |= 1 << (status & 31);
  }

  public static boolean contains(int status) {
    return (status < bits) && (set[status >> 5] & (1 << (status & 31))) != 0;
  }

  // int num -> 32位的状态
  // 请打印这32位状态
  public static void printBinary(int num) {
    for (int i = 31; i >= 0; i--) {
      System.out.print((num & (1 << i)) != 0 ? "1" : "0");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int x = 7;
    printBinary(x);

    int y = 4;

    printBinary(y);

    // x_y 组合！
    int c = (x << 10) | y;
    printBinary(c);

    System.out.println(c);

    // 0 ~ 1048575 任何一个数，bit来表示的！
    // int[] set = new int[32768];
    // set[0] = int 32 位 0~31这些数出现过没出现过
    // set[1] = int 32 位 32~63这些数出现过没出现过

    // 0 ~ 1048575
    // int[] set = new int[32768];
    // int num = 738473; // 32 bit int
    //// set[ 734873 / 32 ] // 734873 % 32
    // boolean exist = (set[num / 32] & (1 << (num % 32))) != 0;

  }
}
