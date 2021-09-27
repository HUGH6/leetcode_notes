## 题目

给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

* s = s1 + s2 + ... + sn
* t = t1 + t2 + ... + tm
* |n - m| <= 1
* 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...

提示：a + b 意味着字符串 a 和 b 连接。

 

示例 1：

> 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
> 输出：true

示例 2：

> 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
> 输出：false

示例 3：

> 输入：s1 = "", s2 = "", s3 = ""
> 输出：true


提示：

* 0 <= s1.length, s2.length <= 100
* 0 <= s3.length <= 200
* s1、s2、和 s3 都由小写英文字母组成

**链接：**

https://leetcode-cn.com/problems/interleaving-string

## 思路

**动态规划**

方法一：动态规划
记 $|s_1|=n,|s_2|=m$

思路与算法

双指针法错在哪里？

使用指针 p1指向s1 的头部，指针p2 一开始指向s2的头部，指针p3指向s3的头部，每次观察p1和p2指向的元素哪一个和p3指向的元素相等，相等则匹配并后移指针。

样例就是一个很好的反例，用这种方法判断 $s_1 = {\rm aabcc}，s_2 = {\rm dbbca}, s_3 = {\rm aadbbcbcac}$时，得到的结果是False，实际应该是True。

解决这个问题的正确方法是动态规划。 

首先如果 

$|s_1| + |s_2| \neq |s_3|$，那 $s_3$必然不可能由$s_1$和 $s_2$交错组成。

在 $|s_1| + |s_2| = |s_3|$时，可以用动态规划来求解：

定义 $f(i, j)$表示s1的前i个元素和s2的前j个元素是否能交错组成s3的前i+j个元素。

如果s1的第i个元素和s3的第i+j个元素相等，那么s1的前i个元素和s2 的前 j 个元素是否能交错组成s3的前i+j个元素取决于s1的前i−1 元素和s2的前j个元素是否能交错组成s3的前i+j−1个元素，即此时$ f(i, j)$取决于 $f(i−1,j)。

同理，可得对称情况的递推公式，于是可以推导出这样的动态规划转移方程：

$f(i, j) = [f(i - 1, j) \, {\rm and} \, s_1(i - 1) = s_3(p)] \, {\rm or} \, [f(i, j - 1) \, {\rm and} \, s_2(j - 1) = s_3(p)]$
边界条件为 $f(0, 0) = {\rm True}$。

## 题解

动态规划

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }

        boolean [][] dp = new boolean[len1 + 1][len2 + 1];

        // 如果len1 + len2 != len3，则肯定组不成
        // f(i,j) 表示s1前i位和s2前j位能否组成s3的前i + j位
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || s1.charAt(i - 1) == s3.charAt(p) && dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || s2.charAt(j - 1) == s3.charAt(p) && dp[i][j - 1];
                }
            }
        }

        return dp[len1][len2];
    }
}
```

