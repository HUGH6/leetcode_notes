## 题目

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

> 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
> 输出：[1,2,3,6,9,8,7,4,5]

示例 2：

> 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
> 输出：[1,2,3,4,8,12,11,10,9,5,6,7]


限制：

* 0 <= matrix.length <= 100
* 0 <= matrix[i].length <= 100

**链接：**

https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof

## 思路

逐层扫描。

## 题解

```java
class Solution {
    private int [] ans = null;
    private int index = 0;
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        ans = new int [matrix.length * matrix[0].length];

        int xlen = matrix.length, ylen = matrix[0].length;
        int x = 0, y = 0;

        while (xlen > 0 && ylen > 0) {
            travel(matrix, x, y, xlen, ylen);

            xlen -= 2;
            ylen -= 2;
            x += 1;
            y += 1;
        }

        return ans;
    }

    public void travel(int [][] matrix, int x, int y, int xlen, int ylen) {

        if (xlen == 1) {
            for (int i = y; i <= y + ylen - 1; i++) {
                ans[index++] = matrix[x][i];
            }
        } else if (ylen == 1) {
            for (int i = x; i <= x + xlen - 1; i++) {
                ans[index++] = matrix[i][y];
            }
        } else {
            int yend = y + ylen - 1;
            for (int i = y; i <= yend; i++) {
                ans[index++] = matrix[x][i];
            }

            int xend = x + xlen - 1;
            for (int i = x + 1; i <= xend; i++) {
                ans[index++] = matrix[i][yend];
            }

            
            for (int i = yend - 1; i >= y; i--) {
                ans[index++] = matrix[xend][i];
            }

            for (int i = xend - 1; i >= x + 1; i--) {
                ans[index++] = matrix[i][y];
            }
        }


    }
}
```

