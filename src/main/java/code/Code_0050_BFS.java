package code;

import code.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历 宽度优先遍历 1，利用队列实现 2，从源节点开始依次按照宽度进队列，然后弹出 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
 * 4，直到队列变空
 */
public class Code_0050_BFS {

    public static void bfs(Node node) {
        if (null == node) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.nexts != null && !cur.nexts.isEmpty()) {
                for (Node t : cur.nexts) {
                    if (!set.contains(t)) {
                        queue.offer(t);
                        set.add(t);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Node t3 = new Node(3);
        Node t4 = new Node(4);
        Node t5 = new Node(5);
        Node t6 = new Node(6);
        Node t7 = new Node(7);
        Node t8 = new Node(8);
        Node t9 = new Node(9);
        t3.nexts.add(t6);
        t3.nexts.add(t5);
        t4.nexts.add(t7);
        t4.nexts.add(t9);
        t5.nexts.add(t8);
        t5.nexts.add(t7);
        t6.nexts.add(t4);
        t7.nexts.add(t8);
        bfs(t3);
    }
}
