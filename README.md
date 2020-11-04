## >> 和 >>> 区别

>> 表示带符合右移
100011000
-> 110001100
最高位和符号位一致

>>> 表示不带符号右移
100011000
-> 010001100

不管符号位原来是什么，都用0来补





## 选择排序

arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。

arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。

arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。

…

arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。

所以选择排序的时间复杂度为O(N^2)。

Code_0002_SelectionSort.java



## 冒泡排序

在arr[0～N-1]范围上：

arr[0]和arr[1]，谁大谁来到1位置；

arr[1]和arr[2]，谁大谁来到2位置

…

arr[N-2]和arr[N-1]，谁大谁来到N-1位置

在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]
，谁大谁来到N-2位置

在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置

…

最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置

Code_0003_BubbleSort.java



## 插入排序

想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。

想让arr[0~1]上有序，所以从arr[1]开始往前看，如果arr[1] < arr[0]，就交换。否则什么也不做。

…

想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。

最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。


估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。

Code_0001_InsertionSort.java

## 二分

1. 在一个有序数组中，找某个数是否存在
2. 在一个有序数组中，找>=某个数的最左位置
3. 在一个有序数组中，找<=某个数的最右位置
4. 局部最小值问题
arr 无序数组，相邻的数都不相等，返回一个局部最小的位置即可
先看0位置是不是局部最小，
然后再看N-1位置是不是局部最小
然后来中点，然后二分（某个位置比左边小，又比右最小就是那个位置）

二分取中点的安全方式 int M = L + (R - L) >> 1
N * 2 => N << 1
N * 2 + 1 => (N << 1) | 1

Code_0019_BinarySearch.java

## 异或运算就是无进位相加

0^N = N
N^N = 0
异或满足交换律和结合律

```
a = a^b;
b = a^b;
a = a^b;
```

## 怎么把一个int类型的数，提取出最右侧的1来（二进制）

```
i & (~i + 1)
```



## 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数？

Code_0004_EvenOddTimes.java

## 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数

1. 假设出现了奇数次的数字为a和b，我们对数组所有数做异或操作，得到的最后结果一定是：a^b，记这个值为：m = a^b
2. 将m的二进制数的最右侧的1所代表的数找到，假设这个数为n，最右侧1所在的位置为r
3. 那么整个数组中，一定分为两类数：r位置上为1和r位置上为0的数，且a和b一定分别位于这两类数中（a和b不可能在r位置上同时为0或者同时为1）
4. 我们可以通过n和数组中每个数字做与操作，如果为0，说明这个数字中的r位置为0
5. 然后将这些r位置中为0的数字做异或操作，得到最后得结果就是a和b中的一个，假设为a
6. 然后将a和m做异或，得到b

Code_0004_EvenOddTimes.java

一个数字中有多少个1
LeetCode_0191_NumberOfOneBits.java

## 单链表反转

```java 
    public static Node reverse(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
```

## 双向链表反转

```java
public static DoubleNode reverse(DoubleNode head) {
        DoubleNode next = null;
        DoubleNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    } 
```

## 链表中删除给定值的节点

> 为了防止头节点就是要删除的节点，所以一开始要先找到不需要删除的第一个节点 

```java
public static Node removeGivenNum(Node head, int num) {
        if (head == null) {
            return null;
        }
        // 找到第一个不需要删除的节点
        // 防止要删的节点就是头节点以及头节点下面的一批节点
        while (head != null) {
            if (head.v != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.v == num) {
                pre.next = cur.next;
                pre = cur;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
```

## 双向链表实现栈和队列
```java
public final static class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> last;

        public Node(T data) {
            this.data = data;
        }
    }

    public final static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                tail = node;
            } else {
                node.next = head;
                head.last = node;
            }
            head = node;
        }

        public void addFromBottom(T value) {
            Node<T> node = new Node<>(value);
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
                node.last = tail;
            }
            tail = node;
        }

        public T popFromHead() {
            if (null == head || tail == null) {
                return null;
            }
            T data = head.data;

            if (head == tail) {
                head = null;
                tail = null;
                return data;
            }
            head = head.next;
            head.last = null;

            return data;

        }

        public T popFromBottom() {
            if (tail == null || head == null) {
                return null;
            }
            T data = tail.data;
            if (tail == head) {
                tail = null;
                head = null;
                return data;
            }
            tail = tail.last;
            tail.next = null;

            return data;
        }

        public boolean isEmpty() {
            return head == null || tail == null;
        }

    }

    public final static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            if (null == queue || isEmpty()) {
                return null;
            }
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public final static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            if (null == queue) {
                return;
            }
            queue.addFromHead(value);
        }

        public T poll() {
            if (isEmpty()) {
                return null;
            }
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }
```
## 数组实现栈和队列
```java
public final static class MyStack<T> {
        private ArrayList<T> queue;

        public MyStack() {
            queue = new ArrayList<>();
        }

        public void push(T value) {
            queue.add(value);
        }

        public T pop() {
            if (null == queue || isEmpty()) {
                return null;
            }
            return queue.remove(queue.size()  - 1);
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public final static class MyQueue<T> {
        private ArrayList<T> queue;

        public MyQueue() {
            queue = new ArrayList<>();
        }

        public void push(T value) {
            queue.add(value);

        }

        public T poll() {
            if (isEmpty()) {
                return null;
            }
            return queue.remove(0);
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }
```



