package leetcode.easy;

// 给你一个字符串columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
// 例如，
//
// A -> 1
// B -> 2
// C -> 3
// ...
// Z -> 26
// AA -> 27
// AB -> 28
// ...
//
// 示例 1:
// 输入: columnTitle = "A"
// 输出: 1
// 示例2:
// 输入: columnTitle = "AB"
// 输出: 28
// 示例3:
// 输入: columnTitle = "ZY"
// 输出: 701
// 示例 4:
// 输入: columnTitle = "FXSHRXW"
// 输出: 2147483647
// Leetcode题目 : https://leetcode.com/problems/excel-sheet-column-number/
public class LeetCode_0171_ExcelSheetColumnNumber {

    // 这道题反过来也要会写
    public static int titleToNumber(String s) {
        char[] str = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            ans = ans * 26 + (str[i] - 'A') + 1;
        }

        return ans;
    }

}
