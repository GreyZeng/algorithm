package snippet;

import java.util.HashSet;
import java.util.Stack;

import snippet.graph.Node;

/**
 * 深度优先遍历 1，利用栈实现 2，从源节点开始把节点按照深度放入栈，然后弹出 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈 4，直到栈变空
 */
public class Code_0051_DFS {
	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		System.out.println(node.value);
		stack.push(node);
		set.add(node);
		while (!stack.isEmpty()) {
			Node t = stack.pop();
			for (Node n : t.nexts) {
				if (!set.contains(n)) {
					set.add(n);
					stack.push(t);
					stack.push(n);
					System.out.println(n.value);
					break;
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
		dfs(t3);
	}
}
