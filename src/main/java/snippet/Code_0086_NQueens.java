package snippet;

// N皇后问题
// 在N*N的棋盘上要摆N个皇后， 要求任何两个皇后不同行、不同列， 也不在同一条斜线上 给定一个整数n，返回n皇后的摆法有多少种。
// n=1，返回1 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0 n=8，返回92
// tip:
//两个点：[x,y]和[甲,乙]
//是否有冲突，只需要判断两个条件
//(y == 乙) || (|甲-x|==|乙-y|)
// https://www.nowcoder.com/questionTerminal/c76408782512486d91eea181107293b6
public class Code_0086_NQueens {
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // 编号i从0开始，一直到n-1
        // record[i] = j 表示：i号皇后放在了第j列上
        int[] record = new int[n];
        return process1(0, record, n);
    }

    // 当前来到i行，一共是0~N-1行
    // 在i行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后不打架
    // int[] record record[x] = y 之前的第x行的皇后，放在了y列上
    // 返回：不关心i以上发生了什么，i.... 后续有多少合法的方法数
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            // 居然可以顺利填到最后，返回一种方法数
            return 1;
        }
        int res = 0;
        // i行的皇后，放哪一列呢？j列，
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                // 无需回溯
                // 因为i只和之前的皇后有关系
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 0..i-1
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("start");
        int times = 16;
        for (int n = 0; n < times; n++) {
            int ans1 = num1(n);
            int ans2 = num2(n);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("end");
    }
}
