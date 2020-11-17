//链接：https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372
//        来源：牛客网
//
//        给定一个没有重复值的整形数组arr，初始时认为arr中每一个数各自都是一个单独的集合。请设计一种叫UnionFind的结构，并提供以下两个操作。
//        boolean isSameSet(int a, int b): 查询a和b这两个数是否属于一个集合
//        void union(int a, int b): 把a所在的集合与b所在的集合合并在一起，原本两个集合各自的元素以后都算作同一个集合
//        [要求]
//        如果调用isSameSet和union的总次数逼近或超过O(N)，请做到单次调用isSameSet或union方法的平均时间复杂度为O(1)
//
//        输入描述:
//        第一行两个整数N, M。分别表示数组大小、操作次数
//        接下来M行，每行有一个整数opt
//        若opt = 1，后面有两个数x, y，表示查询(x, y)这两个数是否属于同一个集合
//        若opt = 2，后面有两个数x, y，表示把x, y所在的集合合并在一起
//
//
//        输出描述:
//        对于每个opt = 1的操作，若为真则输出"Yes"，否则输出"No"
//        示例1
//        输入
//        4 5
//        1 1 2
//        2 2 3
//        2 1 3
//        1 1 1
//        1 2 3
//        输出
//        No
//        Yes
//        Yes
//        说明
//        每次2操作后的集合为
//        ({1}, {2}, {3}, {4})
//        ({1}, {2, 3}, {4})
//        ({1, 2, 3}, {4})

//备注:
//        1 <= N, M <= 10^6
//
//        保证1 <= x, y <= N
package nowcoder;

import java.util.*;

// 并查集
// 这题要特别注意备注说的情况
//备注:
//        1 <= N, M <= 10^6
//
//        保证1 <= x, y <= N
// 因为x,y [1,N] 所以，准备一个N长度的数组可以表示所有x，y的代表节点，省去了Map
// https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372
public class NowCoder_UnionFind {

    static class UnionFind {
        private int record[];// record[i] 表示i的所属的代表点是i

        UnionFind(int n) {
            record = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                record[i] = i;
            }
        }

        public int find(int x) {
            int fx = x;
            while (fx != record[fx]) {
                fx = record[fx];
            }
            while (x != fx) {
                int temp = record[x];
                record[x] = fx;
                x = temp;
            }
            return fx;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                record[fx] = fy;
            }
        }

        public boolean isSameSet(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            return fx == fy;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int count = in.nextInt();
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < count; i++) {
            int opt = in.nextInt();
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            if (opt == 1) {
                System.out.println(unionFind.isSameSet(v1, v2) ? "Yes" : "No");
            } else {
                unionFind.union(v1, v2);
            }
        }
    }
}
