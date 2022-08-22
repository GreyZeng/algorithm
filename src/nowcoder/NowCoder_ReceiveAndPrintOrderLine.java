/*链接：https://www.nowcoder.com/questionTerminal/155238870f834406ba6971dec4825ab6
        来源：牛客网

        已知一个消息流会不断地吐出整数 1∼N，但不一定按照顺序吐出。如果上次打印的数为i，那么当i+1出现时，请打印i+1及其之后接收过的并且连续的所有数，直到 1∼N全部接收并打印完，请设计这种接收并打印的结构
        [要求]
        消息流最终会吐出全部的 1∼N，当然最终也会打印完所有的 1∼N，要求接收和打印 1∼N的整个过程，时间复杂度为O(N) 。

        输入描述:
        第一行一个整数N。

        接下来一行有N个整数。保证输入是一个1到N的排列

        输出描述:
        输出N行，每行两个数。

        为了检验输出的正确性，请在输出当前打印的数字之后输出此时最后一个加入的元素。

        具体看输入输出样例
        示例1
        输入
        9
        2 1 4 5 7 3 9 8 6
        输出
        1 1
        2 1
        3 3
        4 3
        5 3
        6 6
        7 6
        8 6
        9 6
        说明
        消息流吐出2，一种结构接收而不打印2，因为1还没出现。
        消息流吐出1，一种结构接收1，并且打印：1, 2。
        消息流吐出4，一种结构接收而不打印4，因为3还没出现。
        消息流吐出5，一种结构接收而不打印5，因为3还没出现。
        消息流吐出7，一种结构接收而不打印7，因为3还没出现。
        消息流吐出3，一种结构接收3，并且打印：3, 4, 5。
        消息流吐出9，一种结构接收而不打印9，因为6还没出现。
        消息流吐出8，一种结构接收而不打印8，因为6还没出现。
        消息流吐出6，一种结构接收6，并且打印：6, 7, 8, 9。

        备注:
        1⩽N⩽10^5

        保证输入的是一个1∼N的排列*/
package nowcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//tips:
// hashmap head表 和 tail表 和链表配合
public class NowCoder_ReceiveAndPrintOrderLine {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        MQ mq = new MQ();
        for (int i = 0; i < n; i++) {
            mq.receive(in.nextInt());
        }
        in.close();
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    public static class MQ {
        private Map<Integer, Node> head;
        private Map<Integer, Node> tail;
        private int wait;

        public MQ() {
            wait = 1;
            head = new HashMap<>();
            tail = new HashMap<>();
        }

        public void receive(int num) {
            Node cur = new Node(num);
            head.put(num, cur);
            tail.put(num, cur);
            if (tail.containsKey(num - 1)) {
                tail.get(num - 1).next = cur;
                tail.remove(num - 1);
                head.remove(num);
            }
            if (head.containsKey(num + 1)) {
                cur.next = head.get(num + 1);
                head.remove(num + 1);
                tail.remove(num);
            }
            if (num == wait) {
                print(num);
            }
        }

        public void print(int trigger) {
            Node start = head.get(wait);
            head.remove(wait);
            while (start != null) {
                System.out.println(start.value + " " + trigger);
                start = start.next;
                wait++;
            }
            tail.remove(wait - 1);
        }
    }
}
