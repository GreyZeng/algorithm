## 题目描述

[题目链接](https://leetcode.com/problems/valid-parentheses/)


## 思路

使用一个栈，然后开始遍历整个序列，入栈和出栈规则如下：

1. 遇到左括号入栈
2. 遇到右括号，从栈里先弹出一个元素，如果弹出的元素和这个右括号正好匹配，则继续，如果不匹配，直接返回不是合法序列

走完整个遍历后，栈为空，则是合法序列，栈不为空，则不是合法序列。

在遍历过程中（还没遍历结束），一旦某个时间点栈为空，则直接返回不合法序列。

完整源码：

```java
public static boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		char[] strs = s.toCharArray();
		Deque<Character> stack = new ArrayDeque<Character>();
		int len = strs.length;
		for (int i = 0; i < len; i++) {
			if (isLeft(strs[i])) {
				stack.push(strs[i]);
			} else {
				if (stack.isEmpty()) {
					// 遍历的中间过程，一旦发现栈空，则直接返回false
					return false;
				} else if (!isMatch(stack.poll(), strs[i])) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	
	public static boolean isLeft(char c) {
		return '(' == c || '{' == c || '[' == c;
	}
	
	public static boolean isMatch(char left, char right) {
		return (left == '[' && right == ']') || (left == '(' && right == ')') || (left == '{' && right == '}');
	}
```

注：这里没有使用stack，而是用的Deque，原因在这里：[Java Deque vs. Stack](https://www.baeldung.com/java-deque-vs-stack)

> We've seen that the Stack class is a subclass of java.util.Vector. The Vector class is synchronized. It uses the traditional way to achieve thread-safety: making its methods “synchronized.”
As its subclass, the Stack class is synchronized as well.
On the other hand, the Deque interface is not thread-safe.
So, if thread-safety is not a requirement, a Deque can bring us better performance than a Stack.

简言之，Deque使用无锁操作，线程不安全，但是效率更高，如果不要求线程安全，Deque是更好的选择。


## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料

- [Java Deque vs. Stack](https://www.baeldung.com/java-deque-vs-stack)
- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
