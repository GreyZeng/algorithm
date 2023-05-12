package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

// [编程题]拼接所有的字符串产生字典序最小的字符串
// https://www.nowcoder.com/questionTerminal/f1f6a1a1b6f6409b944f869dc8fd3381
// 笔记：https://www.cnblogs.com/greyzeng/p/16704842.html
public class NowCoder_LowestString {

    public static String minString(String[] strs) {
        Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    // 暴力解
    public static String minString2(String[] strs) {
        HashSet<Integer> used = new HashSet<>();
        ArrayList<String> all = new ArrayList<>();
        String path = "";
        process(strs, used, path, all);
        String min = all.get(0);
        for (String s : all) {
            if (min.compareTo(s) > 0) {
                min = s;
            }
        }
        return min;
    }

    // 已经用过的字符串在used中登记了
    // 已经用过的字符串拼接的结果是path
    // 所有拼接的方式存在all里面
    public static void process(String[] strs, HashSet<Integer> used, String path,
                               ArrayList<String> all) {
        if (used.size() == strs.length) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    process(strs, used, path + strs[i], all);
                    used.remove(i);
                }
            }
        }
    }

    public static void main(String[] args) {

        int arrLen = 6;
        int strLen = 5;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            String[] arr1 = copyStringArray(arr);
            String[] arr2 = copyStringArray(arr);
            String ans1 = minString(arr1);
            String ans2 = minString2(arr2);

            if (!ans1.equals(ans2)) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s + ",");
        }
        System.out.println();

    }

    private static String[] copyStringArray(String[] arr1) {
        if (null == arr1) {
            return null;
        }
        String[] arr2 = new String[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = String.valueOf(arr1[i]);
        }
        return arr2;
    }

    private static String[] generateRandomStringArray(int arrLen, int strLen) {
        int len = (int) (Math.random() * (arrLen + 1));
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) {
            arr[i] = generateString(strLen);
        }
        return arr;
    }

    private static String generateString(int strLen) {
        int len = (int) (Math.random() * (strLen)) + 1;
        char[] arr = new char[len];
        for (int i = 0; i < arr.length; i++) {
            int v = 97 + (int) (Math.random() * 26);
            arr[i] = (char) v;
        }
        return String.valueOf(arr);
    }

}
