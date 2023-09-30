package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// TODO
// 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有
// ki 个身高大于或等于 hi 的人。
// 请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0]
// 是排在队列前面的人）。
// 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
// 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
// 解释：
// 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
// 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
// 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
// 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
// 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
// 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
// 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
// Leetcode题目 : https://leetcode.com/problems/queue-reconstruction-by-height/
// tips：
// 高度 + 次数 排序
// 按次数 放入 ArrayList/LinkedList (插入和找都是瓶颈）
// 设计一个结构O(logN) 改写有序表 使用SB树
public class LeetCode_0406_QueueReconstructionByHeight {

  public static int[][] reconstructQueue1(int[][] people) {
    int N = people.length;
    Unit[] units = new Unit[N];
    for (int i = 0; i < N; i++) {
      units[i] = new Unit(people[i][0], people[i][1]);
    }
    Arrays.sort(units, new UnitComparator());
    ArrayList<Unit> arrList = new ArrayList<>();
    for (Unit unit : units) {
      arrList.add(unit.k, unit);
    }
    int[][] ans = new int[N][2];
    int index = 0;
    for (Unit unit : arrList) {
      ans[index][0] = unit.h;
      ans[index++][1] = unit.k;
    }
    return ans;
  }

  public static int[][] reconstructQueue2(int[][] people) {
    int N = people.length;
    Unit[] units = new Unit[N];
    for (int i = 0; i < N; i++) {
      units[i] = new Unit(people[i][0], people[i][1]);
    }
    Arrays.sort(units, new UnitComparator());
    SBTree tree = new SBTree();
    for (int i = 0; i < N; i++) {
      tree.insert(units[i].k, i);
    }
    LinkedList<Integer> allIndexes = tree.allIndexes();
    int[][] ans = new int[N][2];
    int index = 0;
    for (Integer arri : allIndexes) {
      ans[index][0] = units[arri].h;
      ans[index++][1] = units[arri].k;
    }
    return ans;
  }

  public static class Unit {
    public int h;
    public int k;

    public Unit(int height, int greater) {
      h = height;
      k = greater;
    }
  }

  public static class UnitComparator implements Comparator<Unit> {

    @Override
    public int compare(Unit o1, Unit o2) {
      return o1.h != o2.h ? (o2.h - o1.h) : (o1.k - o2.k);
    }
  }

  public static class SBTNode {
    public int value;
    public SBTNode l;
    public SBTNode r;
    public int size;

    public SBTNode(int arrIndex) {
      value = arrIndex;
      size = 1;
    }
  }

  public static class SBTree {
    private SBTNode root;

    private SBTNode rightRotate(SBTNode cur) {
      SBTNode leftNode = cur.l;
      cur.l = leftNode.r;
      leftNode.r = cur;
      leftNode.size = cur.size;
      cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
      return leftNode;
    }

    private SBTNode leftRotate(SBTNode cur) {
      SBTNode rightNode = cur.r;
      cur.r = rightNode.l;
      rightNode.l = cur;
      rightNode.size = cur.size;
      cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
      return rightNode;
    }

    private SBTNode maintain(SBTNode cur) {
      if (cur == null) {
        return null;
      }
      int leftSize = cur.l != null ? cur.l.size : 0;
      int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
      int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
      int rightSize = cur.r != null ? cur.r.size : 0;
      int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
      int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
      if (leftLeftSize > rightSize) {
        cur = rightRotate(cur);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      } else if (leftRightSize > rightSize) {
        cur.l = leftRotate(cur.l);
        cur = rightRotate(cur);
        cur.l = maintain(cur.l);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      } else if (rightRightSize > leftSize) {
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur = maintain(cur);
      } else if (rightLeftSize > leftSize) {
        cur.r = rightRotate(cur.r);
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }
      return cur;
    }

    private SBTNode insert(SBTNode root, int index, SBTNode cur) {
      if (root == null) {
        return cur;
      }
      root.size++;
      int leftAndHeadSize = (root.l != null ? root.l.size : 0) + 1;
      if (index < leftAndHeadSize) {
        root.l = insert(root.l, index, cur);
      } else {
        root.r = insert(root.r, index - leftAndHeadSize, cur);
      }
      root = maintain(root);
      return root;
    }

    private SBTNode get(SBTNode root, int index) {
      int leftSize = root.l != null ? root.l.size : 0;
      if (index < leftSize) {
        return get(root.l, index);
      } else if (index == leftSize) {
        return root;
      } else {
        return get(root.r, index - leftSize - 1);
      }
    }

    private void process(SBTNode head, LinkedList<Integer> indexes) {
      if (head == null) {
        return;
      }
      process(head.l, indexes);
      indexes.addLast(head.value);
      process(head.r, indexes);
    }

    public void insert(int index, int value) {
      SBTNode cur = new SBTNode(value);
      if (root == null) {
        root = cur;
      } else {
        if (index <= root.size) {
          root = insert(root, index, cur);
        }
      }
    }

    public int get(int index) {
      SBTNode ans = get(root, index);
      return ans.value;
    }

    public LinkedList<Integer> allIndexes() {
      LinkedList<Integer> indexes = new LinkedList<>();
      process(root, indexes);
      return indexes;
    }
  }

  // 通过以下这个测试，
  // 可以很明显的看到LinkedList的插入和get效率不如SBTree
  // LinkedList需要找到index所在的位置之后才能插入或者读取，时间复杂度O(N)
  // SBTree是平衡搜索二叉树，所以插入或者读取时间复杂度都是O(logN)
  public static void main(String[] args) {
    // 功能测试
    int test = 10000;
    int max = 1000000;
    boolean pass = true;
    LinkedList<Integer> list = new LinkedList<>();
    SBTree sbtree = new SBTree();
    for (int i = 0; i < test; i++) {
      int randomIndex = (int) (Math.random() * (i + 1));
      int randomValue = (int) (Math.random() * (max + 1));
      list.add(randomIndex, randomValue);
      sbtree.insert(randomIndex, randomValue);
    }
    for (int i = 0; i < test; i++) {
      if (list.get(i) != sbtree.get(i)) {
        pass = false;
        break;
      }
    }
    System.out.println("功能测试是否通过 : " + pass);

    // 性能测试
    test = 50000;
    list = new LinkedList<>();
    sbtree = new SBTree();
    long start = 0;
    long end = 0;

    start = System.currentTimeMillis();
    for (int i = 0; i < test; i++) {
      int randomIndex = (int) (Math.random() * (i + 1));
      int randomValue = (int) (Math.random() * (max + 1));
      list.add(randomIndex, randomValue);
    }
    end = System.currentTimeMillis();
    System.out.println("LinkedList插入总时长(毫秒) ： " + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < test; i++) {
      int randomIndex = (int) (Math.random() * (i + 1));
      list.get(randomIndex);
    }
    end = System.currentTimeMillis();
    System.out.println("LinkedList读取总时长(毫秒) : " + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < test; i++) {
      int randomIndex = (int) (Math.random() * (i + 1));
      int randomValue = (int) (Math.random() * (max + 1));
      sbtree.insert(randomIndex, randomValue);
    }
    end = System.currentTimeMillis();
    System.out.println("SBTree插入总时长(毫秒) : " + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < test; i++) {
      int randomIndex = (int) (Math.random() * (i + 1));
      sbtree.get(randomIndex);
    }
    end = System.currentTimeMillis();
    System.out.println("SBTree读取总时长(毫秒) :  " + (end - start));
  }
}
