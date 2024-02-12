package class_2022_08_1_week;

import java.util.Stack;

// 栈只提供push、pop、isEmpty三个方法
// 请完成无序栈的排序，要求排完序之后，从栈顶到栈底从小到大
// 只能使用栈提供的push、pop、isEmpty三个方法、以及递归函数
// 除此之外不能使用任何的容器，任何容器都不许，连数组也不行
// 也不能自己定义任何结构体
// 就是只用：
// 1) 栈提供的push、pop、isEmpty三个方法
// 2) 简单返回值的递归函数
public class Code06_SortStackUsingRecursive {
	
	public static void sortStack(Stack<Integer> stack) {
		int deep = deep(stack);
		while (deep > 0) {
			int max = max(stack, deep);
			int k = times(stack, max, deep);
			down(stack, deep, max, k);
			deep -= k;
		}
	}

	// 返回栈的深度
	// 不改变栈的数据状况
	// stack push pop isEmpty
	public static int deep(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return 0;
		}
		int num = stack.pop();
		int deep = deep(stack) + 1;
		stack.push(num);
		return deep;
	}

	// 从栈当前的顶部开始，往下数deep层
	// ) 返回这deep层里的最大值
	public static int max(Stack<Integer> stack, int deep) {
		if (deep == 0) {
			return Integer.MIN_VALUE;
		}
		int num = stack.pop();
		int restMax = max(stack, deep - 1);
		int max = Math.max(num, restMax);
		stack.push(num);
		return max;
	}

	// 从栈当前的顶部开始，往下数deep层，已知最大值是max了
	// 返回，max出现了几次，不改变栈的数据状况
	public static int times(Stack<Integer> stack, int max, int deep) {
		if (deep == 0) {
			return 0;
		}
		int num = stack.pop();
		int restTimes = times(stack, max, deep - 1);
		int times = restTimes + (num == max ? 1 : 0);
		stack.push(num);
		return times;
	}

	// 从栈当前的顶部开始，往下数deep层，已知最大值是max，出现了k次
	// 请把这k个最大值沉底，剩下的数据状况不变
	public static void down(Stack<Integer> stack, int deep, int max, int k) {
		if (deep == 0) {
			for (int i = 0; i < k; i++) {
				stack.push(max);
			}
		} else {
			int num = stack.pop();
			down(stack, deep - 1, max, k);
			if (num != max) {
				stack.push(num);
			}
		}
	}

	// 为了测试
	// 生成随机栈
	public static Stack<Integer> generateRandomStack(int n, int v) {
		Stack<Integer> ans = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			ans.add((int) (Math.random() * v));
		}
		return ans;
	}

	// 为了测试
	// 检测栈是不是有序的
	public static boolean isSorted(Stack<Integer> stack) {
		int step = Integer.MIN_VALUE;
		while (!stack.isEmpty()) {
			if (step > stack.peek()) {
				return false;
			}
			step = stack.pop();
		}
		return true;
	}

	// 为了测试
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.add(1);
		test.add(5);
		test.add(4);
		test.add(5);
		test.add(3);
		test.add(2);
		test.add(3);
		test.add(1);
		test.add(4);
		test.add(2);
		// 1 5 4 5 3 2 3 1 4 2
		sortStack(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

		int N = 20;
		int V = 20;
		int testTimes = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N);
			Stack<Integer> stack = generateRandomStack(n, V);
			sortStack(stack);
			if (!isSorted(stack)) {
				System.out.println("出错了!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
