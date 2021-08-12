package lintcode;

import java.util.ArrayList;
import java.util.List;

//描述
//给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 初始二维矩阵全0. 二元数组A内的k个元素代表k次操作, 
// 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿. 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.
//
//设定0表示海洋, 1代表岛屿, 并且上下左右相邻的1为同一个岛屿.
// https://www.lintcode.com/problem/434/
// TODO
public class LintCode_0434_NumberOfIslandsII {
	class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public List<Integer> numIslands2(int n, int m, Point[] operators) {
		return new ArrayList<>();
	}
}
