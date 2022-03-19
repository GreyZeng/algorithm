# dinic算法

源点A，目标D，最多能灌水多少。

![dinic](dinic1.png)

深度优先遍历不行

![dinic](dinic2.png)

扣完以后，补充一个反向的边

原图
![dinic](dinic3.png)

补充反向边以后

![dinic](dinic4.png)

可以防止反悔的问题，如下图

![dinic](dinic5.png)

第一个优化，定义层数，相同层不串，所以防止了往回走的问题

![dinic](dinic6.png)

第二个优化，多支路情况下，不需要重复走之前走过的支路

比如下图中的E到D的情况

![dinic](dinic7.png)

如何找反向边  i^1 就是i的反向边
