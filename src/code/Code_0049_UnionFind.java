package code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 并查集
public class Code_0049_UnionFind {
    
    public static class UnionFind<V> {
        private HashMap<V, Node<V>> nodeMap;
        private HashMap<Node<V>, Node<V>> parentMap;
        private HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodeMap = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : values) {
                Node<V> n = new Node<>(v);
                nodeMap.put(v, n);
                parentMap.put(n, n);
                sizeMap.put(n, 1);
            }
        }

        // v1，v2必须是已经存在nodeMap中的元素
        public void union(V v1, V v2) {
            if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
                return;
            }
            if (!isSameSet(v1, v2)) {
                Node<V> v1Parent = nodeMap.get(v1);
                Node<V> v2Parent = nodeMap.get(v2);
                Node<V> small = sizeMap.get(v1Parent) > sizeMap.get(v2Parent) ? v2Parent : v1Parent;
                Node<V> large = small == v1Parent ? v2Parent : v1Parent;
                sizeMap.put(large, sizeMap.get(large) + sizeMap.get(small));
                parentMap.put(small, large);
                parentMap.remove(small);
            }
        }

        private Node<V> findFather(Node<V> node) {
            Queue<Node<V>> queue = new LinkedList<>();
            while (node != parentMap.get(node)) {
                queue.offer(node);
                node = parentMap.get(node);
            }
            while (!queue.isEmpty()) {
                parentMap.put(queue.poll(), node);
            }
            return node;
        }

        public boolean isSameSet(V v1, V v2) {
            if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
                return false;
            }
            return findFather(nodeMap.get(v1)) == findFather(nodeMap.get(v2));
        }


        private static class Node<V> {
            private V value;

            public Node(V value) {
                this.value = value;
            }
        }
    }

}
