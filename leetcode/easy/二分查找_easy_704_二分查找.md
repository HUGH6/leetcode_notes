## 题目

难度简单340收藏分享切换为英文接收动态反馈

给定一个 `n` 个元素有序的（升序）整型数组 `nums` 和一个目标值 `target` ，写一个函数搜索 `nums` 中的 `target`，如果目标值存在返回下标，否则返回 `-1`。


**示例 1:**

```
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
```

**示例 2:**

```
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
```

 

**提示：**

1. 你可以假设 `nums` 中的所有元素是不重复的。
2. `n` 将在 `[1, 10000]`之间。
3. `nums` 的每个元素都将在 `[-9999, 9999]`之间。

**链接：**

https://leetcode-cn.com/problems/binary-search/

## 思路

基本解法：

* 实现普通的二分搜索

## 题解

基本解法：


    class Solution {
        public int search(int[] nums, int target) {
            return binarySearch(nums, 0, nums.length - 1, target);
    
        }
    
        private int binarySearch(int [] nums, int left, int right, int target) {
            if (left > right) {
                return -1;
            }
    
            int middle = (left + right) / 2;
    
            int middleNum = nums[middle];
            if (middleNum == target) {
                return middle;
            } else if (middleNum < target) {
                return binarySearch(nums, middle + 1, right, target);
            } else {
                return binarySearch(nums, left, middle - 1, target);
            }
        }
    }