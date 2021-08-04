/*550. 最常使用的K个单词II
        在实时数据流中找到最常使用的k个单词.
        实现TopK类中的三个方法:
        TopK(k), 构造方法
        add(word), 增加一个新单词
        topk(), 得到当前最常使用的k个单词.

        样例
        样例 1:

        输入：
        TopK(2)
        add("lint")
        add("code")
        add("code")
        topk()
        输出：["code", "lint"]
        解释：
        "code" 出现两次并且 "lint" 出现一次， 它们是出现最频繁的两个单词。
        样例 2:

        输入：
        TopK(1)
        add("aa")
        add("ab")
        topk()
        输出：["aa"]
        解释：
        "aa" 和 "ab" 出现 , 但是aa的字典序小于ab。
        注意事项
        如果两个单词有相同的使用频率, 按字典序排名.*/
package lintcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// https://www.lintcode.com/problem/top-k-frequent-words-ii/description
public class LintCode_0550_TopKFrequentWordsII {

	public static void main(String[] args) {
		TopK topk = new TopK(2);
		topk.add("lint");
		topk.add("code");
		topk.add("code");
		List<String> ans = topk.topk();
		System.out.println(ans);

	}

	public static class TopK {
		private TreeSet<Word> topK;
		private Heap heap;
		private Map<String, Word> map;
		private int k;

		public TopK(int k) {
			this.k = k;
			topK = new TreeSet<>(new TopKComparator());
			heap = new Heap(k, new ThresholdComparator());
			map = new HashMap<>();
		}

		public void add(String str) {
			if (k == 0) {
				return;
			}
			Word word = map.get(str);
			if (word == null) {
				// 新增元素
				word = new Word(str, 1);
				// 是否到达门槛可以替换堆中元素
				if (heap.isReachThreshold(word)) {
					if (heap.isFull()) {
						Word toBeRemoved = heap.poll();
						topK.remove(toBeRemoved);
					}
					heap.add(word);
					topK.add(word);
				}
			} else {
				if (heap.contains(word)) {
					topK.remove(word);
					word.times++;
					topK.add(word);
					heap.resign(word);
				} else {
					word.times++;
					if (heap.isReachThreshold(word)) {
						if (heap.isFull()) {
							Word toBeRemoved = heap.poll();
							topK.remove(toBeRemoved);
						}
						heap.add(word);
						topK.add(word);
					}
				}
			}
			map.put(str, word);
		}

		public List<String> topk() {
			if (k == 0) {
				return new ArrayList<>();
			}
			List<String> result = new ArrayList<>();
			for (Word word : topK) {
				result.add(word.value);
			}
			return result;
		}

		private class Word {
			public String value;
			public int times;

			public Word(String v, int t) {
				value = v;
				times = t;
			}
		}

		private class TopKComparator implements Comparator<Word> {
			@Override
			public int compare(Word o1, Word o2) {
				// 次数大的排前面，次数一样字典序在小的排前面
				return o1.times == o2.times ? o1.value.compareTo(o2.value) : (o2.times - o1.times);
			}
		}

		private class ThresholdComparator implements Comparator<Word> {

			@Override
			public int compare(Word o1, Word o2) {
				// 设置堆门槛，堆顶元素最先被淘汰
				return o1.times == o2.times ? o2.value.compareTo(o1.value) : (o1.times - o2.times);
			}
		}

		private class Heap {
			private Word[] words;
			private Comparator<Word> comparator;
			private Map<Word, Integer> indexMap;

			public Heap(int k, Comparator<Word> comparator) {
				words = new Word[k];
				indexMap = new HashMap<>();
				this.comparator = comparator;
			}

			public boolean isEmpty() {
				return indexMap.isEmpty();
			}

			public boolean isFull() {
				return indexMap.size() == words.length;
			}

			public boolean isReachThreshold(Word word) {
				if (isEmpty() || indexMap.size() < words.length) {
					return true;
				} else {
					if (comparator.compare(words[0], word) < 0) {
						return true;
					}
					return false;
				}
			}

			public void add(Word word) {
				int size = indexMap.size();
				words[size] = word;
				indexMap.put(word, size);
				heapInsert(size);

			}

			private void heapify(int i) {
				int size = indexMap.size();
				int leftChildIndex = 2 * i + 1;
				while (leftChildIndex < size) {
					Word weakest = leftChildIndex + 1 < size
							? (comparator.compare(words[leftChildIndex], words[leftChildIndex + 1]) < 0
									? words[leftChildIndex]
									: words[leftChildIndex + 1])
							: words[leftChildIndex];
					if (comparator.compare(words[i], weakest) < 0) {
						break;
					}
					int weakestIndex = weakest == words[leftChildIndex] ? leftChildIndex : leftChildIndex + 1;
					swap(weakestIndex, i);
					i = weakestIndex;
					leftChildIndex = 2 * i + 1;
				}
			}

			public void resign(Word word) {
				int i = indexMap.get(word);
				heapify(i);
				heapInsert(i);
			}

			private void heapInsert(int i) {
				while (comparator.compare(words[i], words[(i - 1) / 2]) < 0) {
					swap(i, (i - 1) / 2);
					i = (i - 1) / 2;
				}
			}

			public boolean contains(Word word) {
				return indexMap.containsKey(word);
			}

			public Word poll() {
				Word result = words[0];
				swap(0, indexMap.size() - 1);
				indexMap.remove(result);
				heapify(0);
				return result;
			}

			private void swap(int i, int j) {
				if (i != j) {
					indexMap.put(words[i], j);
					indexMap.put(words[j], i);
					Word tmp = words[i];
					words[i] = words[j];
					words[j] = tmp;
				}
			}
		}
	}
}
