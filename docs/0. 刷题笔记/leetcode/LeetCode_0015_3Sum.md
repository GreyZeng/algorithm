## 题目描述

[题目链接](https://leetcode.com/problems/3sum/)


## 思路 

求三个数的和为0的子数组有哪些，我们可以转换一下，考虑一下求两个数之和等于某个target的子数组有哪些，
假设我们已经实现了这个方法：

```java
List<List<Integer>> twoSum(int[] numbers, int start, int end, int target)
```
这个方法表示：在numbers这个有序数组中，从start到end这个数组下标区间内，两个数之和为target的所有子数组有哪些

假设上面这个方法实现了，那么我们可以通过这个方法来求我们这个三数之和为0的问题，

我们可以：

```java
for(int i = len - 1; i >= 2; i++) {
    ...
    List<List<Integer>> list = twoSum(numbers,0, i-1, -numbers[i] )
    ...    
}
```
这样来求三数和为0的问题。

那么在有序数组中，怎么求两数之和为target比较快呢？

我们可以设置两个指针。一个指向start, 一个指向end, 先看start对应的值+end对应的值之和（我们假设为sum）与target之间的关系，

如果: sum > target

那么: end--

如果: sum < target

那么： start++

如果：sum == target

那么：收集答案

这里还有一些细节需要注意，就是数组中可能有重复元素，而题目要求不能有重复的元组，

那么在每次start++，或者end--的时候，都要判断移动到新的数是否和原数一样，如果一样，直接继续start++，或者end--
直到下一个和原数不相等的数才停止。

完整代码如下：

```java
public List<List<Integer>> threeSum(int[] numbers) {
        if (null == numbers || numbers.length == 0) {
            return new ArrayList<>();
        }
        int size = numbers.length;
        List<List<Integer>> result = new ArrayList<>();
        // 先排序 O(N*logN)
        Arrays.sort(numbers);
        for (int i = size - 1; i >= 2; i--) {
            if (i == size - 1 || numbers[i] != numbers[i + 1]) {
                // 必须要移动到下一个不等于number[i]的数才不会出现重复
                List<List<Integer>> lists = twoSum(numbers, 0, i - 1, -numbers[i]);
                if (null != lists && 0 != lists.size()) {
                    for (List<Integer> list : lists) {
                        list.add(numbers[i]);
                        result.add(list);
                    }
                }
            }

        }
        return result;
    }

    // start ... end 之间是有序的
    public List<List<Integer>> twoSum(int[] numbers, int start, int end, int target) {
        List<List<Integer>> list = new ArrayList<>();
        while (start < end) {
            int startVal = numbers[start];
            int endVal = numbers[end];
            int sum = startVal + endVal;
            if (sum < target) {
                while (start < end && numbers[start] == startVal) {
                    // 必须要移动到下一个不为startVal的数才不会出现重复
                    start++;
                }
            } else if (sum > target) {
                while (end > start && numbers[end] == endVal) {
                    end--;
                }
            } else {
                // sum == target
                List<Integer> pair = new LinkedList<>();
                pair.add(startVal);
                pair.add(endVal);
                list.add(pair);
                while (start < end && numbers[start] == startVal) {
                    start++;
                }
            }
        }
        return list;
    }

```


## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
