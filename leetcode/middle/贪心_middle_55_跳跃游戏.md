## 题目

给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。

 

示例 1：

> 输入：nums = [2,3,1,1,4]
> 输出：true
> 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

示例 2：

> 输入：nums = [3,2,1,0,4]
> 输出：false
> 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。


提示：

* 1 <= nums.length <= 3 * 104
* 0 <= nums[i] <= 105

**链接：**

https://leetcode-cn.com/problems/jump-game

## 思路

**贪心**

当dp[i]可达时，那么dp[i]~dp[i+nums[i]]这段区间也可达。

只需遍历数组，在访问每一位时，每次更新最远的可达距离，最后即可进行判断。



**动态规划**

dp[i]表示i位置能否到达，dp[0]一定能到达，为true。

从dp[1]开始，向前搜索，对于每一个dp[i]之前的dp[j]，判断dp[j] == true && j + nums[j] >= i，则当前dp[i]也可以确定为true。

最后查看dp[nums.length - 1]是否为true即可。

问题：

每次都要前向搜索，太费时间，这里可以优化：当确定dp[i]为true时，那么dp[i]~dp[i + nums[i]]这个范围内的位置都是可以到达的，即，直接就能确定了，而不需要每次都要前向搜索才能判断。

## 题解

贪心

```java
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        int index = 0;
        max = nums[0];

        while (index <= max && index < nums.length) {
            max = Math.max(max, index + nums[index]);
            index++;
        }

        return max >= nums.length - 1;
    }
}
```

动态规划

```java
class Solution {
    public boolean canJump(int[] nums) {
        boolean [] dp = new boolean[nums.length];

        dp[0] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >=0; j--) {
                if (dp[j] && j + nums[j] - i >= 0) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[nums.length - 1];
    }
}
```