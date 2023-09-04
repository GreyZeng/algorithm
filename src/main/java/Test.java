public class Test {
    public static void main(String[] args) {
        int a = 0b0101;
        System.out.println(a); // 5
        System.out.println(a & (-a)); // 1
        System.out.println(a & (~a + 1)); // 1
        int b = 0b0110;
        System.out.println(b); // 6
        System.out.println(b & (-b)); // 2
        System.out.println(b & (~b + 1)); // 2
    }
}
