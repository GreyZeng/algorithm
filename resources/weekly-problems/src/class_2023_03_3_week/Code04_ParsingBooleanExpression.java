package class_2023_03_3_week;

// 布尔表达式 是计算结果不是 true 就是 false 的表达式
// 有效的表达式需遵循以下约定：
// 't'，运算结果为 true
// 'f'，运算结果为 false
// '!(subExpr)'，运算过程为对内部表达式 subExpr 进行 逻辑非（NOT）运算
// '&(subExpr1, subExpr2, ..., subExprn)'
// 运算过程为对 2 个或以上内部表达式 
// subExpr1, subExpr2, ..., subExprn 进行 逻辑与（AND）运算
// '|(subExpr1, subExpr2, ..., subExprn)'
// 运算过程为对 2 个或以上内部表达式
// subExpr1, subExpr2, ..., subExprn 进行 逻辑或（OR）运算
// 给你一个以字符串形式表述的 布尔表达式 expression，返回该式的运算结果。
// 题目测试用例所给出的表达式均为有效的布尔表达式，遵循上述约定。
// 测试链接 : https://leetcode.cn/problems/parsing-a-boolean-expression/
public class Code04_ParsingBooleanExpression {

	public static boolean parseBoolExpr(String expression) {
		return f(expression.toCharArray(), 0).ans;
	}

	public static class Info {
		public boolean ans;
		// 结束下标！
		public int end;

		public Info(boolean a, int e) {
			ans = a;
			end = e;
		}
	}

	// 核心递归
	public static Info f(char[] exp, int index) {
		char judge = exp[index];
		if (judge == 'f') {
			return new Info(false, index);
		} else if (judge == 't') {
			return new Info(true, index);
		} else {
			// !
			// &
			// |
			// 再说！
			boolean ans;
			// ! ( ?
			// i i+1 i+2
			// & ( ?
			// i i+1 i+2
			// | ( ?
			// i i+1 i+2
			index += 2;
			if (judge == '!') {
				// ! ( ?...... )
				// i i+1 i+2
				Info next = f(exp, index);
				ans = !next.ans;
				index = next.end + 1;
			} else {
				// &
				// i
				// judge == '&' 或者 judge == '|'
				ans = judge == '&';
				while (exp[index] != ')') {
					if (exp[index] == ',') {
						index++;
					} else {
						Info next = f(exp, index);
						if (judge == '&') {
							if (!next.ans) {
								ans = false;
							}
						} else {
							if (next.ans) {
								ans = true;
							}
						}
						index = next.end + 1;
					}
				}
			}
			return new Info(ans, index);
		}
	}

//	public static Info f(char[] exp, int index) {
//		char judge = exp[index];
//		if (judge == 'f') {
//			return new Info(false, index);
//		} else if (judge == 't') {
//			return new Info(true, index);
//		} else {
//			// !
//			// &
//			// |
//			// 再说！
//			boolean ans;
//			// ! ( ?
//			// i i+1 i+2
//			// & ( ?
//			// i i+1 i+2
//			// | ( ?
//			// i i+1 i+2
//			index += 2;
//			if (judge == '!') {
//				// ! ( ?...... )
//				// i i+1 i+2
//				Info next = f(exp, index);
//				ans = !next.ans;
//				index = next.end + 1;
//			} else if (judge == '&') {
//				// & ( ?... , ?.... , ?.... )
//				// i i+1 index
//				ans = true;
//				while (exp[index] != ')') {
//					if (exp[index] == ',') {
//						index++;
//					} else {
//						Info next = f(exp, index);
//						if (!next.ans) {
//							ans = false;
//						}
//						index = next.end + 1;
//					}
//				}
//			} else {
//				ans = false;
//				while (exp[index] != ')') {
//					if (exp[index] == ',') {
//						index++;
//					} else {
//						Info next = f(exp, index);
//						if (next.ans) {
//							ans = true;
//						}
//						index = next.end + 1;
//					}
//				}
//			}
//			return new Info(ans, index);
//		}
//	}

}
