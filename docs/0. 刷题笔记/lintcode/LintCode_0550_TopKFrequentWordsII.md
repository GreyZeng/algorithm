## 题目描述

[题目链接](https://www.lintcode.com/problem/550/description)

## 思路

由于要统计每个字符串的次数，以及字典序，所以，我们需要把用户每次add的字符串封装成一个对象，这个对象中包括了这个字符串和这个字符串出现的次数。

假设我们封装的对象如下：

```java
public class Word {
    public String value; // 对应的字符串
    public int times; // 对应的字符串出现的次数

    public Word(String v, int t) {
        value = v;
        times = t;
    }
}
```

topk的要求是： 出现次数多的排前面,如果次数一样，字典序小的排前面

很容易想到用有序表+比较器来做。

比较器的规则定义成和topk的要求一样，然后把元素元素加入使用比较器的有序表中，如果要返回topk，直接从这个有序表弹出返回给用户即可。比较器的定义如下：

```java
public class TopKComparator implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        // 次数大的排前面，次数一样字典序在小的排前面
        return o1.times == o2.times ? o1.value.compareTo(o2.value) : (o2.times - o1.times);
    }
}
```

有序表配置这个比较器即可

```
TreeSet<Word>  topK = new TreeSet<>(new TopKComparator());
```

所以topk()方法很简单，只需要从有序表里面把元素拿出来返回给用户即可

```
public List<String> topk() {
      List<String> result = new ArrayList<>();
      for (Word word : topK) {
          result.add(word.value);
      }
      return result;
}
```

时间复杂度 O(K)

以上步骤不复杂，接下来是add的逻辑，add的每次操作都有可能对前面我们设置的topK有序表造成影响，

所以在每次add操作的时候需要有一个机制可以**告诉topK这个有序表，需要淘汰什么元素，需要新加哪个元素**，让topK这个有序表时时刻刻只存topk个元素，

这样就可以确保topK()方法比较单纯，时间复杂度保持在O(K)

所以接下来的问题是：**如何告诉topK这个有序表，需要淘汰什么元素，需要新加哪个元素**?

我们可以通过堆来维持一个门槛



add方法，复杂度O(log K)
topk方法，复杂度O(K)

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
