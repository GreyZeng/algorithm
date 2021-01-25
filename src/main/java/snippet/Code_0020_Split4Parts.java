package snippet;

/**
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 * 例如:
 * arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
 * 三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
 * 累加和都是5
 * <p>
 * tips:
 * 数组长度一定要大于7
 * 前缀和加入map中
 * 第一刀 1 .... N - 6
 * 第一刀确定后，第二刀的位置
 *
 * @author Young
 * @version 1.0
 * @date 2021/1/25 22:23
 */
// TODO
public class Code_0020_Split4Parts {
    // 总长度不能小于7
    // 第一刀不能超过N-6
    // 前缀和加速
    public static boolean canSplits(int[] arr) {
        return false;
    }
}
