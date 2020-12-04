package nowcoder;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。 ‘X’表示墙，不能放灯，也不需要点亮 ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * <p>
 * 暴力方法 可以放灯的点，有放灯不放灯两种情况，在这两种情况下，摘出照亮所有点的情况， 然后再在这些情况中选出灯最少的方案
 */

// https://www.nowcoder.com/questionTerminal/45d20d0e59d94e7d8879f19a5755c177
public class NowCoder_Light {

    public static int minLight(String road) {
        if (null == road || road.trim().isEmpty()) {
            return 0;
        }
        char[] str = road.toCharArray();
        int light = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.') {
                light++;
                if (i + 1 == str.length) {
                    // 下一个节点就是尾节点，不需要继续走下去，在当前位置放个灯就可以了
                    break;
                } else {
                    if (str[i + 1] == 'X') {
                        i += 1;
                    } else {
                        // .  .   .   .
                        // i i+1 i+2 i+3
                        // str[i+1] == '.'
                        // i + 1 放一盏，然后去i+3位置放灯
                        i += 2;
                    }
                }
            }
        }
        return light;
    }

    public static int minLight1(String road) {
        if (null == road || road.trim().isEmpty()) {
            return 0;
        }
        char[] str = road.toCharArray();
        return process(str, 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) {
            // 在结束位置的时候，开始验证是否全部点亮所有的灯
            for (int i = 0; i < str.length; i++) {
                if (str[i] == '.') {
                    if (!lights.contains(i) && !lights.contains(i - 1) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(yes, no);
        }
    }

    // for test
    public static String randomString(int len) {
        char[] arr = new char[(int) (Math.random() * (len)) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() < 0.5 ? '.' : 'X';
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        int len = 20;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            String test = randomString(len);
            int ans1 = minLight(test);
            int ans2 = minLight1(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");


        // 以下为牛客的输入
        /*Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String str = in.next();
        System.out.println(minLight(String.valueOf(str)));
        in.close();*/
    }
}
