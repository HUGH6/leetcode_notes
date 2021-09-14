## 题目

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

> 输入: [2,2,1]
> 输出: 1

示例 2:

> 输入: [4,1,2,1,2]
>
> 输出: 4



**链接：**

https://leetcode-cn.com/problems/single-number

## 思路

**异或运算消除重复数字**

1. 交换律：a ^ b ^ c == a ^ c ^ b

1. 任何数于0异或为任何数 0 ^ n == n
2. 相同的数异或为0: n ^ n == 0

因此，遍历数组，将所有数都异或起来，相同的数会被异或消除为0，最后剩下的单个的数就是要找的结果

## 题解

异或消除


    class Solution {
        public int singleNumber(int[] nums) {
            int ans = nums[0];
            for (int i = 1; i < nums.length; i++) {
                ans ^= nums[i];
            }
    
            return ans;
        }
    }

