## 题目

给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。

 

示例 1：

> 输入：n = 4, k = 2
> 输出：
> [
>   [2,4],
>   [3,4],
>   [2,3],
>   [1,2],
>   [1,3],
>   [1,4],
> ]

示例 2：

> 输入：n = 1, k = 1
> 输出：[[1]]


提示：

* 1 <= n <= 20
* 1 <= k <= n



**链接：**

https://leetcode-cn.com/problems/combinations

## 思路

**回溯**

1~n每一位可以有选择或不选择两种情况，通过回溯，遍历所有可能。

## 题解

回溯

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> list = new ArrayList<>();
        backtracing(1, n, list, k);
        return ans;
    }

    private void backtracing(int index, int n, List<Integer> list, int k) {
        if (list.size() == k) {
            ans.add(new ArrayList(list));
            return;
        }

        if (index > n) {
            return;
        }

        list.add(index);
        backtracing(index + 1, n, list, k);
        list.remove(list.size() - 1);

        backtracing(index + 1, n, list, k);
    }
}
```


