/*
链接：https://www.nowcoder.com/questionTerminal/64b472c9bed247b586859978d13145ad
来源：牛客网

给你一串路径，譬如： a\b\c a\d\e b\cst d\ 你把这些路径中蕴含的目录结构给画出来，子目录直接列在父目录下面，
并比父目录向右缩一格，就像这样： a   b     c   d      e b   cst d 同一级的需要按字母顺序排列，不能乱。

        输入描述:
        每个测试案例第一行为一个正整数n（n<=10）表示有n个路径，当n为0时，测试结束，接下来有n行，每行有一个字串表示一个路径，长度小于50。


        输出描述:
        输出目录结构，每一个测试样例的输出紧跟一个空行。
        示例1
        输入
        4
        a\b\c
        a\d\e
        b\cst
        d\
        0
        输出
        a
         b
          c
         d
          e
        b
         cst
        d
*/


package nowcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Young
 * @version 1.0
 * @date 2021/1/24 15:40
 */
public class NowCoder_Folder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != 0) {
            String[] folders = new String[n];
            for (int i = 0; i < n; i++) {
                folders[i] = in.next();
            }
            List<String> ans = print(folders);
            for (String a : ans) {
                System.out.println(a);
            }
            System.out.println();
            n = in.nextInt();
        }

        in.close();
    }

    public static List<String> print(String[] folders) {
        if (null == folders || folders.length == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        Node head = buildTrie(folders);
        buildLevel(head, 0, ans);
        return ans;
    }

    private static void buildLevel(Node head, int level, List<String> ans) {
        if (level != 0) {
            ans.add(space(level) + head.value);
        }
        for (Node next : head.next.values()) {
            buildLevel(next, level + 1, ans);
        }
    }

    private static String space(int level) {
        String res = "";
        for (int i = 1; i < level; i++) {
            res += "  ";
        }
        return res;
    }

    public static class Node {
        public String value;
        public TreeMap<String, Node> next;

        public Node(String v) {
            value = v;
            next = new TreeMap<>();
        }
    }

    public static Node buildTrie(String[] folders) {
        Node head = new Node("");
        for (String folder : folders) {
            String[] paths = folder.split("\\\\");
            Node cur = head;
            for (String path : paths) {
                if (!cur.next.containsKey(path)) {
                    cur.next.put(path, new Node(path));
                }
                cur = cur.next.get(path);
            }
        }
        return head;
    }

}
