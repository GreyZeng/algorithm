package snippet;

/**
 * welcome to guangzhou 变成 guangzhou to welcome 只能转成char[]，不能使用额外空间
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/12/3
 * @since 1.8
 */
public class Code_0073_ReverseString {
    public static String reverse(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            char t = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = t;
        }
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ' || i + 1 == arr.length) {
                if (arr[i] == ' ')
                    end = i - 1;
                else if (i + 1 == arr.length)
                    end = i;
                for (int j = start; j < (start + end + 1) / 2; j++) {
                    char temp = arr[j];
                    arr[j] = arr[start + end - j];
                    arr[start + end - j] = temp;
                }
                start = i + 1;
            }
        }
        return String.valueOf(arr);
    }
}
