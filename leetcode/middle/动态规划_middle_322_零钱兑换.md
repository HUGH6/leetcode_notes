## 题目

给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。

 

示例 1：

> 输入：coins = [1, 2, 5], amount = 11
> 输出：3 
> 解释：11 = 5 + 5 + 1

示例 2：

> 输入：coins = [2], amount = 3
> 输出：-1

示例 3：

> 输入：coins = [1], amount = 0
> 输出：0

示例 4：

> 输入：coins = [1], amount = 1
> 输出：1

示例 5：

> 输入：coins = [1], amount = 2
> 输出：2


提示：

* 1 <= coins.length <= 12
* 1 <= coins[i] <= 2^31 - 1
* 0 <= amount <= 104

链接

https://leetcode-cn.com/problems/coin-change

## 思路

**记忆化搜索**

空间复杂度：$O(S×n)$

时间复杂度：$O(S)$

首先，我们定义：

F(S)：组成金额 SS 所需的最少硬币数量

$[c_{0}\ldots c_{n-1}]$：可选的 n 枚硬币面额值

这个问题有一个最优的子结构性质，这是解决动态规划问题的关键。最优解可以从其子问题的最优解构造出来。

如何将问题分解成子问题：

假设我们知道 F(S) ，即组成金额 S 最少的硬币数，最后一枚硬币的面值是 C。那么由于问题的最优子结构，转移方程应为：

$F(S) = F(S - C) + 1$

但我们不知道最后一枚硬币的面值是多少，所以我们需要枚举每个硬币面额值 $c_0, c_1, c_2 \ldots c_{n -1}$,并选择其中的最小值。

下列递推关系成立：

$F(S) = \min_{i=0 ... n-1}{ F(S - c_i) } + 1 \ \text{subject to} \ \ S-c_i \geq 0$

$F(S) = 0 \ , \text{when} \ S = 0$

$F(S) = -1 \ , \text{when} \ n = 0$

![](https://pic.leetcode-cn.com/e0fd2252775b89649ceb6e867ff0e546ec77621edb566693482c8588a98066b8-file_1583404923188)

在上面的递归树中，可以看到许多子问题被多次计算。为了避免重复的计算，将每个子问题的答案存在一个数组中进行记忆化，如果下次还要计算这个问题的值直接从数组中取出返回即可，这样能保证每个子问题最多只被计算一次。

**动态规划**

空间复杂度：$O(S×n)$

时间复杂度：$O(S)$

采用自下而上的方式进行思考。

仍定义 F(i) 为组成金额 i 所需最少的硬币数量，假设在计算 F(i) 之前，已经计算出 F(0)−F(i−1) 的答案。 则 F(i) 对应的转移方程应为：

$F(i)=\min_{j=0 \ldots n-1}{F(i -c_j)} + 1$



## 题解

记忆化搜索

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        return getCoinNum(coins, amount, new int[amount]);
    }

    public int getCoinNum(int[] coins, int rem, int [] count) {
        if (rem < 0) {
            return -1;
        }

        if (rem == 0) {
            return 0;
        }

        // 如果rem需要的硬币数已经计算过，则从记录中直接读取
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }

        // 如果rem需要的硬币数没有计算，则需要计算
        // f(rem) = f(rem - C) + 1
        // 由于不知道最后一个硬币的面值，因此，需要遍历搜索所有面值的硬币，取最小值
        int min = Integer.MAX_VALUE;
        for (int c : coins) {
            int ret = getCoinNum(coins, rem - c, count);
            if (ret >= 0 && ret < min) {
                min = ret;
            }
        }

        if (min == Integer.MAX_VALUE) {
            count[rem - 1] = -1;
        } else {
            count[rem - 1] = min + 1;
        }

        return count[rem - 1];
    }
}
```

动态规划

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        int [] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 10);
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int c = 0; c < coins.length; c++) {
                if (i >= coins[c]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[c]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

