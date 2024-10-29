package git.snippet.snippet;

// 给定一个参数N，返回1!+2!+3!+4!+…+N!的结果
public class Code_SumOfFactorial {

	public static void main(String[] args) {
		System.out.println("test begin");
		int times = 18;
		Code_SumOfFactorial test = new Code_SumOfFactorial();
		if (test.f1(11) != 43954713L) {
			System.out.println("出错了");
		}
		if (test.f2(11) != 43954713L) {
			System.out.println("出错了");
		}
		for (int i = 1; i <= times; i++) {
			if (test.f1(i) != test.f2(i)) {
				System.out.println("出错了");
				break;
			}
		}
		System.out.println("test end");
	}

	public long f1(int num) {
		long result = 0;
		for (int i = 1; i <= num; i++) {
			result += factorial(i);
		}
		return result;
	}

	public long factorial(int n) {
		long result = 1L;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public long f2(int num) {
		long result = 0;
		long pre = 1;
		for (int i = 1; i <= num; i++) {
			pre = pre * i;
			result += pre;
		}
		return result;
	}
}
