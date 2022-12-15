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
package 练习题.lintcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// https://www.lintcode.com/problem/top-k-frequent-words-ii/description
// 笔记：https://www.cnblogs.com/greyzeng/p/16125150.html
public class LintCode_0550_TopKFrequentWordsII {
    // 该方法是正式方法，使用加强堆
    public static class TopK {
        private TreeSet<Word> topK;
        private Heap heap;
        private Map<String, Word> map;
        private int k;

        public TopK(int k) {
            this.k = k;
            topK = new TreeSet<>((o1, o2) -> {
                // 次数大的排前面，次数一样字典序在小的排前面
                return o1.times == o2.times ? o1.value.compareTo(o2.value) : (o2.times - o1.times);
            });
            heap = new Heap(k, (o1, o2) -> {
                // 设置堆门槛，堆顶元素最先被淘汰
                return o1.times == o2.times ? o2.value.compareTo(o1.value) : (o1.times - o2.times);
            });
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
                    return comparator.compare(words[0], word) < 0;
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
                    Word weakest = leftChildIndex + 1 < size ? (comparator.compare(words[leftChildIndex], words[leftChildIndex + 1]) < 0 ? words[leftChildIndex] : words[leftChildIndex + 1]) : words[leftChildIndex];
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

    // 该方法是对数器，也可以通过lintcode测评
    public static class TopK2 {
        private TreeSet<Word> topK;
        private Map<String, Word> indexMap;
        private int k;


        public TopK2(int k) {
            this.k = k;
            topK = new TreeSet<>((o1, o2) -> {
                // 次数大的排前面，次数一样字典序在小的排前面
                return o1.times == o2.times ? o1.value.compareTo(o2.value) : (o2.times - o1.times);
            });
            indexMap = new HashMap<>();
        }

        private class Word {
            public String value;
            public int times;

            public Word(String v, int t) {
                value = v;
                times = t;
            }
        }

        public void add(String str) {
            if (k == 0) {
                return;
            }
            Word word = indexMap.get(str);
            if (word == null) {
                // 新增元素
                word = new Word(str, 1);
                topK.add(word);
            } else {
                topK.remove(word);
                word.times++;
                topK.add(word);
            }
            indexMap.put(str, word);
        }

        public List<String> topk() {
            if (k == 0) {
                return new ArrayList<>();
            }
            List<String> result = new ArrayList<>();
            int t = k;
            for (Word word : topK) {
                if (t == 0) {
                    break;
                }
                result.add(word.value);
                t--;
            }
            return result;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            int k = (int) (Math.random() * arrLen);

            TopK topK = new TopK(k);
            TopK2 topK2 = new TopK2(k);
            // 随机调用add的次数
            int addTimes = Math.min(arr.length, k + (int) (Math.random() * k));
            for (int j = 0; j < addTimes; j++) {
                topK.add(arr[j]);
                topK2.add(arr[j]);
            }
            List<String> res1 = topK.topk();
            List<String> res2 = topK2.topk();
            if (!equalList(res1, res2)) {
                System.out.println("oops!!");
                break;
            }
        }
        System.out.println("finish!");

    }

    private static boolean equalList(List<String> res1, List<String> res2) {
        if (null == res1) {
            return res2 == null;
        }
        if (res1.isEmpty()) {
            return res2 != null && res2.isEmpty();
        }
        if (res1.size() != res2.size()) {
            return false;
        }
        for (int i = 0; i < res1.size(); i++) {
            if (!res1.get(i).equals(res2.get(i))) {
                return false;
            }
        }
        return true;

    }
}
