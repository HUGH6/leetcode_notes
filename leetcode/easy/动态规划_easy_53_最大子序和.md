## 题目

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 

示例 1：

> 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
> 输出：6
> 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

示例 2：

> 输入：nums = [1]
> 输出：1

示例 3：

> 输入：nums = [0]
> 输出：0

示例 4：

> 输入：nums = [-1]
> 输出：-1

示例 5：

> 输入：nums = [-100000]
> 输出：-100000


提示：

* 1 <= nums.length <= 3 * 104
* -105 <= nums[i] <= 105

**链接：**

https://leetcode-cn.com/problems/maximum-subarray

## 思路

**动态规划**

时间：O（n）

空间：O（n）

重点是：使用dp[i]表示以i结尾的子序和，那么递推公式则为

*f*(*i*)=max{*f*(*i*−1)+*nums*[*i*],*nums*[*i*]}

**分治**

复杂度时间：假设把递归的过程看作是一颗二叉树的先序遍历，那么这颗二叉树的深度的渐进上界为 O(logn)，这里的总时间相当于遍历这颗二叉树的所有节点，故总时间的渐进上界是 $O(\sum_{i=1}^{\log n} 2^{i-1})=O(n)$，故渐进时间复杂度为 O(n)。

空间复杂度：递归会使用 $O(\log n)$的栈空间，故渐进空间复杂度为 $O(\log n)$。



这个分治方法类似于「线段树求解最长公共上升子序列问题」的 pushUp 操作。 

定义一个操作 get(a, l, r) 表示查询 a 序列 [l,r] 区间内的最大子段和，那么我们最终要求的答案就是 get(nums, 0, nums.size() - 1)。

如何分治实现这个操作？

对于一个区间 [l,r]，取 m=⌊ l+r⌋/2，对区间 [l,m] 和 [m+1,r] 分治求解。

当递归逐层深入直到区间长度缩小为 1 的时候，递归「开始回升」。

考虑如何通过 [l,m] 区间的信息和 [m+1,r] 区间的信息合并成区间 [l,r] 的信息。

最关键的两个问题是：

* 要维护区间的哪些信息呢？
* 如何合并这些信息呢？

对于一个区间 [l,r]，可以维护四个量：

* lSum 表示 [l,r] 内以 l 为左端点的最大子段和
* rSum 表示 [l,r] 内以 r 为右端点的最大子段和
* mSum 表示 [l,r] 内的最大子段和
* iSum 表示 [l,r] 的区间和

以下简称 [l,m] 为 [l,r] 的「左子区间」，[m+1,r] 为 [l,r] 的「右子区间」。

考虑如何维护这些量（如何通过左右子区间的信息合并得到 [l,r] 的信息）？

* 对于长度为 1 的区间 [i,i]，四个量的值都和nums[i] 相等。

* 对于长度大于 1 的区间：

  * iSum：区间 [l,r] 的 iSum 就等于「左子区间」的 iSum 加上「右子区间」的 iSum。
  * lSum：对于 [l,r] 的lSum，存在两种可能：
  
    * 要么等于「左子区间」的 lSum
  
    * 要么等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
  * rSum：对于 [l,r] 的 rSum，同理，
    * 要么等于「右子区间」的 rSum，
    * 要么等于「右子区间」的 iSum 加上「左子区间」的 rSum，二者取大。
  * mSum ：可以考虑 [l,r] 的 mSum 对应的区间是否跨越 m
      * 它可能不跨越 m，也就是说 [l,r] 的 mSum 可能是「左子区间」的 mSum 和 「右子区间」的 mSum 中的一个；
      * 它也可能跨越 m，可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。
      * 三者取大。

这样问题就得到了解决。

## 题解

动态规划


    class Solution {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            int [] dp = new int[len];
            dp[0] = nums[0];
            int max = dp[0];
    
            for (int i = 1; i < len; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                if (dp[i] > max) {
                    max = dp[i];
                }
            }
            return max;
        }
    }


​    

**分治**

```
class Solution {
    public static class Info {
        public int lsum;
        public int rsum;
        public int msum;
        public int isum;
        public Info(int l, int r, int m, int i) {
            this.lsum = l;
            this.rsum = r;
            this.msum = m;
            this.isum = i;
        }
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).msum;
    }

    public Info getInfo(int[] nums, int left, int right) {
        if (left == right) {
            return new Info(nums[left],nums[left],nums[left],nums[left]);
        }

        int middle = (left + right) >> 1;
        Info leftInfo = getInfo(nums, left, middle);
        Info rightInfo = getInfo(nums, middle + 1, right);

        return pushUp(leftInfo, rightInfo);
    }

    public Info pushUp(Info left, Info right) {
        int lsum = Math.max(left.lsum, left.isum + right.lsum);
        int rsum = Math.max(right.rsum, right.isum + left.rsum);
        int isum = left.isum + right.isum;
        int msum = Math.max(left.rsum + right.lsum, Math.max(left.msum , right.msum));
        return new Info(lsum, rsum, msum, isum);
    }
}
```

