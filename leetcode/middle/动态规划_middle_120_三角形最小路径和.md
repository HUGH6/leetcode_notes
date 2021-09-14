## 题目

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

 

示例 1：

> 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
> 输出：11
> 解释：如下面简图所示：
>    2
>   3 4
>  6 5 7
> 4 1 8 3
> 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

示例 2：

> 输入：triangle = [[-10]]
> 输出：-10


提示：

* 1 <= triangle.length <= 200
* triangle[0].length == 1
* triangle[i].length == triangle[i - 1].length + 1
* -104 <= triangle[i][j] <= 104


进阶：

* 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？

https://leetcode-cn.com/problems/triangle

## 思路

动态规划

转换三角形的排列方式就可以看出上一层与下一层的规律：

```
0
1 2
3 4 5
6 7 8 9
```

可见，第l层的第i个元素，可以又上一层（l-1层）的第i和第i-1个元素的路径走到，因此，只需按照层遍历每个元素，比较上一层中都能到达该元素的路径最小值即可。

需要注意，由于上一层比该层小1个元素，因此，要考虑边界（上一层的第i和第i-1个元素未必能同时取到）。

当遍历完成后，只需重新比较最后一层的dp数组即可。

## 题解

动态规划

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> list = new ArrayList<>();

        for (List<Integer> l : triangle) {
            for (Integer i : l) {
                list.add(0);
            }
        }
        int len = list.size();
        list.set(0, triangle.get(0).get(0));

        int count = 0, level = 1;
        for (List<Integer> l : triangle) {
            if (l.size() == 1) {
                count++;
                level++;
                continue;
            }

            int levelStart = count;
            int levelCount = 0;
            int preLevelStart = levelStart - (level - 1);

            for (Integer i : l) {
                int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

                int preLevelIndex = preLevelStart + levelCount;
                if (preLevelIndex < levelStart) {
                    min1 = list.get(preLevelIndex);
                }   

                int preLevleIndex2 = preLevelIndex - 1;
                if (preLevleIndex2 >= preLevelStart) {
                    min2 = list.get(preLevleIndex2);
                }

                list.set(count, Math.min(min1, min2) + (int)i);

                levelCount++;
                count++;
            }
            level++;
        }

        int lastLevelStart = count - level + 1;
        int lastLevelCount = level - 1;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lastLevelCount; i++) {
            int index = lastLevelStart + i;
            if (min > list.get(index)) {
                min = list.get(index);
            }
        }

        return min;
    }
}
```

