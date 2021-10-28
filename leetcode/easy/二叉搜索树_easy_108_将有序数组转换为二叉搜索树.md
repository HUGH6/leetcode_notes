## 题目

给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

 

示例 1：

> 输入：nums = [-10,-3,0,5,9]
> 输出：[0,-3,9,-10,null,5]
> 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：

示例 2：

> 输入：nums = [1,3]
> 输出：[3,1]
> 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。


提示：

* 1 <= nums.length <= 104
* -104 <= nums[i] <= 104
* nums 按 严格递增 顺序排列

**链接：**

https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree

## 思路

这种区间分治类的题目，用左闭右闭就好，左闭右开不方便。

另外需要注意的是，求中点不要用 int mid = (l + r)/2，有溢出风险，稳妥的方法是 int mid = l + (r-l)/2。

## 题解


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }
    
        public TreeNode build(int [] nums, int left, int right) {
            if (left > right) {
                return null;
            }
    
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = build(nums, left, mid - 1);
            root.right = build(nums, mid + 1, right);
    
            return root;
        }
    }