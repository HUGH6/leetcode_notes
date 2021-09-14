## 题目

给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。

 

示例 1：



> 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
> 输出：[[0,0,0],[0,1,0],[0,0,0]]

示例 2：



> 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
> 输出：[[0,0,0],[0,1,0],[1,2,1]]


提示：

* m == mat.length
* n == mat[i].length
* 1 <= m, n <= 104
* 1 <= m * n <= 104
* mat[i][j] is either 0 or 1.
* mat 中至少有一个 0 

**链接：**

https://leetcode-cn.com/problems/01-matrix

## 思路

方法1：广度有限搜索

时间：O（mn）

空间：O（mn）

从0出发，进行广度优先遍历，邻近0的1的距离则为1。以此层层遍历。

由于有多个0，因此可以将这些0看做一个整体0，即，现将所有0都入队列，然后先从0开始遍历。





## 题解

方法一：广度优先遍历

```java
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int [][] dis = new int[rows][cols];
        boolean [][] visited = new boolean[rows][cols];
        int [][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<Point> queue = new LinkedList<>();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (mat[x][y] == 0) {
                    queue.offer(new Point(x, y));
                    visited[x][y] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            for (int i = 0; i < 4; i++) {
                int a = x + dirs[i][0];
                int b = y + dirs[i][1];
                if (a >= 0 && b >= 0 && a < rows && b < cols && !visited[a][b]) {
                    dis[a][b] = dis[x][y] + 1;
                    visited[a][b] = true;
                    queue.offer(new Point(a, b));
                }
            }
        }

        return dis;

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


