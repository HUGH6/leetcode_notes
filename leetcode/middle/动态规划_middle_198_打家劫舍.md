## 题目

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 

示例 1：

> 输入：[1,2,3,1]
> 输出：4
> 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
>      偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：

> 输入：[2,7,9,3,1]
> 输出：12
> 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
>      偷窃到的最高金额 = 2 + 9 + 1 = 12 。


提示：

* 1 <= nums.length <= 100
* 0 <= nums[i] <= 400

**链接：**

https://leetcode-cn.com/problems/house-robber

## 思路

动态规划

由于不能连续偷相邻的两家，因此小偷偷东西的间隔最小为1（即中间必须隔1户人家）。

用dp[]数组，表示当偷第i户人家时能获得的最大金额。

那如何表示一夜最多能获得多少金额：比较dp[n]和dp[n - 1]即可，因为要使得偷的收益最大化，最终，肯定会偷这最后两家之一。（为什么？如果最后不偷最后两家，而是偷了倒数第三家，那么，按照规则，此时不是最大收益，因为还能偷最后一家，如果最后偷得是前面的人家，也是类似道理）。

如何求dp[i]，即偷了第i户人家能获得的最大金额：根据规则，要偷第i家，那么就不能偷第i-1家，那么小偷偷的前一家就只能是1~i-1中的一家。

但由于dp[k] （1 <= k <= i - 1）= 第k家金额 + 前面能偷到的最大金额，因此，当偷第i家时，要求得前面能偷到的最大金额，并不需要遍历前面所有的dp[1]~dp[i-1]，因为某一个dp[k]就已经包含了k之前能偷到的最大值。

通过分析，要求dp[i]前能偷的最大值，只需要比较dp[i - 2]和dp[i -3]即可（这两个已经包含了前面所有的可以渠道的最大值，但dp[i]只能选择其中一个）。

## 题解

动态规划

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int [] dp = new int [nums.length];

        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) { 
            if (i - 3 < 0) {
                dp[i] = dp[i - 2] + nums[i];
            } else {
                dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
            }
        }

        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }
}
```

