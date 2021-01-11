package snippet;

/**
 * 题目三
 * <p>
 * 怎么把一个int类型的数，提取出最右侧的1来
 */
public class Code_0005_RightOne {
    public static void main(String[] args) {
        int i = 116;
        int result = rightOne(i);
        System.out.println(result);
        String x = Integer.toBinaryString(i);
        String y = Integer.toBinaryString(result);
        // 1110100
        // 0001011
        System.out.println(" x = " + x);
        System.out.println(" y = " + y);
    }


    public static int rightOne(int N) {
        return N & ((~N) + 1);
    }
}