## 怎么用数组实现不超过固定大小的队列和栈？

> 环形队列

```java
public static final class MyQueue {
        private int[] array;
        private int size;
        private final int limit;
        private int pushi;
        private int polli;

        public MyQueue(int limit) {
            this.limit = limit;
            array = new int[limit];
            this.size = 0;
            this.pushi = 0;
            this.polli = 0;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Queue is empty, can not pop");
            }
            size--;
            int data = array[polli];
            polli = nextIndex(polli);
            return data;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("Queue is full, can not push");
            }
            size++;
            array[pushi] = value;
            pushi = nextIndex(pushi);
        }

        private int nextIndex(int index) {
            return (index > limit - 1) ? 0 : index + 1;

        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
```


## 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能

1. pop、push、getMin操作的时间复杂度都是 O(1)。

2. 设计的栈类型可以使用现成的栈结构。

tips: 
方法1. 使用两个栈 空间复杂度O(N)
Code_0012_MinStack
LeetCode_0155_MinStack

方法2. 空间复杂度O(1) 但是需要限定值的范围
Code_0013_MinStackO1


## 如何用队列实现栈
```java
public static class MyStack<T> {
        private Queue<T> queue;
        private Queue<T> help;
        private int size;

        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("no data to pop");
            }
            size--;
            for (int i = 0; i < size; i++) {
                help.offer(queue.poll());
            }
            T result = queue.poll();
            Queue<T> t = queue;
            queue = help;
            help = t;
            return result;
        }

        public void push(T value) {
            queue.offer(value);
            size++;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("no data to peek");
            }
            T ans = null;
            for (int i = 0; i < size; i++) {
                T result = queue.poll();
                if (i == size - 1) {
                    ans = result;
                }
                help.offer(result);
            }
            Queue<T> t = queue;
            queue = help;
            help = t;
            return ans;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }
    }
```
## 如何用栈实现队列

```java
public static class MyQueue<T> {
        private Stack<T> push;
        private Stack<T> pop;
        private int size;

        public MyQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        public void add(T v) {
            push.push(v);
            size++;
        }

        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("no data to poll");
            }
            size--;
            for (int i = 0; i < size; i++) {
                pop.push(push.pop());
            }
            T result = push.pop();
            for (int i = 0; i < size; i++) {
                push.push(pop.pop());
            }
            return result;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("no data to peek");
            }

            for (int i = 0; i < size - 1; i++) {
                pop.push(push.pop());
            }
            T result = push.peek();
            pop.push(push.pop());
            for (int i = 0; i < size; i++) {
                push.push(pop.pop());
            }
            return result;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }
    }
```

## 完美洗牌问题

给定一个长度为偶数的数组arr，长度记为2*N。
前N个为左部分，后N个为右部分。 arr就可以表示为{L1,L2,..,Ln,R1,R2,..,Rn}， 请将数组调整成{R1,L1,R2,L2,..,Rn,Ln}的样子。

- 一个结论：当arr长度S = 3^k - 1 (偶数)的时候，环的出发点1,3,9...3^(k-1)

前提：
 1) 数组从1开始计算
 2) 数组长度偶数


Code_0017_Shuffle.java

## 单调栈
 
 左边右边离它最近比它小的数 O（N）

 - 数组中有重复 Stack<List<Integer>>
 - 数组中无重复 Stack<Integer>
 
 栈底到栈顶从小到大
 弹出的时候，假设弹出的值是A，那么让它弹出的值就是它右边离它最近的最小值
 原先A压的是谁，那么谁就是A左边离它最近的最小值

Code_0018_MonoStack.java


## Manacher算法
 
用来解决回文问题，求一个字符串最长回文子串是什么 O(N)
 
暴力解法：
- 字符串每个字符之间用一个特殊字符插入，每个元素为中心，左边右边扩，直到扩不动为止
- 大小为：位置/2 = 代表原始串中的大小
- 复杂度：O（N^2）

基本概念：
- 回文半径
- 回文直径
- 回文区域
- 回文半径数组PARR[] ，每个位置得到的答案都放入PARR[]
- 回文最右边界 （int R) ，中心(int C) ---> C就是扩到R位置的的中心点

i当前位置，如果

    1）i在R外，同暴力方法

    2）i在R内或者和R同位置

 假设i'为i关于C对称的点
  
  i' 自己的回文区域都在L。。R内，所以i的答案和i'的答案一样，存入parr中
  
  i' 自己的回文区域在L。。。R外，i到R的距离就是i的回文半径
  
  i' 自己的回文区域左边界和L压线，需要继续验，R外的情况
 
LeetCode_0005_LongestPalindromicSubstring.java

LeetCode_0647_PalindromicSubstrings.java

LeetCode_0214_ShortestPalindrome.java



## 最长递增子序列问题的O(N*logN)解法

- 这里的递增指的是严格递增（相等都不算）
- 暴力解是 O(N^2)

经典解法：
- ends数组，ends[i] 找到的所有长度为i+1的递增子序列中最小结尾是什么
- dp[i]数组, 必须以i结尾的，最长递增子序列有多长

Code_0021_LIS.java
LeetCode_0300_LongestIncreasingSubsequence.java
LeetCode_0334_IncreasingTripletSubsequence.java
LeetCode_0354_RussianDollEnvelopes.java


