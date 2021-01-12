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
        private class Node {
            public String str;
            public int times;

            public Node(String str, int times) {
                this.str = str;
                this.times = times;
            }
        }

        private Node[] heap;
        private int heapSize;
        private int k;
        private HashMap<Node, Integer> indexMap;
        private HashMap<String, Node> valueMap; // 来的String在valueMap里面有没有

        public TopK(int k) {
            this.k = k;
            if (!fibbiden()) {
                heap = new Node[k];
                indexMap = new HashMap<>();
                valueMap = new HashMap<>();
                heapSize = 0;
            }
        }

        private boolean fibbiden() {
            return k <= 0;
        }

        public void add(String word) {
            if (fibbiden()) {
                return;
            }
            int pre;
            Node node;
            if (valueMap.containsKey(word)) {
                node = valueMap.get(word);
                node.times++;
                pre = indexMap.get(node);
            } else {
                node = new Node(word, 1);
                valueMap.put(word, node);
                pre = -1;
                indexMap.put(node, pre); // -1表示还没入堆,新增节点
            }
            // 要考虑堆满的情况，indexMap和valueMap会把word先缓存下来，等到次数到达一定程度了，再把节点加入到堆中
            if (pre == -1) { // 新增节点
                if (heapSize == heap.length) {
                    // 堆满了，要查看当前节点能否替换堆中的第一个节点
                    if (node.times > heap[0].times
                            || 
                            (node.times == heap[0].times && node.str.compareTo(heap[0].str) < 0)) {
                        indexMap.put(heap[0], -1);
                        heap[0] = node;
                        indexMap.put(node, 0);
                        heapify(0);
                    }
                } else {
                    heap[heapSize] = node;
                    indexMap.put(node, heapSize);
                    heapInsert(heapSize++);
                }
            } else {
                heapify(pre);
            }

        }

        private void heapInsert(int i) {
            while (heap[i].times < heap[(i - 1) / 2].times
                    || (heap[i].times == heap[(i - 1) / 2].times && heap[i].str.compareTo(heap[(i - 1) / 2].str) > 0)) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void heapify(int i) {
            int leftChild = 2 * i + 1;
            while (leftChild < heapSize) {
                Node best = null;
                if (leftChild + 1 < heapSize) {
                    Node left = heap[leftChild];
                    Node right = heap[leftChild + 1];
                    if (left.times > right.times) {
                        best = right;
                    } else if (right.times > left.times) {
                        best = left;
                    } else {
                        if (right.str.compareTo(left.str) < 0) {
                            best = left;
                        } else {
                            best = right;
                        }
                    }
                } else {
                    best = heap[leftChild];
                }

                if (heap[i].times < best.times
                        || (heap[i].times == best.times && heap[i].str.compareTo(best.str) > 0)) {
                    break;
                }

                int bestIndex = best == heap[leftChild] ? leftChild : leftChild + 1;
                swap(i, bestIndex);
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
            if (fibbiden()) {
                return new ArrayList<>();
            }
            List<String> ans = new ArrayList<>();
            for (int i = heapSize - 1; i >= 0; i--) {
                ans.add(heap[i].str);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int k = 3;
        TopK topK = new TopK(k);
         List<String> topk = new ArrayList<>();
        topK.add("yes");
        topK.add("lint");
        topK.add("code");
        topK.add("yes");
        topK.add("code");
        topK.add("baby");
        topK.add("you");
        topK.add("baby");
        topK.add("chrome");
        topk = topK.topk();
        for (String s : topk) {
            System.out.println(s);
        }
        System.out.println("=--");
        topK.add("safari");
        topK.add("lint");
        topK.add("code");
        topK.add("body");
        topK.add("lint");
        topK.add("code");
        topk =  topK.topk();
        for (String s : topk) {
            System.out.println(s);
        }

    }

}
