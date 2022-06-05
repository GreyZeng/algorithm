package leetcode.hard;

import java.util.*;

//给定一个由不同正整数的组成的非空数组 A，考虑下面的图：
//有A.length个节点，按从A[0]到A[A.length - 1]标记；
//只有当 A[i] 和 A[j] 共用一个大于 1 的公因数时，A[i]和 A[j] 之间才有一条边。
//返回图中最大连通组件的大小。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/largest-component-size-by-common-factor
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0952_LargestComponentSizeByCommonFactor {
    public static int largestComponentSize(int[] arr) {
        UnionFind uf = new UnionFind(arr.length);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // 以下是找arr[i]有哪些因数的相对比较快的做法。
            int num = (int) Math.sqrt(arr[i]);
            for (int j = 1; j <= num; j++) {
                if (arr[i] % j == 0) {
                    if (j != 1) {
                        if (!map.containsKey(j)) {
                            map.put(j, i);
                        } else {
                            // 找到有共同因数的元素了，可以合并了
                            uf.union(map.get(j), i);
                        }
                    }
                    int other = arr[i] / j;
                    if (other != 1) {
                        if (!map.containsKey(other)) {
                            map.put(other, i);
                        } else {
                            // 找到有共同因数的元素了，可以合并了
                            uf.union(map.get(other), i);
                        }
                    }
                }
            }
        }
        return uf.maxSize();
    }

    // 并查集
    public static class UnionFind {
        private int[] parents;
        private int[] size;
        private int[] help;

        public UnionFind(int len) {
            parents = new int[len];
            size = new int[len];
            help = new int[len];
            for (int i = 0; i < len; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int maxSize() {
            int ans = 0;
            for (int size : size) {
                ans = Math.max(ans, size);
            }
            return ans;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }
            for (int j = 0; j < hi; j++) {
                parents[help[j]] = i;
            }
            return i;
        }

        // i 和 j分别是两个数的位置，不是值
        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                int s1 = size[f1];
                int s2 = size[f2];
                if (s1 > s2) {
                    parents[f2] = f1;
                    size[f1] += s2;
                } else {
                    parents[f1] = f2;
                    size[f2] += s1;
                }
            }
        }
    }
}
