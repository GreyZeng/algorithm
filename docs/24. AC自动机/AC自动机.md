文章中匹配多个候选词

1. 候选词先建立前缀树
2. 以宽度优先遍历的方式把前缀树的每个节点设置fail指针
   头节点的fail指针指向空
   头节点孩子的fail指针指向头
   其他节点的fail指针设置逻辑为：
      来到X节点的时候，是设置X的孩子的fail指针
   举例：
   case 1：假设X通过b指向了它的孩子，假设孩子为C，
   X的fail指针指向的节点假设为Y，
   Y有走向b的路，
   且Y走向b的路是指向的Z，那么 C的fail指针指向Z
   Y没有走向b的路，那么就看Y的fail指针指向的节点的fail指针有没有走向b的路，依次往复，如果走到null都没有，那么进入case 2
   case 2：如果X的fail指针指向null，那么就把X的孩子C指向头节点


fail指针的含义
假设：
abcde
bcde
cde
de
e

e的fail指针指向的是：假设要以e结尾，哪一个另外的后缀串和其前缀串完全相等
所以abcde中e的fail指针指向bcde中的e

   
假设文章是：abcdtks
候选词是：
abcde
bcdf
cdtks


假设文章是：abcde
候选词：
abc
abcde
abcd
bc
cd

假设文章是：abct
候选词：
abck
bct
st

每次来到一个节点，根据fail指针转一圈，收集答案，结尾位置改成不是结尾
失败的时候，要顺着fail指针蹦到另外一条路径
   