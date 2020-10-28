/*设计一个迭代器来实现摊平二维向量的功能

        样例
        例1:

        输入:[[1,2],[3],[4,5,6]]
        输出:[1,2,3,4,5,6]
        例2:

        输入:[[7,9],[5]]
        输出:[7,9,5]*/
package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO
// leetcode加锁
// lintcode: https://www.lintcode.com/problem/flatten-2d-vector/description
public class LeetCode_0251_Flatten2DVector {

    public class Vector2D implements Iterator<Integer> {

        public Vector2D(List<List<Integer>> vec2d) {

        }

        @Override
        public Integer next() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public void remove() {

        }
    }

}
