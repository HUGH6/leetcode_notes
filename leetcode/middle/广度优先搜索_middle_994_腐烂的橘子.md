## 题目

在给定的网格中，每个单元格可以有以下三个值之一：

值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。

 

示例 1：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/16/oranges.png)

> 输入：[[2,1,1],[1,1,0],[0,1,1]]
> 输出：4

示例 2：

> 输入：[[2,1,1],[0,1,1],[1,0,1]]
> 输出：-1
> 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。

示例 3：

> 输入：[[0,2]]
> 输出：0
> 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。


提示：

* 1 <= grid.length <= 10
* 1 <= grid[0].length <= 10
* grid[i][j] 仅为 0、1 或 2



**链接：**

https://leetcode-cn.com/problems/rotting-oranges

## 思路

广度有限搜索

时间：O（mn）

空间：O（mn）

从腐烂橘子出发，进行广度优先遍历，每层将旁边的新鲜橘子感染。

由于有多个腐烂橘子，因此可以将这些看做一个整体，即，现将所有腐烂橘子都入队列，然后开始遍历。

## 题解

广度优先遍历

```java
class Solution {
    public int orangesRotting(int[][] grid) {
        int [][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Point> queue = new LinkedList<>();

        int rows = grid.length, cols = grid[0].length;

        int time = -1;
        int count = 0;
        int bad = 0, good = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == 2) {
                    queue.offer(new Point(x, y));
                    grid[x][y] = -1;
                    count++;
                    bad++;
                } else if (grid[x][y] == 1) {
                    good++;
                }
            }
        }

        if (good == 0) {
            return 0;
        }

        int total = 0;
        while (!queue.isEmpty()) {
            int nums = count;
            count = 0;
            for (int i = 0; i < nums; i++) {
                Point p = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int x = p.x + dir[d][0];
                    int y = p.y + dir[d][1];

                    if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 1) {
                        count++;
                        grid[x][y] = -1;
                        queue.offer(new Point(x, y));
                    }
                }

            }
            total += count;
            time++;

        }

        return total == good ? time : -1;

    }

    public static class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
```

