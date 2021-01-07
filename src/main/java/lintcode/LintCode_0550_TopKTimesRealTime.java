/*550. 最常使用的K个单词II
        中文English
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
// follow up:add方法，复杂度O(log K);
//        top方法，复杂度O(K)
package lintcode;

import java.util.List;

// https://www.lintcode.com/problem/top-k-frequent-words-ii/description
public class LintCode_0550_TopKTimesRealTime {

}

// TODO
class TopK {

    public TopK(int k) {

    }

    public void add(String word) {

    }

    public List<String> topk() {
        return null;
    }
}