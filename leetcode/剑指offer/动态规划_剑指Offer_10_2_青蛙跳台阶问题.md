## 题目

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

> 输入：n = 2
> 输出：2

示例 2：

> 输入：n = 7
> 输出：21

示例 3：

> 输入：n = 0
> 输出：1

提示：

0 <= n <= 100

**链接：**

https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof

## 思路

正常按动态规划思路求解。

其中需要注意的是求余数操作：

循环求余法：

> 大数越界： 随着 n 增大, f(n) 会超过 Int32 甚至 Int64 的取值范围，导致最终的返回值错误。

求余运算规则： 

设正整数 x, y, p 求余符号为 $\odot$ ，则有$ (x + y) \odot p = (x \odot p + y \odot p) \odot p$ 。

解析： 根据以上规则，可推出 $f(n) \odot p = [f(n-1) \odot p + f(n-2) \odot p] \odot p$，从而可以在循环过程中每次计算 $sum = (a + b) \odot 1000000007$ ，此操作与最终返回前取余等价。

## 题解

```java
class Solution {
    public int numWays(int n) {
        if (n < 3) {
            if (n == 0) {
                return 1;
            } else {
                return n;
            }
        }

        long [] dp = new long [n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return (int)dp[n];
    }
}
```

