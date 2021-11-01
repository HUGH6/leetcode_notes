## 题目

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。

 

示例：

> 输入：nums = [1,2,3,4]
> 输出：[1,3,2,4] 

注：[3,1,2,4] 也是正确的答案之一。


提示：

* 0 <= nums.length <= 50000
* 0 <= nums[i] <= 10000

**链接：**

https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof

## 思路

使用双指针：

odd指针从左往右，表示的是奇数应该存放的位置，每在当前位置存放一个奇数，就向右移动一位。

scan指针是扫描数组的指针，当从左往右扫描过程中遇到奇数，就与odd指针位置的数进行替换。

## 题解

```java
class Solution {
    public int[] exchange(int[] nums) {
        int oddIndex = 0, scanIndex = 0;

        while (scanIndex < nums.length) {
            if (nums[scanIndex] % 2 != 0) {
                swap(nums, oddIndex, scanIndex);
                oddIndex++;
            }
            scanIndex++;
        }

        return nums;
    }

    private void swap(int[] nums, int index1, int index2) {
        int t = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = t;
    }
}
```

