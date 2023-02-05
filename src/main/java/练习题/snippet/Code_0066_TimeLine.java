package 练习题.snippet;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Code_0066_TimeLine {


    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        int[] nodes = {1, 5, 8, 11, 16, 17, 29, 34, 39};
        for (int node : nodes) {
            set.add(node);
        }
        int[] timeLines = {3, 7, 12, 17, 19, 31, 40};
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < timeLines.length; i++) {
            Integer floor = set.floor(timeLines[i]);
            Integer celling = set.ceiling(timeLines[i]);
            int r = Integer.MAX_VALUE;
            if (floor != null && celling != null) {
                int gapFloor = Math.abs(floor - r);
                int gapCelling = Math.abs(celling - r);
                if (gapCelling == gapFloor) {
                    r = celling;
                } else if (gapFloor > gapCelling) {
                    r = celling;
                } else {
                    r = floor;
                }
            }
            if (floor == null) {
                r = celling;
            }
            if (celling == null) {
                r = floor;
            }
            ans.add("{line:" + timeLines[i] + ",time:" + r + "}");
        }
        printNode(ans);
    }

    public static void printNode(List<String> nodes) {
        StringBuilder sb = new StringBuilder("[");
        for (String node : nodes) {
            sb.append(node);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

}
