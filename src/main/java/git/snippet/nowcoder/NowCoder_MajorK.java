package git.snippet.nowcoder;

import java.util.*;

// https://www.nowcoder.com/questionTerminal/4d448650c0324df08c40c614226026ad
// 笔记： https://www.cnblogs.com/greyzeng/p/14410280.html
public class NowCoder_MajorK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        List<Integer> ans = major(arr, k);
        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int e : ans) {
                System.out.print(e + " ");
            }
        }
        in.close();
    }

    // 空间复杂度：O(K)
    // 推广到k
    public static List<Integer> major(int[] arr, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k < 2) {
            return ans;
        }
        Map<Integer, Integer> map = new HashMap<>(k - 1);
        for (int e : arr) {
            if (map.containsKey(e)) {
                map.put(e, map.get(e) + 1);
            } else {
                if (map.size() == k - 1) {
                    // 已经满了
                    LinkedList<Integer> removed = new LinkedList<>();
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        if (entry.getValue() == 1) {
                            removed.add(entry.getValue());
                        }
                        map.put(entry.getKey(), entry.getValue() - 1);
                    }
                    for (int remove : removed) {
                        map.remove(remove);
                    }
                } else {
                    map.put(e, 1);
                }
            }
        }
        int n = arr.length;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (times(arr, entry.getKey()) > n / k) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }

    public static int times(int[] arr, int m) {
        int count = 0;
        for (int v : arr) {
            if (v == m) {
                count++;
            }
        }
        return count;
    }
}
