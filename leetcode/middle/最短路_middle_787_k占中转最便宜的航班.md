## 题目

有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。

现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。

 

示例 1：

> 输入: 
> n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
> src = 0, dst = 2, k = 1
> 输出: 200
> 解释: 
> 城市航班图如下
>
> ![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png)

从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。

示例 2：

> 输入: 
> n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
> src = 0, dst = 2, k = 0
> 输出: 500
> 解释: 
> 城市航班图如下
>
> ![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png)


从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。


提示：

* 1 <= n <= 100
* 0 <= flights.length <= (n * (n - 1) / 2)
* flights[i].length == 3
* 0 <= fromi, toi < n
* fromi != toi
* 1 <= pricei <= 104
* 航班没有重复，且不存在自环
* 0 <= src, dst, k < n
* src != dst

**链接：**

https://leetcode-cn.com/problems/cheapest-flights-within-k-stops

## 思路

Bellman-flod/动态规划

从题面看就能知道，这是一类「有限制」的最短路问题。

「限制最多经过不超过 k 个点」等价于「限制最多不超过 k+1 条边」，而解决「有边数限制的最短路问题」是 SPFA 所不能取代 Bellman Ford 算法的经典应用之一（SPFA 能做，但不能直接做）。

经典Bellman的思路只能保证在k轮对所有边进行松弛后一定能找到最短路径，但不能保证最短路的长度在k以内。因此，需要转换一下理解思路，以动态规划的方式去理解。

Bellman Ford是基于动态规划，其原始的状态定义为 f\[i\]\[k\] 代表从起点到 i 点，且经过最多 k 条边的最短路径。这样的状态定义引导我们能够使用 Bellman Ford 来解决有边数限制的最短路问题。

那么，f\[i\]\[k\]就等于所有可以到i的节点a中，源点到这些a最多只经过k - 1条边的路径长度f\[a\]\[k - 1\] + gragh\[a\]\[i\]的最小值。这样就获状态递推的公式。

而这个递推可以直接采用bellman的形式实现。

因为mellman中每轮对所有边的松弛，就可以理解为，在当前长度不超过k的路径中，对所有上述边的松弛。在遍历所有的“点对/边”进行松弛操作前，需要先对 distdist 进行备份，否则会出现「本次松弛操作所使用到的边，也是在同一次迭代所更新的」，从而不满足边数限制的要求。

## 题解

Bellman-flod/动态规划

```java
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = Integer.MAX_VALUE / 2;
        // 建图
        int [][] gragh = new int [n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gragh[i][j] = (i == j) ? 0 : INF;
            }
        }
        for (int i = 0; i < flights.length; i++) {
            gragh[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        // bellman-fold/动态规划
        int [] dis = new int [n];
        Arrays.fill(dis, INF);
        dis[src] = 0;

        for (int t = 0; t <= k; t++) {
            int [] clone = dis.clone();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dis[j] = Math.min(dis[j], clone[i] + gragh[i][j]);
                }
            }
        }

        int ans = dis[dst];
        if (ans == INF) {
            return -1;
        } else {
            return ans;
        }
    }
}
```

