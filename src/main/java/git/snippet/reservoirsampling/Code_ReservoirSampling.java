package git.snippet.reservoirsampling;

// 蓄水池算法
// 解决的问题：
// 假设有一个源源吐出不同球的机器，
// 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
// 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里

// 解法：
// 吐出1到10号球，完全入袋
// 引入随机函数f(i)，提供一个值i，等概率返回1-i的一个数字
// 当K号球吐出的时候（K>10) ,我们通过以下决策决定是否要入袋
// 1) 10/K的概率决定球是否入袋 f(K) -> 如果返回10以内的数，则入袋，如果返回10以外的数，则扔掉
// 2) 第一步中如果决定入袋，那么袋子中已经存在的球以等概率丢弃一个。
// TODO 证明
// 假设吐出了17号球，3号球在袋中的概率
//
// ```
// 1 * （1- 10/11 * 1/10) * (1 - 10/12 * 1/10) *....(1 - 10/17 * 1/10) = 10/17
// ```
// 用处
// （10亿个抽100）
// 一台服务器 放100个用户
// 用户检查是否是首次登录
// 用户是第几个登录的用户
// 如果非首次登录，直接丢弃，如果首次登录，那么以第100/N概率选中
// 分布式蓄水池算法使用：https://www.jianshu.com/p/7a9ea6ece2af
// ## 相关习题
//
// LeetCode_0398_RandomPickIndex.java
//
// LeetCode_0382_LinkedListRandomNode.java
//
// LeetCode_0692_TopKFrequentWords.java
//
// NowCoder_Bitorrent.java
//
// ## UUID全局唯一生成器
//
// base + 各区域的服务器 range + base 最小值+range 压力被range分散掉
public class Code_ReservoirSampling {
    public static void main(String[] args) {
        System.out.println("hello");
        int all = 100;
        int choose = 10;
        RandomBox box = new RandomBox(choose);
        for (int num = 1; num <= all; num++) {
            box.add(num);
        }
        int[] ans = box.choices();
        for (int i = 0; i < ans.length; i++) {
            System.out.println(i + " times : " + ans[i]);
        }
    }

    public static class RandomBox {
        private int[] bag;
        // 袋子容量
        private int capacity;
        // 第几号球
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            this.capacity = capacity;
            count = 0;
        }

        // 随机函数，等概率生成1-max之间随机的一个数字
        // Math.random() -> 生成[0,1)范围内的数
        // (int)i 是对i进行向下取整
        private int rand(int max) {
            return (int) (Math.random() * max) + 1;
        }

        public void add(int num) {
            // 球个数增加
            count++;
            // 如果球的个数没有超过容量
            if (count <= capacity) {
                // 则入袋
                bag[count - 1] = num;
            } else if (rand(count) <= capacity) {
                // 否则以N/count的概率入袋
                bag[rand(capacity) - 1] = num;
            }
        }

        // 返回袋子中最终选中的球
        public int[] choices() {
            int[] res = new int[capacity];
            System.arraycopy(bag, 0, res, 0, capacity);
            return res;
        }
    }
}
