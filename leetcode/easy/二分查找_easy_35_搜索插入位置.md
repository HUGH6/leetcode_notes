## 题目

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。

 

示例 1:

> 输入: nums = [1,3,5,6], target = 5
> 输出: 2

示例 2:

> 输入: nums = [1,3,5,6], target = 2
> 输出: 1

示例 3:

> 输入: nums = [1,3,5,6], target = 7
> 输出: 4

示例 4:

> 输入: nums = [1,3,5,6], target = 0
> 输出: 0

示例 5:

> 输入: nums = [1], target = 0
> 输出: 0

提示:

1. 1 <= nums.length <= 104
2. -104 <= nums[i] <= 104
3. nums 为无重复元素的升序排列数组
4. -104 <= target <= 104

**链接：**

https://leetcode-cn.com/problems/search-insert-position

## 思路

基本解法：

* 实现二分搜索进行实现
* 直接搜索对应数字的位置在哪里
* 本题的关键在于，有可能找不到对应的元素：即当left==right时，此时有可能还不是目标数字
* 此时就需要停止迭代，针对left==right时的情形判断返回结果
  * 如果此时的数就是target，那么直接返回该数下标
  * 如果此时的数大于target，那么说明target应该插入到这个数之前，即，应该顶替该数的位置，则返回left
  * 如果此时的数小于target，说明target应该插入到这个数之后，即返回left+1

## 题解


    class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
    
            while (left < right) {
                int middle = (left + right) / 2;
                if (nums[middle] == target) {
                    return middle;
                } else if (nums[middle] < target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
    
            if (nums[left] < target) {
                return left + 1;
            } else if (nums[left] > target) {
                return left;
            } else {
                return left;
            }
    
        }
    }