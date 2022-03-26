package snippet;

/**
 * 贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额，一次购买只能出一瓶可乐， 且投钱和找零都遵循优先使用大钱的原则，
 * 需要购买的可乐数量是m，其中手头拥有的10、50、100的数量分别为a、b、c， 可乐的价格是x(x是10的倍数)，请计算出需要投入硬币次数？
 * 限制：可乐的数量在10^18次方
 *
 * @author GreyZeng
 */
public class Code_0104_Cola {

	// 暴力尝试，为了验证正式方法而已
	public static int right(int m, int a, int b, int c, int x) {
		int[] qian = { 100, 50, 10 };
		int[] zhang = { c, b, a };
		int puts = 0;
		while (m != 0) {
			int cur = buy(qian, zhang, x);
			if (cur == -1) {
				return -1;
			}
			puts += cur;
			m--;
		}
		return puts;
	}

	public static int buy(int[] qian, int[] zhang, int rest) {
		int first = -1;
		for (int i = 0; i < 3; i++) {
			if (zhang[i] != 0) {
				first = i;
				break;
			}
		}
		if (first == -1) {
			return -1;
		}
		if (qian[first] >= rest) {
			zhang[first]--;
			giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
			return 1;
		} else {
			zhang[first]--;
			int next = buy(qian, zhang, rest - qian[first]);
			if (next == -1) {
				return -1;
			}
			return 1 + next;
		}
	}

	// 正式的方法
	// 可乐数量num,100,50,10的张数分别为n100，n50，n10,可乐单价是price
	public static int putTimes(int num, int n10, int n50, int n100, int price) {
		int[] qian = { 100, 50, 10 };
		int[] zhang = { n100, n50, n10 };
		int times = 0;
		int preMoney = 0;
		int preZhang = 0;
		for (int i = 0; i < 3 && num != 0; i++) {
			int firstNeedZhang = (price - preMoney + qian[i] - 1) / qian[i];
			if (zhang[i] >= firstNeedZhang) {
				// 找回的钱用于提升其他种类钱的数量
				giveRest(qian, zhang, i + 1, (preMoney + qian[i] * firstNeedZhang) - price, 1);
				times += firstNeedZhang + preZhang;
				zhang[i] -= firstNeedZhang;
				num--;
			} else {
				preMoney += qian[i] * zhang[i];
				preZhang += zhang[i];
				continue;
			}
			// 买接下来的可乐
			int curQianBuyOneColaZhang = (price + qian[i] - 1) / qian[i];
			int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, num);
			int oneTimeRest = qian[i] * curQianBuyOneColaZhang - price;
			giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
			times += curQianBuyOneColaZhang * curQianBuyColas;
			num -= curQianBuyColas;
			zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
			preMoney = qian[i] * zhang[i];
			preZhang = zhang[i];
		}
		return num == 0 ? times : -1;
	}

	public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
		for (; i < 3; i++) {
			zhang[i] += (oneTimeRest / qian[i]) * times;
			oneTimeRest %= qian[i];
		}
	}

	public static void main(String[] args) {
		int testTime = 1000;
		int zhangMax = 10;
		int colaMax = 10;
		int priceMax = 20;
		System.out.println("如果错误会打印错误数据，否则就是正确");
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int m = (int) (Math.random() * colaMax);
			int a = (int) (Math.random() * zhangMax);
			int b = (int) (Math.random() * zhangMax);
			int c = (int) (Math.random() * zhangMax);
			int x = ((int) (Math.random() * priceMax) + 1) * 10;
			int ans1 = putTimes(m, a, b, c, x);
			int ans2 = right(m, a, b, c, x);
			if (ans1 != ans2) {
				System.out.println("int m = " + m + ";");
				System.out.println("int a = " + a + ";");
				System.out.println("int b = " + b + ";");
				System.out.println("int c = " + c + ";");
				System.out.println("int x = " + x + ";");
				break;
			}
		}
		System.out.println("test end");
	}

}