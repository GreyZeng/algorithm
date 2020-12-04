package leetcode;

public class LeetCode_0011_ContainerWithMostWater {

	
	public static int maxArea(int[] height) {
		int max = 0;
		int l = 0;
		int r = height.length - 1;
		int cur;
		while (l < r) {
			if (height[l] > height[r]) {
				cur = (r - l) * height[r];
				r--;
			} else {
				cur = (r - l) * height[l];
				l++;
			}
			max = Math.max(max, cur);
		}
		return max;
	}

}
