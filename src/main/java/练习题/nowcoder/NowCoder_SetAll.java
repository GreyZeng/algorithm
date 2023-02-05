// 链接：https://www.nowcoder.com/questionTerminal/7c4559f138e74ceb9ba57d76fd169967
// 来源：牛客网
//
// 哈希表常见的三个操作时put、get和containsKey，而且这三个操作的时间复杂度为O(1)。现在想加一个setAll功能，就是把所有记录value都设成统一的值。请设计并实现这种有setAll功能的哈希表，并且put、get、containsKey和setAll四个操作的时间复杂度都为O(1)。
// [友情提示]: C++选手若有需要可以使用unordered_map替换map来将复杂度从O(log n)降为O(1)
//
// 输入描述:
// 第一行一个整数N表示操作数。
// 接下来N行，每行第一个数字opt代表操作类型
// 若opt=1，接下来有两个整数x, y表示设置key=x对应的value=y
// 若opt=2，接下来一个整数x，表示查询key=x对应的value，若key=x不存在输出-1
// 若opt=3，接下来一个整数x，表示把加入过的所有的key对应的value都设置为x
//
//
// 输出描述:
// 对于每个操作2，输出一个整数表示答案
// 示例1
// 输入
// 6
// 1 1 2
// 2 1
// 2 2
// 3 4
// 2 1
// 2 2
// 输出
// 2
// -1
// 4
// -1

package 练习题.nowcoder;

import java.util.HashMap;
import java.util.Scanner;

public class NowCoder_SetAll {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < N; i++) {
            int op = in.nextInt();
            if (op == 1) {
                int key = in.nextInt();
                int value = in.nextInt();
                map.put(key, value);
            } else if (op == 2) {
                int key = in.nextInt();
                Integer value = map.get(key);
                if (value == null) {
                    System.out.println(-1);
                } else {
                    System.out.println(value);
                }
            } else if (op == 3) {
                int value = in.nextInt();
                map.setAll(value);
            }
        }

        in.close();
    }

    public static class MyValue<V> {
        public V value;
        public long time;

        public MyValue(V v, long t) {
            value = v;
            time = t;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<V>(null, -1);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<V>(value, time++));
        }

        public void setAll(V value) {
            setAll = new MyValue<V>(value, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).value;
            } else {
                return setAll.value;
            }
        }
    }
}
