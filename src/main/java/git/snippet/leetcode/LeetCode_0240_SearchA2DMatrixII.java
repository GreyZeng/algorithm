package git.snippet.leetcode;

// 给定一个每一行有序、每一列也有序，整体可能无序的二维数组
// 再给定一个数num，
// 返回二维数组中有没有num这个数
// https://leetcode.cn/problems/search-a-2d-matrix-ii/
public class LeetCode_0240_SearchA2DMatrixII {
  // 从右上角或者左下角开始
  // 时间复杂度是O(M+N)
  public boolean searchMatrix(int[][] matrix, int target) {
    int startX = 0;
    int startY = matrix[0].length - 1;
    if (matrix[startX][startY] == target) {
      return true;
    }
    while (startX < matrix.length && startY >= 0) {
      if (matrix[startX][startY] < target) {
        startX++;
      } else if (matrix[startX][startY] > target) {
        startY--;
      } else {
        return true;
      }
    }
    return false;
  }
}
