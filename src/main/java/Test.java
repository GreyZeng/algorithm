public class Test {
    public static void main(String[] args) {
        int[] arr = {11, 1, 15, 10, 13, 4};
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        for (int i : eor) {
            System.out.println(Integer.toBinaryString(i));
        }
    }
}
