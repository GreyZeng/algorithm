package git.snippet.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目的宣讲。 给你每一个项目开始的时间和结束的时间 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。 返回最多的宣讲场次。
 */
// 笔记：https://www.cnblogs.com/greyzeng/p/16704842.html
public class Code_BestArrange {
    public static int bestArrange0(Program[] programs) {
        if (null == programs || programs.length < 1) {
            return 0;
        }
        List<Program> ans = new ArrayList<>();
        return p(programs, 0, ans);
    }

    public static int p(Program[] programs, int start, List<Program> ans) {
        if (programs.length == 0 || !enough(programs, start)) {
            return ans.size();
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < programs.length; i++) {
                if (start <= programs[i].start) {
                    ans.add(programs[i]);
                    max = Math.max(p(copyExcept(programs, i), programs[i].end, ans), max);
                    ans.remove(programs[i]);
                }
            }
            return max;
        }
    }

    private static boolean enough(Program[] programs, int start) {
        boolean enough = false;
        for (Program p : programs) {
            if (start <= p.start) {
                enough = true;
                break;
            }
        }
        return enough;
    }

    public static Program[] copyExcept(Program[] p, int i) {
        int ind = 0;
        Program[] n = new Program[p.length - 1];
        for (int j = 0; j < p.length; j++) {
            if (j != i) {
                n[ind++] = p[j];
            }
        }
        return n;
    }

    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    // 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
    // 返回能安排的最多会议数量
    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        // 还有会议可以选择
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    // 还剩什么会议都放在programs里
    // done 之前已经安排了多少会议，数量
    // timeLine目前来到的时间点是什么

    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        int timeLine = 0;
        int result = 0;
        for (Program program : programs) {
            if (timeLine <= program.start) {
                result++;
                timeLine = program.end;
            }
        }
        return result;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            int ans0 = bestArrange0(programs);
            int ans1 = bestArrange1(programs);
            int ans2 = bestArrange2(programs);
            if (ans1 != ans2 || ans1 != ans0 || ans0 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "start:" + start + " end:" + end;
        }
    }
}
