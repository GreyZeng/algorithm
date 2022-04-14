package snippet;

/*
 * 给定一个数组arr，长度为N，arr中的值不是0就是1
 * arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+2栈灯的状态
 * 问题一：
 * 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
 * 排成一条直线说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 *
 * 问题二：
 * 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * 排成一个圈说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 *
 * */
public class Code_0014_LightProblem {

    // 无环改灯问题的暴力版本
    public static int noLoopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f1(arr, 0);
    }

    public static int f1(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f1(arr, i + 1);
        change1(arr, i);
        int p2 = f1(arr, i + 1);
        change1(arr, i);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static void change1(int[] arr, int i) {
        if (i == 0) {
            arr[0] ^= 1;
            arr[1] ^= 1;
        } else if (i == arr.length - 1) {
            arr[i - 1] ^= 1;
            arr[i] ^= 1;
        } else {
            arr[i - 1] ^= 1;
            arr[i] ^= 1;
            arr[i + 1] ^= 1;
        }
    }

    public static boolean valid(int[] arr) {
        for (int n : arr) {
            if (n == 0) {
                return false;
            }
        }
        return true;
    }

    public static int noLoopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0位置点灯
        int p1 = p(2, arr[1] ^ 1, arr[0] ^ 1, arr);
        if (p1 != Integer.MAX_VALUE) {
            p1 += 1;
        }
        // 0位置不点灯
        int p2 = p(2, arr[1], arr[0], arr);
        return Math.min(p1, p2);
    }

    // 当前位置是：cur,可以省略这个变量
    // cur的下一个位置是next
    // cur位置的状态是curStatus
    // cur前一个位置的状态是preStatus
    // preStatus之前的灯全部是亮的状态
    // 返回最少的点灯数量
    public static int p(int next, int curStatus, int preStatus, int[] arr) {
        if (next == arr.length) {
            // cur 来到结尾位置
            // 处理结尾两位的状态，同时是1就按下0次开关，同时是0就按下一次开关，否则，怎么都做不到
            return curStatus == preStatus ? curStatus ^ 1 : Integer.MAX_VALUE;
        }
        if (preStatus == 0) {
            // cur位置必须按下开关，否则就永远不可能让preStatus亮起来
            int p1 = p(next + 1, arr[next] ^ 1, curStatus ^ 1, arr);
            return p1 == Integer.MAX_VALUE ? p1 : p1 + 1;
        } else {
            // cur位置一定不能按下开关
            return p(next + 1, arr[next], curStatus, arr);
        }
    }

    // 无环改灯问题的迭代版本
    public static int noLoopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        int p1 = traceNoLoop(arr, arr[0], arr[1]);
        int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
        int i = 2;
        int op = 0;
        while (i != arr.length) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        return (preStatus != curStatus) ? Integer.MAX_VALUE : (op + (curStatus ^ 1));
    }

    // 有环改灯问题的暴力版本
    public static int loopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f2(arr, 0);
    }

    public static int f2(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f2(arr, i + 1);
        change2(arr, i);
        int p2 = f2(arr, i + 1);
        change2(arr, i);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static void change2(int[] arr, int i) {
        arr[lastIndex(i, arr.length)] ^= 1;
        arr[i] ^= 1;
        arr[nextIndex(i, arr.length)] ^= 1;
    }

    public static int lastIndex(int i, int N) {
        return i == 0 ? (N - 1) : (i - 1);
    }

    public static int nextIndex(int i, int N) {
        return i == N - 1 ? 0 : (i + 1);
    }

    // 有环改灯问题的递归版本
    // 在无环的版本中增加两个状态：zeroStatus，endStatus
    public static int loopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
//        // 当前来到的位置(nextIndex - 1)，一定不能是1！至少从2开始，因为1位置比较特殊，因为1位置前一个0位置可亮可不亮
//        // 所以直接调用下述四个分支，跳过第一个位置
//        // 0不变，1不变
        int p1 = process2(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
//        // 0改变，1不变
        int p2 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
//        // 0不变，1改变
        int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
//        // 0改变，1改变
        int p4 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    // 下一个位置是，nextIndex
    // 当前位置是，nextIndex - 1 -> curIndex
    // 上一个位置是, nextIndex - 2 -> preIndex   preStatus
    // 当前位置是，nextIndex - 1, curStatus
    // endStatus, N-1位置的状态，一直可以copy，直到N-2号做决策的时候，会影响
    // firstStatus, 0位置的状态
    // 返回，让所有灯都亮，至少按下几次按钮

    // 当前来到的位置(nextIndex - 1)，一定不能是1！至少从2开始，因为1位置比较特殊，因为1位置前一个0位置可亮可不亮
    // nextIndex >= 3
    public static int process2(int[] arr, int nextIndex, int preStatus, int curStatus, int endStatus, int firstStatus) {

        if (nextIndex == arr.length) { // 最后一按钮！
            return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }
        // 当前位置，nextIndex - 1
        // 当前的状态，叫curStatus
        // 如果不按下按钮，下一步的preStatus, curStatus
        // 如果按下按钮，下一步的preStatus, curStatus ^ 1
        // 如果不按下按钮，下一步的curStatus, arr[nextIndex]
        // 如果按下按钮，下一步的curStatus, arr[nextIndex] ^ 1
        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus = 0;
        int yesNextCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if (nextIndex < arr.length - 1) {// 当前没来到N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else if (nextIndex == arr.length - 1) {// 当前来到的就是N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus;
            yesNextCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }

        if (preStatus == 0) {
            int next = process2(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus, nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else {
            return process2(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus, nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);

        }
//		int curStay = (nextIndex == arr.length - 1) ? endStatus : arr[nextIndex];
//		int curChange = (nextIndex == arr.length - 1) ? (endStatus ^ 1) : (arr[nextIndex] ^ 1);
//		int endChange = (nextIndex == arr.length - 1) ? curChange : endStatus;
//		if (preStatus == 0) {
//			int next = process2(arr, nextIndex + 1, curStatus ^ 1, curChange, endChange, firstStatus);
//			return next == Integer.MAX_VALUE ? next : (next + 1);
//		} else {
//			return process2(arr, nextIndex + 1, curStatus, curStay, endStatus, firstStatus);
//		}
    }

    // 有环改灯问题的迭代版本
    public static int loopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0不变，1不变
        int p1 = traceLoop(arr, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = traceLoop(arr, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = traceLoop(arr, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = traceLoop(arr, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    public static int traceLoop(int[] arr, int preStatus, int curStatus, int endStatus, int firstStatus) {
        int i = 3;
        int op = 0;
        while (i < arr.length - 1) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = (arr[i++] ^ 1);
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        if (preStatus == 0) {
            op++;
            preStatus = curStatus ^ 1;
            endStatus ^= 1;
            curStatus = endStatus;
        } else {
            preStatus = curStatus;
            curStatus = endStatus;
        }
        return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (op + (endStatus ^ 1));
    }

    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("如果没有任何Oops打印，说明所有方法都正确");
        System.out.println("test begin");
        int testTime = 20000;
        int lenMax = 12;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int ans1 = noLoopRight(arr1);
            int ans2 = noLoopMinStep1(arr2);
            int ans3 = noLoopMinStep2(arr3);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("1 Oops!");
            }
        }
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int ans1 = loopRight(arr1);
            int ans2 = loopMinStep1(arr2);
            int ans3 = loopMinStep2(arr3);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("2 Oops!");
            }
        }
        System.out.println("test end");

        int len = 100000000;
        System.out.println("性能测试");
        System.out.println("数组大小：" + len);
        int[] arr = randomArray(len);
        int[] arr1 = copyArray(arr);
        int[] arr2 = copyArray(arr);
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        noLoopMinStep2(arr1);
        end = System.currentTimeMillis();
        System.out.println("noLoopMinStep2 run time: " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        loopMinStep2(arr2);
        end = System.currentTimeMillis();
        System.out.println("loopMinStep2 run time: " + (end - start) + "(ms)");
    }

}
