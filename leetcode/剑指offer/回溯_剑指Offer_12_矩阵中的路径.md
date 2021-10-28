## 题目

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。

![](https://assets.leetcode.com/uploads/2020/11/04/word2.jpg)

 

示例 1：

> 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
> 输出：true

示例 2：

> 输入：board = [["a","b"],["c","d"]], word = "abcd"
> 输出：false


提示：

* 1 <= board.length <= 200
* 1 <= board[i].length <= 200
* board 和 word 仅由大小写英文字母组成

**链接：**

https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof

## 思路

对矩阵中的每个节点，都执行一次搜索。每遍历到一个节点就与字符串当前位置匹配，如果匹配成功，就继续向四周未匹配过的节点分别尝试匹配。如果匹配失败，则要恢复之前被置为已访问的节点的状态。通过回溯，查找所有的可能。

## 题解

```java
class Solution {
    private int [][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        int ylen = board.length, xlen = board[0].length;
        int nodes = xlen * ylen;

        boolean ans = false;

        for (int i = 0; i < nodes; i++) {
            int x = i % xlen;
            int y = i / xlen;

            boolean [][] visited = new boolean[ylen][xlen];
            ans |= match(word, 0, board, visited, x, y);
        }

        return ans;
    }

    public boolean match(String word, int index, char[][] board, boolean [][] visited, int x, int y) {
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) {
            return false;
        }

        if (visited[y][x]) {
            return false;
        }


        if (board[y][x] == word.charAt(index)) {
            visited[y][x] = true;

            if (index == word.length() - 1) {
                return true;
            }

            boolean ans = false;
            for (int [] dir : dirs) {
                ans |= match(word, index + 1, board, visited, x + dir[0], y + dir[1]);
            }

            if (!ans) {
                visited[y][x] = false;
            }

            return ans;
        }

        return false;
    }
}
```

