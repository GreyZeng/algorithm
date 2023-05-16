package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// TODO
// 设计一个数据结构，和文件系统很像
// 类中的方法如下：
// FileSystem()：初始化这个数据结构
// List<String> ls(String
// path)：如果根据路径path找到的是文件，列出这个文件的名字，如果path找到的是文件夹，列出这个文件夹里的所有文件夹和文件名。（就像ls命令一样）
// void mkdir(String path)：根据path路径建出文件夹，如果中间的文件夹缺失，一路都建立出来。
// void addContentToFile(String filePath, String
// content)：根据filePath路径找到文件，如果不存在就新建。然后把内容content加在这个文件的尾部。
// String readContentFromFile(String filePath)：根据filePath读出文件内容并返回。
// leetcode题目：https://leetcode.cn/problems/design-in-memory-file-system/
// tips:
// 前缀树使用
public class LeetCode_0588_DesignInMemoryFileSystem {

    class FileSystem {

        public class Node {
            // 文件名、目录名
            public String name;
            // content == null 意味着这个节点是目录
            // content != null 意味着这个节点是文件
            public StringBuilder content;
            public TreeMap<String, Node> nexts;

            // 构造目录
            public Node(String n) {
                name = n;
                content = null;
                nexts = new TreeMap<>();
            }

            // 构造文件，c是文件内容
            public Node(String n, String c) {
                name = n;
                content = new StringBuilder(c);
                nexts = new TreeMap<>();
            }

        }

        public Node head;

        public FileSystem() {
            head = new Node("");
        }

        public List<String> ls(String path) {
            List<String> ans = new ArrayList<>();
            Node cur = head;
            String[] parts = path.split("/");
            int n = parts.length;
            for (int i = 1; i < n; i++) {
                if (!cur.nexts.containsKey(parts[i])) {
                    return ans;
                }
                cur = cur.nexts.get(parts[i]);
            }
            // cur结束了！来到path最后的节点，该返回了
            // ls a/b/c cur 来到c目录
            // 如果c是目录，那么就要返回c下面所有的东西！
            // 如果c是文件，那么就值返回c
            if (cur.content == null) {
                ans.addAll(cur.nexts.keySet());
            } else {
                ans.add(cur.name);
            }
            return ans;
        }

        public void mkdir(String path) {
            Node cur = head;
            String[] parts = path.split("/");
            int n = parts.length;
            for (int i = 1; i < n; i++) {
                if (!cur.nexts.containsKey(parts[i])) {
                    cur.nexts.put(parts[i], new Node(parts[i]));
                }
                cur = cur.nexts.get(parts[i]);
            }
        }

        public void addContentToFile(String path, String content) {
            Node cur = head;
            String[] parts = path.split("/");
            int n = parts.length;
            for (int i = 1; i < n - 1; i++) {
                if (!cur.nexts.containsKey(parts[i])) {
                    cur.nexts.put(parts[i], new Node(parts[i]));
                }
                cur = cur.nexts.get(parts[i]);
            }
            // 来到的是倒数第二的节点了！注意for！
            if (!cur.nexts.containsKey(parts[n - 1])) {
                cur.nexts.put(parts[n - 1], new Node(parts[n - 1], ""));
            }
            cur.nexts.get(parts[n - 1]).content.append(content);
        }

        public String readContentFromFile(String path) {
            Node cur = head;
            String[] parts = path.split("/");
            int n = parts.length;
            for (int i = 1; i < n; i++) {
                if (!cur.nexts.containsKey(parts[i])) {
                    cur.nexts.put(parts[i], new Node(parts[i]));
                }
                cur = cur.nexts.get(parts[i]);
            }
            return cur.content.toString();
        }
    }

}
