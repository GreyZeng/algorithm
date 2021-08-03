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
import java.util.TreeSet;

// https://www.lintcode.com/problem/top-k-frequent-words-ii/description
public class LintCode_0550_TopKFrequentWordsII {
    public class TopK {
        public class Node {
            public String value;
            public int times;

            public Node(String v, int t) {
                value = v;
                times = t;
            }
        }

        // 控制门槛，次数由少到多，次数一样的，字典序从大到小
        // 保证heap[0] 位置是最先可能被淘汰的位置
        public class MyComparator implements Comparator<Node> {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.times < o2.times) {
                    return -1;
                } else if (o1.times > o2.times) {
                    return 1;
                } else {
                    return o2.value.compareTo(o1.value);
                }
            }
        }

        // 次数由多到少，次数一样，按字典序由小到大
        public class TreeSetComp implements Comparator<Node> {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.times < o2.times) {
                    return 1;
                } else if (o1.times > o2.times) {
                    return -1;
                } else {
                    return o1.value.compareTo(o2.value);
                }
            }

        }

        private Node[] heap; // heap[0]门槛
        private int heapSize;
        // private int k;
        // node节点在heap的哪个位置
        private HashMap<Node, Integer> indexMap;
        // 通过valueMap可以找到某个字符串对应的Node内存地址
        private HashMap<String, Node> valueMap;
        private Comparator<Node> comp;
        private TreeSet<Node> set; // 存候选

        public TopK(int k) {
            heap = new Node[k];
            indexMap = new HashMap<>();
            valueMap = new HashMap<>();
            heapSize = 0;
            comp = new MyComparator();
            set = new TreeSet<>(new TreeSetComp());
        }

        public void add(String word) {
            if (heap.length == 0) {
                return;
            }
            int pre;
            Node node;
            if (valueMap.containsKey(word)) {
                node = valueMap.get(word);
                if (set.contains(node)) {
                    set.remove(node);
                }
                pre = indexMap.get(node);
                node.times++;
            } else {
                node = new Node(word, 1);
                valueMap.put(word, node);
                pre = -1;
                indexMap.put(node, pre); // -1表示还没入堆,新增节点
            }
            // 要考虑堆满的情况，indexMap和valueMap会把word先缓存下来，等到次数到达一定程度了，再把节点加入到堆中
            if (pre == -1) { // 新增节点或者淘汰后的节点
                if (heapSize == heap.length) {
                    // 堆满了，要查看当前节点能否替换堆中的第一个节点
                    if (comp.compare(heap[0], node) < 0) {
                        set.remove(heap[0]);
                        set.add(node);
                        indexMap.put(heap[0], -1);
                        heap[0] = node;
                        indexMap.put(node, 0);
                        heapify(0);
                    }
                } else {
                    set.add(node);
                    heap[heapSize] = node;
                    indexMap.put(node, heapSize);
                    heapInsert(heapSize++);
                }
            } else {
                set.add(node);
                heapify(pre);
            }

        }

        private void heapInsert(int i) {
            while (comp.compare(heap[i], heap[(i - 1) / 2]) < 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void heapify(int i) {
            int leftChild = 2 * i + 1;
            while (leftChild < heapSize) {
                Node best;
                if (leftChild + 1 < heapSize) {
                    Node left = heap[leftChild];
                    Node right = heap[leftChild + 1];
                    if (comp.compare(left, right) < 0) {
                        best = left;
                    } else {
                        best = right;
                    }
                } else {
                    best = heap[leftChild];
                }
                if (comp.compare(heap[i], best) < 0) {
                    break;
                }
                int bestIndex = best == heap[leftChild] ? leftChild : leftChild + 1;
                swap(bestIndex, i);
                i = bestIndex;
                leftChild = 2 * i + 1;
            }
        }

        private void swap(int i, int j) {
            indexMap.put(heap[i], j);
            indexMap.put(heap[j], i);
            Node t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;
        }

        // heap维持好堆的状态，直接遍历前k个即可
        public List<String> topk() {
            List<String> ans = new ArrayList<>();
            for (Node n : set) {
                ans.add(n.value);
            }
            return ans;
        }
    }

    

}
