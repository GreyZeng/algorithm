/*链接：https://www.nowcoder.com/questionTerminal/ed0334a5e88f4662bb69374b308862d8
        来源：牛客网

        牛牛和羊羊都很喜欢青草。今天他们决定玩青草游戏。
        最初有一个装有n份青草的箱子,牛牛和羊羊依次进行,牛牛先开始。在每个回合中,每个玩家必须吃一些箱子中的青草,所吃的青草份数必须是4的x次幂,
        比如1,4,16,64等等。不能在箱子中吃到有效份数青草的玩家落败。假定牛牛和羊羊都是按照最佳方法进行游戏,请输出胜利者的名字。

        输入描述:
        输入包括t+1行。
        第一行包括一个整数t(1 ≤ t ≤ 100),表示情况数.
        接下来t行每行一个n(1 ≤ n ≤ 10^9),表示青草份数


        输出描述:
        对于每一个n,如果牛牛胜利输出"niu",如果羊羊胜利输出"yang"。
        示例1
        输入
        3
        1
        2
        3
        输出
        niu
        yang
        niu*/
package nowcoder;


public class NowCoder_EatGrass {
    public static String winner(int n) {
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (n < 5) { // base case
            return (n == 0 || n == 2) ? "yang" : "niu";
        }
        // n >= 5 时
        int base = 1; // 当前先手决定吃的草数
        // 当前是先手在选
        while (base <= n) {
            // 当前一共n份草，先手吃掉的是base份，n - base 是留给后手的草
            // 母过程 先手 在子过程里是 后手
            if (winner(n - base).equals("yang")) {
                return "niu";
            }
            if (base > (n >> 2)) { // 防止base*4之后溢出
                break;
            }
            base <<= 2;
        }
        return "yang";
    }

    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "yang";
        } else {
            return "niu";
        }
    }

    public static void main(String[] args) {
        int times = 20000;
        int maxValue = 55;
        int t = 0;
        for (int i = 0; i < times; i++) {
            t = (int) (Math.random() * maxValue) + 1;
            if (!winner(t).equals(winner2(t))) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish");
    }

}
