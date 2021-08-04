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
import java.util.List;
import java.util.TreeSet;

// https://www.lintcode.com/problem/top-k-frequent-words-ii/description
public class LintCode_0550_TopKFrequentWordsII {
    public class TopK {
        private TreeSet<Word> topK;
        public TopK(int k) {
            topK = new TreeSet<>(new TopKComparator());
        }

        public void add(String str) {

        }

        public List<String> topk() {
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
                return o1.times == o2.times ? o2.value.compareTo(o2.value) : (o1.times - o2.times);
            }
        }


    }
}
