## 题目

给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

 

示例 1：

> 输入：nums = [-4,-1,0,3,10]
> 输出：[0,1,9,16,100]
> 解释：平方后，数组变为 [16,1,0,9,100]
> 排序后，数组变为 [0,1,9,16,100]

示例 2：

> 输入：nums = [-7,-3,2,3,11]
> 输出：[4,9,9,49,121]


提示：

* 1 <= nums.length <= 10^4
* -10^4 <= nums[i] <= 10^4
* nums 已按 非递减顺序 排序


进阶：

请你设计时间复杂度为 O(n) 的算法解决本问题

**链接：**

https://leetcode-cn.com/problems/squares-of-a-sorted-array

## 思路

基础解法：

* 遍历一次数组，将每个数的平方记录到新数组中O(n)
* 对新数组进行一次排序O（logn）



进阶解法：

* 使用双指针left和right，分别从数组左右同时开始遍历
* 每次先比较left和right位置的数的绝对值大小
* 将较大的数放入新数组中，从新数组的右侧从左依次放置
* 没放置一个数相应移动双指针中的一个，直到指针相遇

## 题解

基础

```
class Solution {
    public int[] sortedSquares(int[] nums) {
        int [] ans = new int[nums.length];
         for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i] * nums[i];
        }

        Arrays.sort(ans);

        return ans;
    }
}
```




    class Solution {
        public int[] sortedSquares(int[] nums) {
            int [] ans = new int[nums.length];
            int left = 0, right = nums.length - 1;
            int index = right;
    
            while (left <= right) {
                int leftAbs = Math.abs(nums[left]);
                int rightAbs = Math.abs(nums[right]);
                if (leftAbs > rightAbs) {
                    ans[index] = leftAbs * leftAbs;
                    left++;
                } else if (leftAbs < rightAbs) {
                    ans[index] = rightAbs * rightAbs;
                    right--;
                } else {
                    ans[index] = leftAbs * leftAbs;
                    left++;
                }
                index--;
            }
    
            return ans;
        }
    }