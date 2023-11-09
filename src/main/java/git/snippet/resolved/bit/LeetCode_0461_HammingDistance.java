package git.snippet.resolved.bit;

// ref LeetCode 191
// https://leetcode.com/problems/hamming-distance/description/
public class LeetCode_0461_HammingDistance {
    public static int hammingWeight2(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }

    public int hammingDistance(int x, int y) {
        return hammingWeight2(x ^ y);
    }
}
