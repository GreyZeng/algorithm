package leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// TODO
// 给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；
// 该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
// 实现扁平迭代器类 NestedIterator ：
// NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
// int next() 返回嵌套列表的下一个整数。
// boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
// 你的代码将会用下述伪代码检测：
// initialize iterator with nestedList
// res = []
// while iterator.hasNext()
// append iterator.next() to the end of res
// return res
// 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
// 示例 1：
// 输入：nestedList = [[1,1],2,[1,1]]
// 输出：[1,1,2,1,1]
// 解释：通过重复调用next 直到hasNext 返回 false，next返回的元素的顺序应该是: [1,1,2,1,1]。
// 示例 2：
// 输入：nestedList = [1,[4,[6]]]
// 输出：[1,4,6]
// 解释：通过重复调用next直到hasNext 返回 false，next返回的元素的顺序应该是: [1,4,6]。
// Leetcode题目 : https://leetcode.com/problems/flatten-nested-list-iterator/
public class LeetCode_0341_FlattenNestedListIterator {

  // 不要提交这个接口类
  public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  public class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> list;
    private Stack<Integer> stack;
    private boolean used;

    public NestedIterator(List<NestedInteger> nestedList) {
      list = nestedList;
      stack = new Stack<>();
      stack.push(-1);
      used = true;
      hasNext();
    }

    @Override
    public Integer next() {
      Integer ans = null;
      if (!used) {
        ans = get(list, stack);
        used = true;
        hasNext();
      }
      return ans;
    }

    @Override
    public boolean hasNext() {
      if (stack.isEmpty()) {
        return false;
      }
      if (!used) {
        return true;
      }
      if (findNext(list, stack)) {
        used = false;
      }
      return !used;
    }

    private Integer get(List<NestedInteger> nestedList, Stack<Integer> stack) {
      int index = stack.pop();
      Integer ans = null;
      if (!stack.isEmpty()) {
        ans = get(nestedList.get(index).getList(), stack);
      } else {
        ans = nestedList.get(index).getInteger();
      }
      stack.push(index);
      return ans;
    }

    private boolean findNext(List<NestedInteger> nestedList, Stack<Integer> stack) {
      int index = stack.pop();
      if (!stack.isEmpty() && findNext(nestedList.get(index).getList(), stack)) {
        stack.push(index);
        return true;
      }
      for (int i = index + 1; i < nestedList.size(); i++) {
        if (pickFirst(nestedList.get(i), i, stack)) {
          return true;
        }
      }
      return false;
    }

    private boolean pickFirst(NestedInteger nested, int position, Stack<Integer> stack) {
      if (nested.isInteger()) {
        stack.add(position);
        return true;
      } else {
        List<NestedInteger> actualList = nested.getList();
        for (int i = 0; i < actualList.size(); i++) {
          if (pickFirst(actualList.get(i), i, stack)) {
            stack.add(position);
            return true;
          }
        }
      }
      return false;
    }
  }
}
