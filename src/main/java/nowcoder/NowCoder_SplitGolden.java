package nowcoder;

import java.util.*;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。 比如长度为20的金条， 不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50; 一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30; 一共花费90铜板。 输入一个数组，返回分割的最小代价。
 * <p>
 * 注：堆和排序是解决贪心问题的最常用的两种方案
 */
// https://www.nowcoder.com/questionTerminal/418d2fcdf7f24d6f8f4202e23951c0da
public class NowCoder_SplitGolden {
	public static int lessMoney(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        if (arr.length == 2) {
        	return arr[0] + arr[1];
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
        	queue.add(i);
        }
        int cost = 0;
        while (queue.size() > 1) {
        	int i = queue.poll();
        	int j = queue.poll();
        	cost +=(i+j);
        	queue.offer(i+j);
        }
        return cost;
    }
    // 暴力递归版本
    public static int lessMoney0(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return process0(arr, 0);
    }

    private static int process0(int[] arr, int s) {
        if (arr.length == 1) {
            return s;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    min = Math.min(process0(copyExcept(arr, i, j), s + (arr[i] + arr[j])), min);
                }
            }
            return min;
        }
    }


    private static int[] copyExcept(int[] arr, int i1, int i2) {
        int m = 0;
        int[] nArr = new int[arr.length - 1];
        int t = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != i1 && j != i2) {
                nArr[m++] = arr[j];
            } else {
                t += arr[j];
            }
        }
        nArr[arr.length - 2] = t;
        return nArr;
    }
    
    public static int[] generateRandomArr(int maxSize, int maxValue) {
    	int size  = (int)(Math.random() * maxSize) + 1;
    	int[] arr = new int[size];
    	for (int i = 0; i < size;i++) {
    		arr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * (maxValue + 1));
    	}
    	return arr;
    }
    public static void main(String[] args) {
    	int times = 50000;
    	int maxValue = 9;
    	int maxSize = 7;
    	for (int i = 0; i < times;i++) {
    		int[] arr = generateRandomArr(maxSize, maxValue);
    		if (lessMoney(arr) != lessMoney0(arr)) {
    			System.out.println("Ops!");
    		}
    	}
    	System.out.println("Nice!");
    }
}
