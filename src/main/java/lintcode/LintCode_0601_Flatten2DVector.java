package lintcode;

import java.util.Iterator;
import java.util.List;

// 给定一个二维数组，实现二维数组的迭代器，包含hasNext()和next()两个迭代器常见方法。
// Leetcode题目 : https://leetcode.com/problems/flatten-2d-vector/
// leetcode加锁251
// lintcode: https://www.lintcode.com/problem/flatten-2d-vector/description
public class LintCode_0601_Flatten2DVector {

    public class Vector2D implements Iterator<Integer> {
        private int[] data;
        private int count;
        private int current;

        public Vector2D(List<List<Integer>> vec2d) {

            for (List<Integer> list : vec2d) {
                count += list.size();
            }
            data = new int[count];
            int i = 0;
            for (List<Integer> list : vec2d) {
                for (int c : list) {
                    data[i++] = c;
                }
            }
        }

        @Override
        public Integer next() {
            return data[current++];
        }

        @Override
        public boolean hasNext() {
            return current <= count - 1;
        }

        @Override
        public void remove() {
            int[] c = new int[--count];
            for (int i = 0; i < count; i++) {
                c[i] = data[i];
            }
            data = c;
        }
    }

}
