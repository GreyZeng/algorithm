/*设计一个迭代器来实现摊平二维向量的功能

        样例
        例1:

        输入:[[1,2],[3],[4,5,6]]
        输出:[1,2,3,4,5,6]
        例2:

        输入:[[7,9],[5]]
        输出:[7,9,5]*/
package lintcode.medium;

import java.util.Iterator;
import java.util.List;


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
			return current<=count-1;
		}

		@Override
		public void remove() {
			int[] c = new int[--count];
			for (int i = 0; i < count;i++) {
				c[i] =data[i]; 
			}
			data = c;
		}
	}

}
