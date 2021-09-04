## 题目

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

> F(0) = 0,   F(1) = 1
> F(N) = F(N - 1) + F(N - 2), 其中 N > 1.

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

示例 1：

> 输入：n = 2
> 输出：1

示例 2：

> 输入：n = 5
> 输出：5


提示：

* 0 <= n <= 100



**链接：**

https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof

## 思路

直接循环求解。

## 题解

```java
class Solution {
    public int fib(int n) {
        int f0 = 0, f1 = 1;
        int MD = 1000000007;
        if (n < 2) {
            return n;
        }

        int k = 2;
        int fk = 0;
        while (k <= n) {
            fk = (f0 + f1) % MD;
            f0 = f1;
            f1 = fk;

            k++;
        }

        return fk;
    }
}
```

