## 题目

统计一个数字在排序数组中出现的次数。

示例 1:

> 输入: nums = [5,7,7,8,8,10], target = 8
> 输出: 2


示例 2:

> 输入: nums = [5,7,7,8,8,10], target = 6
> 输出: 0


限制：

> 0 <= 数组长度 <= 50000

**链接：**

https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof

## 思路

修改后的二分查找法。二分查找可以用于快速定位有序排列的数组中的元素，但有可能存在多个目标target，因此，即使找到里target，也要继续二分在左右子数组中搜索。为了加快搜索速度，可以在子数组中判断表头或表为是否与target相等，若相等，那么其他target必然相连，直接遍历即可统计子数组中所有target的数量，而无需再递归调用。

## 题解

```java
class Solution {
    public int search(int[] nums, int target) {
        return bisearch(nums, target, 0, nums.length - 1);
    }

    private int bisearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return 0;
        }

        int sum = 0;
        if (nums[left] == target) {
            for (int index = left; index <= right; index++) {
                if (nums[index] == target) {
                    sum++;
                } else {
                    break;
                }
            }
        } else if (nums[right] == target) {
            for (int index = right; index >= left; index--) {
                if (nums[index] == target) {
                    sum++;
                } else {
                    break;
                }
            }
        }

        if (sum != 0) {
            return sum;
        }
        
        int middle = (left + right) / 2;

        if (nums[middle] == target) {
            sum += 1;
            sum += bisearch(nums, target, left, middle - 1);
            sum += bisearch(nums, target, middle + 1, right);
        } else if (nums[middle] < target) {
            sum += bisearch(nums, target, middle + 1, right);
        } else {
            sum += bisearch(nums, target, left, middle - 1);
        }

        return sum;
    }
}
```

