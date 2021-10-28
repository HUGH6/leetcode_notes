## 题目

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

示例:

现有矩阵 matrix 如下：

> [
>   [1,   4,  7, 11, 15],
>   [2,   5,  8, 12, 19],
>   [3,   6,  9, 16, 22],
>   [10, 13, 14, 17, 24],
>   [18, 21, 23, 26, 30]
> ]

给定 target = 5，返回 true。

给定 target = 20，返回 false。

 

限制：

* 0 <= n <= 1000

* 0 <= m <= 1000



**链接：**

https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof

## 思路

只能从左下角或右上角进行查找。

## 题解

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int ylen = matrix.length;
        if (ylen == 0) {
            return false;
        }

        int xlen = matrix[0].length;
        if (xlen == 0) {
            return false;
        }

        int x = 0, y = ylen - 1;

        while (x >= 0 && x < xlen && y >= 0 && y < ylen) {
            if (matrix[y][x] == target) {
                return true;
            }

            if (matrix[y][x] < target) {
                x++;
            } else {
                y--;
            }
        }

        return false;
        
    }
}
```

