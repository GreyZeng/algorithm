//链接：https://www.nowcoder.com/questionTerminal/d3347e177dca478196dfa558a29fb996
//来源：牛客网
//
//游戏规则如下
//- 在棋盘上有N个数字（A1~AN）从左到右排列成一行
//- A，B两个玩家轮流进行游戏，第一回合A玩家行动，第二回合B玩家行动，依次行动直到游戏结束
//- 每回合玩家可以选择拿走棋盘上最左边或者最右边的一个数字，其余的都不能拿
//- 拿走的数字依次从左到右排列在自己面前
//- 棋盘上所有数字被拿走后游戏结束
//- 最优策略的说明：在任意局面下，玩家如果取左边的数字或者取右边的数字，最终最优得分都一样，那么只能取左边的数字
//
//当所有数字都被拿走后，A,B两个玩家面前都各有一个数列。
//假设A玩家面前数字从左到右为 X1,X2,X3...XM，则他的最终得分Sa计算方式如下（B玩家的得分计算Sb也类似，不赘述）：
//Sa = abs(X1-0) + abs(X2-X1) + abs(X3-X2) + ... + abs(XM - X(M-1))
//
//
//请计算在以上的规则下，如果两个玩家都想拿到尽量多的分数，用最优策略进行游戏，计算两个人的最终得分。
//
//
//输入描述:
//第一行一个数字 N, 一半的测试用例 (0 < N <= 50)，一半的测试用例 (0 < N <= 1000)
//第二行N个数字 Ai ( 0 <= Ai <= 50)
//
//
//输出描述:
//用空格隔开的两个整数Sa和Sb
//示例1
//输入
//4
//1 2 3 4
//输出
//7 4
package nowcoder;

// //https://www.nowcoder.com/questionTerminal/d3347e177dca478196dfa558a29fb996
public class NowCoder_CardsInLine {
   // TODO
}
