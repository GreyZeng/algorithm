package 数据结构.树状数组;

// https://www.cnblogs.com/greyzeng/p/15343780.html
public class Code_IndexTree {

  // 下标从1开始！
  public static class IndexTree {

    private int[] tree;
    private int N;

    // 0位置弃而不用！
    public IndexTree(int size) {
      N = size;
      tree = new int[N + 1];
    }

    // 1~index 累加和是多少？
    public int sum(int index) {
      int ret = 0;
      while (index > 0) {
        ret += tree[index];
        index -= index & -index;
      }
      return ret;
    }

    // index位置上加d
    public void add(int index, int d) {
      while (index <= N) {
        tree[index] += d;
        index += index & -index;
      }
    }
  }

  public static class Right {
    private int[] nums;
    private int N;

    public Right(int size) {
      N = size + 1;
      nums = new int[N + 1];
    }

    public int sum(int index) {
      int ret = 0;
      for (int i = 1; i <= index; i++) {
        ret += nums[i];
      }
      return ret;
    }

    public void add(int index, int d) {
      nums[index] += d;
    }

  }

  public static void main(String[] args) {
    int N = 100;
    int V = 100;
    int testTime = 2000000;
    IndexTree tree = new IndexTree(N);
    Right test = new Right(N);
    System.out.println("test begin");
    for (int i = 0; i < testTime; i++) {
      int index = (int) (Math.random() * N) + 1;
      if (Math.random() <= 0.5) {
        int add = (int) (Math.random() * V);
        tree.add(index, add);
        test.add(index, add);
      } else {
        if (tree.sum(index) != test.sum(index)) {
          System.out.println("Oops!");
        }
      }
    }
    System.out.println("test finish");
  }

}
