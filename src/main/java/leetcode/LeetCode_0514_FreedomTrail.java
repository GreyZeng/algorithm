/*In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

        Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

        Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.

        At the stage of rotating the ring to spell the key character key[i]:

        You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
        If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
Note:

Length of both ring and key will be in range 1 to 100.
There are only lowercase letters in both strings and might be some duplcate characters in both strings.
It's guaranteed that string key could always be spelled by rotating the string ring.
        */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//tips:
//        f(i,j) -> i拨动到j怎么走最省
//        map记录哪些位置拥有某个字符
//        深度优先遍历
public class LeetCode_0514_FreedomTrail {
    public static int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] str1 = ring.toCharArray();
        int n = str1.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(str1[i])) {
                map.get(str1[i]).add(i);
            } else {
                map.put(str1[i], new ArrayList<>());
            }
        }
        return f(key.toCharArray(), map, 0, 0, n);
    }

    // cur 当前在哪个位置
    // index需要搞定index...
    // resize 电话盘上一共有多少个位置
    public static int f(char[] key, Map<Character, List<Integer>> map, int cur, int index, int resize) {
        return -1;
    }
    // 从i1到i2的最小距离
    public static int dial(int i1, int i2, int size) {
        return -1;
    }
}
