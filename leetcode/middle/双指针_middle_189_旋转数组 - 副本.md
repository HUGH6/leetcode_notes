## 题目

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

 

进阶：

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？


示例 1:

> 输入: nums = [1,2,3,4,5,6,7], k = 3
> 输出: [5,6,7,1,2,3,4]
> 解释:
> 向右旋转 1 步: [7,1,2,3,4,5,6]
> 向右旋转 2 步: [6,7,1,2,3,4,5]
> 向右旋转 3 步: [5,6,7,1,2,3,4]

示例 2:

> 输入：nums = [-1,-100,3,99], k = 2
> 输出：[3,99,-1,-100]
> 解释: 
> 向右旋转 1 步: [99,-1,-100,3]
> 向右旋转 2 步: [3,99,-1,-100]


提示：

* 1 <= nums.length <= 2 * 104
* -231 <= nums[i] <= 231 - 1
* 0 <= k <= 105



**链接：**

https://leetcode-cn.com/problems/rotate-array

## 思路

环状替换

## 题解

```
class Solution {
    public void rotate(int[] nums, int k) {
        int [] ans = new int [nums.length];
        for (int i = 0; i < nums.length; i++) {
            int newIndex = (i + k) % nums.length;
            ans[newIndex] = nums[i];
        }

        System.arraycopy(ans, 0, nums, 0, nums.length);
    }
}
```



```java
class Solution {
    public void rotate(int[] nums, int k) {
        int start = 0;
        int index = 0;
        int indexNum = nums[index];
        int newIndex = 0;
        int temp = 0;

        int count = nums.length;
        while (count > 0) {
            index = start;
            indexNum = nums[index];

            do {
                newIndex = (index + k) % nums.length;
                temp = nums[newIndex];
                nums[newIndex] = indexNum;

                index = newIndex;
                indexNum = temp;
                count--;
            } while (newIndex != start);

            start++;
        }
    }
}
```