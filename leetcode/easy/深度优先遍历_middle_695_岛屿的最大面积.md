## 题目

给定一个包含了一些 0 和 1 的非空二维数组 grid 。

一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

 

示例 1:

> [[0,0,1,0,0,0,0,1,0,0,0,0,0],
>  [0,0,0,0,0,0,0,1,1,1,0,0,0],
>  [0,1,1,0,1,0,0,0,0,0,0,0,0],
>  [0,1,0,0,1,1,0,0,1,0,1,0,0],
>  [0,1,0,0,1,1,0,0,1,1,1,0,0],
>  [0,0,0,0,0,0,0,0,0,0,1,0,0],
>  [0,0,0,0,0,0,0,1,1,1,0,0,0],
>  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
> 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。



示例 2:

> [[0,0,0,0,0,0,0,0]]
> 对于上面这个给定的矩阵, 返回 0。



**链接：**

https://leetcode-cn.com/problems/max-area-of-island

## 思路

求解联通分量：对于图中每个没被访问的节点调用dfs遍历。将其中所有的联通分量都遍历完。

## 题解


    class Solution {
        boolean [][] visited;
        int maxSize = 0;
        int currentSize = 0;
    
        public int maxAreaOfIsland(int[][] grid) {
            int xLen = grid.length;
            int yLen = grid[0].length;
            int nums = xLen * yLen;
            
            visited = new boolean[xLen][yLen];
    
            for (int i = 0; i < nums; i++) {
                int x = i / yLen;
                int y = i % yLen;
    
                if (!visited[x][y]) {
                    currentSize = 0;
                    dfs(grid, x, y);
                    if (currentSize > maxSize) {
                        maxSize = currentSize;
                    }
                }
            }
    
            return maxSize;
    
        }
    
        private void dfs(int [][] grid, int x, int y) {
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                return;
            }
    
            if (visited[x][y] || grid[x][y] != 1) {
                return;
            }
    
            visited[x][y] = true;
            currentSize++;
            dfs(grid, x - 1, y);
            dfs(grid, x + 1, y);
            dfs(grid, x, y - 1);
            dfs(grid, x, y + 1);
        }
    }