package git.snippet.resolved.bit;

// 怎么把一个int类型的数，提取出最右侧的1来
public class Code_RightOne {
    public static void main(String[] args) {
        int testTimes = 2000000000;
        for (int i = 0; i < testTimes; i++) {
            if (rightOne2(i) != rightOne(i)) {
                System.out.println("oops");
                return;
            }
        }
        System.out.println("nice!");
    }

    public static int rightOne(int N) {
        return N & ((~N) + 1);
    }

    public static int rightOne2(int N) {
        return N & (-N);
    }
}
