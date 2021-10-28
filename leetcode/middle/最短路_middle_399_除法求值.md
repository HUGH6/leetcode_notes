## 题目

给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。

另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

 

示例 1：

> 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
> 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
> 解释：
> 条件：a / b = 2.0, b / c = 3.0
> 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
> 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]

示例 2：

> 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
> 输出：[3.75000,0.40000,5.00000,0.20000]

示例 3：

> 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
> 输出：[0.50000,2.00000,-1.00000,-1.00000]


提示：

* 1 <= equations.length <= 20
* equations[i].length == 2
* 1 <= Ai.length, Bi.length <= 5
* values.length == equations.length
* 0.0 < values[i] <= 20.0
* 1 <= queries.length <= 20
* queries[i].length == 2
* 1 <= Cj.length, Dj.length <= 5
* Ai, Bi, Cj, Dj 由小写英文字母与数字组成



**链接：**

https://leetcode-cn.com/problems/evaluate-division

## 思路

图的最短路径/搜索

可以将该问题转化为一个图的问题，每个除数和被除数就是图中的节点，他们之间路径的权重就是他们除运算的结果。那么，当要求一个除法查询是，问题就转换为以被除数为起点，以除数为终点，查找他们之间的路径，而结果就是路径上权重的积（因为a/c = (a/b) * (b/c)）。

那么，直接用搜索算法或最短路径算法都可以求解。由于问题中有许多个不同查询，每次都需要重新调用一次遍历查询或单元最短路径，比较麻烦，因此干脆直接用floyd求解多源最短路径。然后直接查表即可。



## 题解

图的最短路径/搜索

```java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 建图
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (List<String> list : equations) {
            for (String key : list) {
                if (!map.containsKey(key)) {
                    map.put(key, count++);
                }
            }
        }

        double [][] gragh = new double[count][count];
        for (int i = 0; i < count; i++) {
            Arrays.fill(gragh[i], Double.MAX_VALUE);
        }
        int equationsLength = equations.size();
        for (int i = 0; i < equationsLength; i++) {
            List<String> list = equations.get(i);
            int fromNode = map.get(list.get(0));
            int toNode = map.get(list.get(1));

            gragh[fromNode][toNode] = values[i];
            gragh[toNode][fromNode] = 1 / values[i];
        }

        // 可以用搜索或者最短路径来实现
        floyd(gragh);

        // 输出答案
        double [] ans = new double[queries.size()];
        int index = 0;
        for (List<String> query : queries) {
            Integer a = map.get(query.get(0));
            Integer b = map.get(query.get(1));
            if (a == null || b == null) {
                ans[index++] = -1.0;
                continue;
            } else {
                if (gragh[a][b] == Double.MAX_VALUE) {
                    ans[index++] = -1.0;    
                } else {
                    ans[index++] = gragh[a][b];
                }
            }
        }

        return ans;
    }

    public void floyd(double [][] gragh) {
        int n = gragh.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (gragh[i][k] != Double.MAX_VALUE && gragh[k][j] != Double.MAX_VALUE  && gragh[i][k] * gragh[k][j] < gragh[i][j]) {
                        gragh[i][j] = gragh[i][k] * gragh[k][j];
                    }
                }
            }
        }
    }
}
```

