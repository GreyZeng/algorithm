// 链接：https://www.nowcoder.com/questionTerminal/4d448650c0324df08c40c614226026ad
// 来源：牛客网

// 给定一个整型数组arr，再给定一个整数k，打印所有出现次数大于n/k的数，如果没有这样的数,请打印”-1“。

// 输入描述:
// 输入包含两行，第一行输入包含两个整数n和k(1≤k≤n≤10^5)
//,第二行包含n个整数，代表数组arr(1≤arri≤10^9)。


// 输出描述:
// 输出所有出现次数大于n/k的数，如果没有这样的数,请输出”-1“。
// 示例1
// 输入
// 7 7
// 1 2 3 1 2 3 4
// 输出
// 1 2 3
// 示例2
// 输入
// 4 1
// 1 1 2 3
// 输出
// -1

// 备注:
// 时间复杂度O(n),额外空间复杂度O(k)。
package nowcoder;

import java.util.*;

public class NowCoder_FindKMajor {
    public static void main(String[] args) {
        /*int[] arr = {1, 2, 3, 1, 2, 3, 4};
        int k = 7;
        System.out.println(major(arr, k));
        int[] arr2 = {1, 1, 2, 3};
        int k2 = 1;
        System.out.println(major(arr2, k2));*/
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        List<Integer> result = major(arr, k);
        for (int i = 0; i < result.size(); i++) {
            if (i != result.size() - 1) {
                System.out.print(result.get(i) + " ");
            } else {
                System.out.print(result.get(i));
            }
        }
        System.out.println();
        in.close();
    }

    public static List<Integer> major(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        if (k < 2) {
            result.add(-1);
            return result;
        }
        if (k > arr.length) {
            for (int j : arr) {
                result.add(j);
            }
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>(k);
        for (int j : arr) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                if (map.size() == k - 1) {
                    deleteOne(map);
                } else {
                    map.put(j, 1);
                }
            }
        }
        if (map.isEmpty()) {
            result.add(-1);
            return result;
        }
        HashMap<Integer, Integer> checked = check(arr, map);
        if (checked.isEmpty()) {
            result.add(-1);
            return result;
        }
        Set<Integer> keys = checked.keySet();
        for (int key : keys) {
            if (checked.get(key) > arr.length / k) {
                result.add(key);
            }
        }
        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }

    private static HashMap<Integer, Integer> check(int[] arr, HashMap<Integer, Integer> map) {
        HashMap<Integer, Integer> checked = new HashMap<>();
        for (int j : arr) {
            if (map.containsKey(j)) {
                if (checked.containsKey(j)) {
                    checked.put(j, checked.get(j) + 1);
                } else {
                    checked.put(j, 1);
                }
            }
        }
        return checked;
    }

    private static void deleteOne(HashMap<Integer, Integer> map) {
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            if (map.get(key) == 1) {
                iterator.remove();
            } else {
                map.put(key, map.get(key) - 1);
            }
        }
    }
}
