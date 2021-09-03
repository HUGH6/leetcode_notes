## 题目

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

> 输入: [0,1,0,3,12]
> 输出: [1,3,12,0,0]

说明:

* 必须在原数组上操作，不能拷贝额外的数组。
* 尽量减少操作次数。



**链接：**

https://leetcode-cn.com/problems/move-zeroes

## 思路

双指针：

题目要求要将0全部移到数组右侧，且其他非0数字的相对顺序不变。

可以使用两个指针，一快一慢，slow和fast，都从最左边0下标开始。

slow指示的是左移的非0数字的下一个写入位置。

fast则用于从左向右遍历数组，当fast遇到非0数字时，则该非0数字应该左移，因此，nums[fast]与nums[slow]交换。

由于slow和fast从同一位置开始，因此，就算先遇到非0数字，也只是原地交换，不会打乱与其他非0数字的顺序。

因此，由于slow如果是正数，那么它的位置不变，如果是0，就会被换到后面去。

交换后，slow前进一位，表示下一个正数写入的位置，而fast继续向后搜索。

当fast遍历完数组，所有的正数都被排到了左侧，相应的所有的0就自然会排到右侧。

## 题解

```
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != 0) {
                int tmp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = tmp;

                slow++;
            }
            fast++;
        }
    }
}
```

