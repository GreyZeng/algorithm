package snippet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code_0029_CustomHeap {

    // 自己手写堆
    public static class HeapGreater<T> {

        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap; // 元素在堆中的位置
        private int heapSize; // 和heap配合使用
        private Comparator<? super T> comp;

        public HeapGreater(Comparator<T> c) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            comp = c;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T obj) {
            return indexMap.containsKey(obj);
        }

        public T peek() {
            return heap.get(0);
        }

        public void push(T obj) {
            heap.add(obj);
            indexMap.put(obj, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(ans);
            heap.remove(--heapSize);
            heapify(0);
            return ans;
        }

        public void remove(T obj) {
            T replace = heap.get(heapSize - 1);
            int index = indexMap.get(obj);
            indexMap.remove(obj);
            heap.remove(--heapSize);
            if (obj != replace) { // obj == replace表示删掉的是最后一个位置的数据，此时不需要进行resign操作
                heap.set(index, replace);
                indexMap.put(replace, index);
                resign(replace);
            }
        }

        public void resign(T obj) {
            heapInsert(indexMap.get(obj));
            heapify(indexMap.get(obj));
        }

        // 请返回堆上的所有元素
        public List<T> getAllElements() {
            List<T> ans = new ArrayList<>();
            for (T c : heap) {
                ans.add(c);
            }
            return ans;
        }

        private void heapInsert(int index) {
            while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
                best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
                if (best == index) {
                    break;
                }
                swap(best, index);
                index = best;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o2, i);
            indexMap.put(o1, j);
        }

    }

    public static void main(String[] args) {
        HeapGreater<Integer> heap = new HeapGreater<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        heap.push(new Integer(3));
        heap.push(new Integer(4));
        heap.push(new Integer(2));
        heap.push(new Integer(1));
        heap.push(new Integer(7));
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
    }

}
