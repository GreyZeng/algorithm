package code;

/**
 * 基数排序 一般来讲，基数排序要求，样本是10进制的正整数 1. 找到最大值，这个最大值是几位的 2. 其他数不足这个位数的，用0补齐 3.
 * 准备10个桶，每个桶是队列 4. 从个位依次进桶 5. 然后依次倒出 6. 根据十位数进桶 7. 依次倒出 ....... 桶排序思想下的排序：计数排序
 * & 基数排序
 * <p>
 * 1)桶排序思想下的排序都是不基于比较的排序
 * <p>
 * 2)时间复杂度为O(N)，额外空间负载度O(M)
 * <p>
 * 3)应用范围有限，需要样本的数据状况满足桶的划分
 */
public class Code_0032_RadixSort {

    /**
     * 只针对非负数
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        if (null == arr || arr.length < 1) {
            return;
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int maxBit = getMaxBit(max);


        int[] help = new int[arr.length];
        for (int i = 1; i <= maxBit; i++) {
            // 个位，十位，百位 这样的顺序求
            int[] count = new int[10];
            for (int j = 0; j < arr.length; j++) {
                count[getBitValue(arr[j], i)]++;
            }
            for (int j = 1; j < 10; j++) {
                count[j] += count[j - 1];
            }
            for (int j = arr.length - 1; j >= 0; j--) { 
            	int d = getBitValue(arr[j],i);
                help[count[d] - 1]= arr[j];
                count[d]--;
            }
            for (int j = 0; j < arr.length; j++) {
                arr[j] = help[j];
            }
        }
    } 
    public static int getBitValue(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}

    public static int getMaxBit(int num) {
        int times = 1;
        while (num >= 10) {
            num /= 10;
            times++;
        }
        return times;
    }

    public static void main(String[] args) {
    	System.out.println(getBitValue(1034, 1));
        System.out.println(getMaxBit(3));
        System.out.println(getMaxBit(34));
        System.out.println(getMaxBit(345));
        System.out.println(getMaxBit(345));
    }

}