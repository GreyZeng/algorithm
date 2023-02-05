package 算法.位运算;

// 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
public class Code_Near2Power {

    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    public static int tableSizeFor(int n) {
        n--;
        // 无符号右移，最左侧的1开始，一直到最右侧都变成1
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args) {
        int cap = 120;
        System.out.println(tableSizeFor(cap));
    }
}
