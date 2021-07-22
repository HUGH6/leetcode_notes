## 题目

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

示例1:

> 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
> 输出: 6
> 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。


提示：

> 1 <= arr.length <= 10^5
> -100 <= arr[i] <= 100

**链接：**

https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof

## 思路

采用动态规划解法，用dp[i]数组表示“以num[i]结尾的子数组的最大和”，则dp[i]有两种取值，当dp[i-1]>0时，dp[i] = dp[i-1]+nums[i]，否则，以nums[i]单个元素为一个子数组，dp[i]=nums[i]。从前往后逐步计算dp，过程中记录下最大的dp值即可。

## 题解

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int [] dp = new int[nums.length];
        int maxSubArraySum = 0;

        dp[0] = nums[0];
        maxSubArraySum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = (dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i]);

            if (dp[i] > maxSubArraySum) {
                maxSubArraySum = dp[i];
            }
        }

        return maxSubArraySum;
    }
}
```

