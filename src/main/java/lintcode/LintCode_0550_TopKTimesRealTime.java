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
// follow up:add方法，复杂度O(log K);
//        top方法，复杂度O(K)
package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// TODO
// https://www.lintcode.com/problem/top-k-frequent-words-ii/description
public class LintCode_0550_TopKTimesRealTime {
    public static class TopK {
        public class Node {
            public String str;
            public int times;

            public Node(String str, int times) {
                this.str = str;
                this.times = times;
            }
        }

        private Node[] heap;
        private int heapSize;
        private HashMap<Node, Integer> indexMap;
        private HashMap<String, Node> valueMap; // 来的String在valueMap里面有没有


        public TopK(int k) {
            heap = new Node[k];
            indexMap = new HashMap<>();
            valueMap = new HashMap<>();
        }

        public void add(String word) {

        }

        public void heapInsert(int i) {

        }

        public void heapify(int i) {

        }

        public void swap(Node[] nodes, int i, int j) {

        }

        // heap维持好堆的状态，直接遍历前k个即可
        public List<String> topk() {
            List<String> ans = new ArrayList<>();
            for (Node node : heap) {
                ans.add(node.str);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int k = 2;
        TopK topK = new TopK(k);
        topK.add("lint");
        topK.add("code");
        topK.add("lint");
        topK.add("lint");
        topK.add("lint");
        topK.add("lint");
        topK.add("ssss");
        topK.add("ssss");
        topK.add("sss");
        topK.add("ssss");
        List<String> topk = topK.topk();
        for (String s : topk) {
            System.out.println(s);
        }
    }

}


