## 题目

地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

> 输入：m = 2, n = 3, k = 1
> 输出：3

示例 2：

> 输入：m = 3, n = 1, k = 0
> 输出：1

提示：

* 1 <= n,m <= 100
* 0 <= k <= 20

**链接：**

https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof

## 思路

从0,0点开始深度优先搜索。

## 题解

```java
class Solution {
    private boolean [][] visited;
    private int count = 0;

    private int [][] dirs = new int [][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];

        dfs(0, 0, k);

        return count;
    }

    public void dfs(int x, int y, int k) {
        if (x < 0 || x >= visited.length || y < 0 || y >= visited[0].length || visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        if (getSum(x, y) <= k) {
            count++;

            for (int [] dir : dirs) {
                dfs(x + dir[0], y + dir[1], k);
            }
        }
    }

    public int getSum(int x, int y) {
        int ans = 0;
        while (x != 0) {
            ans += x % 10;
            x /= 10;
        }

        while (y != 0) {
            ans += y % 10;
            y /= 10;
        }

        return ans;
    }
}
```

