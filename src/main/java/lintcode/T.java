package lintcode;

import java.util.Comparator;
import java.util.PriorityQueue; 

public class T {
	public static void main(String[] args) {
//		int k = 3;
//		TopK topK = new TopK(k);
//		List<String> topk = new ArrayList<>();
//		topK.add("yes");
//		topK.add("lint");
//		topK.add("code");
//		// topK.add("yes");
////        topK.add("code");
////        topK.add("baby");
////        topK.add("you");
////        topK.add("baby");
////        topK.add("chrome");
//		topk = topK.topk();
//		for (String s : topk) {
//			System.out.println(s);
//		}
//		System.out.println("=--");
//		topK.add("safari");
//		topK.add("lint");
//		topK.add("code");
//		topK.add("body");
//		topK.add("lint");
//		topK.add("code");
//		topk = topK.topk();
//		for (String s : topk) {
//			System.out.println(s);
//		}
		Node t1 = new Node("ab",2);

		Node t2 = new Node("aa",1);

		Node t3 = new Node("kbc",3);

		Node t4 = new Node("abc",3);

		Node t5 = new Node("ax",3);
		// abc  ax kbc ab aa
		PriorityQueue<Node> queue = new PriorityQueue<>(new MyComparator());
		queue.add(t1);
		queue.add(t2);
		queue.add(t3);
		queue.add(t4);
		queue.add(t5);
		while (!queue.isEmpty()) {
			System.out.println(queue.poll().value);
		}
	}
	public static class Node {
		public String value;
		public int times;
		public Node(String v, int t) {
			value = v;
			times = t;
		}
	}
	public static class MyComparator implements Comparator<Node>{
		@Override
		public int compare(Node o1, Node o2) {
			
			if (o1.times != o2.times) {
				if (o1.times > o2.times) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return o1.value.compareTo(o2.value);
			}

		}
	}
}
