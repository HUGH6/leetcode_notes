## 题目

给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 

示例 1：

> 输入：nums = [1,5,11,5]
> 输出：true
> 解释：数组可以分割成 [1, 5, 5] 和 [11] 。

示例 2：

> 输入：nums = [1,2,3,5]
> 输出：false
> 解释：数组不能分割成两个元素和相等的子集。


提示：

* 1 <= nums.length <= 200
* 1 <= nums[i] <= 100

链接

https://leetcode-cn.com/problems/partition-equal-subset-sum

## 思路

**转换成01背包并用动态规划求解**

空间复杂度：$O(n×target)$

时间复杂度：$O(n \times target)$

通过分析题意，可以将题目转换为：判断数组中是否有子集的和等于整个数组的一半half。

这样，就将问题转换了一个01背包问题：判断数组中某些元素选取或不选取，使得某个子集的和等于half。

这样就可以使用动态规划求解了。

在此之前，可以对一些特殊情况进行直接判断：

* 数组长度 n 如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回false。

* 计算整个数组的元素和sum 以及最大元素maxNum。如果sum 是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回false。
* 如果sum 是偶数，则令 $target=\frac{sum}{2}$，需要判断是否可以从数组中选出一些数字，使得这些数字的和等于 target。
* 如果 maxNum>target，则除了 maxNum 以外的所有元素之和一定小于target，因此不可能将数组分割成元素和相等的两个子集，直接返回false。

创建二维数组dp，包含 n 行target+1 列，其中 dp\[i]\[j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 00 个），是否存在一种选取方案使得被选取的正整数的和等于 j。

初始时，dp 中的全部元素都是false。

在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况。

* 如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 $0 \le i < n$，都有 $\textit{dp}[i][0]=\text{true}$。

* 当 i==0 时，只有一个正整数nums[0] 可以被选取，因此 $\textit{dp}[0][\textit{nums}[0]]=\text{true}$。

对于i>0 且 j>0 的情况，需要分别考虑以下两种情况。

如果$ j \ge \textit{nums}[i]$，则对于当前的数字$ \textit{nums}[i]$，可以选取也可以不选取，两种情况只要有一个为 true，就有 $\textit{dp}[i][j]=\text{true}$。

* 如果不选取 $\textit{nums}[i]$，则 $\textit{dp}[i][j]=\textit{dp}[i-1][j]$；
* 如果选取$ \textit{nums}[i]$，则 $\textit{dp}[i][j]=\textit{dp}[i-1][j-\textit{nums}[i]]$。

如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字nums[i]，因此有 $\textit{dp}[i][j]=\textit{dp}[i-1][j]$。

最终得到 $\textit{dp}[n-1][\textit{target}]$即为答案。

## 题解

转换成01背包并用动态规划求解

```java
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        if (sum % 2 != 0) {
            return false;
        }

        int half = sum / 2;
        if (max > half) {
            return false;
        }

        // 此时转换为01背包问题，要判断数组范围内是否有子集的和等于half
        // 可以使用动态规划问题解决
        // 用dp[i][j]表示数组0~i范围内是否有和为j的子集
        boolean [][] dp = new boolean[nums.length][half + 1];

        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
            dp[i][nums[i]] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < half + 1; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[nums.length - 1][half];

    }
}
```

