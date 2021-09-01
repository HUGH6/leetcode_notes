## 题目

你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。


示例 1：

> 输入：n = 5, bad = 4
> 输出：4
> 解释：
> 调用 isBadVersion(3) -> false 
> 调用 isBadVersion(5) -> true 
> 调用 isBadVersion(4) -> true
> 所以，4 是第一个错误的版本。

示例 2：

> 输入：n = 1, bad = 1
> 输出：1


提示：

* 1 <= bad <= n <= 231 - 1

**链接：**

https://leetcode-cn.com/problems/first-bad-version

## 思路

基本解法：

* 实现二分搜索进行实现
* 根据特性，以第一个错误版本为分界点，左侧的都是正确版本，右侧都是错误版本
* 以版本范围left和right为例，每次判断中间的middle是否是错误版本
* 若中间是正确的，说明第一个错误版本在middle+1到right之间
* 如果中间是错误的，说明第一个错误版本在left和middle之间
* 通过迭代，不停缩小范围，直达找到为止

## 题解

**注意**：注意提示版本的范围，最大有可能达到int类型的最大值，因此，在求解中间版本时，使用（left+right）/ 2可能会导致溢出而造成错误，正确的做法是left + (right - left) / 2。这点是这题的关键。


    /* The isBadVersion API is defined in the parent class VersionControl.
          boolean isBadVersion(int version); */
    
    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 1, right = n;
    
            while (left < right) {
                int middle = left + (right - left) / 2;
                boolean isBad = isBadVersion(middle);
    
                if (!isBad) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
    
            return left;
        }
    }