## 题目

给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。

 

示例 1：

![](https://assets.leetcode.com/uploads/2021/01/18/uniquebstn3.jpg)

> 输入：n = 3
> 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

示例 2：

> 输入：n = 1
> 输出：[[1]]


提示：

* 1 <= n <= 8

**链接：**

https://leetcode-cn.com/problems/unique-binary-search-trees-ii

## 思路

递归

二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，小于右子树所有节点的值，且左子树和右子树也同样为二叉搜索树。因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 n，如果我们枚举根节点的值为 i，那么根据二叉搜索树的性质我们可以知道左子树的节点值的集合为 [1…i−1]，右子树的节点值的集合为[i+1…n]。而左子树和右子树的生成相较于原问题是一个序列长度缩小的子问题，因此我们可以想到用回溯的方法来解决这道题目。

我们定义 generateTrees(start, end) 函数表示当前值的集合为 [start,end]，返回序列 [start,end] 生成的所有可行的二叉搜索树。按照上文的思路，我们考虑枚举 [start,end] 中的值 i 为当前二叉搜索树的根，那么序列划分为了 [start,i−1] 和 [i+1,end] 两部分。我们递归调用这两部分，即 generateTrees(start, i - 1) 和 generateTrees(i + 1, end)，获得所有可行的左子树和可行的右子树，那么最后一步我们只要从可行左子树集合中选一棵，再从可行右子树集合中选一棵拼接到根节点上，并将生成的二叉搜索树放入答案数组即可。

递归的入口即为 generateTrees(1, n)，出口为当 start>end 的时候，当前二叉搜索树为空，返回空节点即可。

## 题解

递归

```java
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

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = visit(1, n);

        return ans;


    }

    private List<TreeNode> visit(int left, int right) {

        if (left > right) {
            return null;
        }

        List<TreeNode> posibleRoots = new ArrayList<>();

        for (int k = left; k <= right; k++) {

            List<TreeNode> leftTrees = visit(left, k - 1);

            List<TreeNode> rightTrees = visit(k + 1, right);

            if (leftTrees != null && rightTrees != null) {
                for (int i = 0; i < leftTrees.size(); i++) {
                    for (int j = 0; j < rightTrees.size(); j++) {
                        TreeNode root = new TreeNode(k);
                        root.left = leftTrees.get(i);
                        root.right = rightTrees.get(j);
                        posibleRoots.add(root);
                    }
                }
            } else if (leftTrees == null && rightTrees != null) {
                for (int j = 0; j < rightTrees.size(); j++) {
                    TreeNode root = new TreeNode(k);
                    root.left = null;
                    root.right = rightTrees.get(j);
                    posibleRoots.add(root);
                }  
            } else if (leftTrees != null && rightTrees == null) {
                for (int i = 0; i < leftTrees.size(); i++) {
                    TreeNode root = new TreeNode(k);
                    root.left = leftTrees.get(i);
                    root.right = null;
                    posibleRoots.add(root);
                }
            } else {
                TreeNode root = new TreeNode(k);
                root.left = null;
                root.right = null;
                posibleRoots.add(root);
            }
        }

        return posibleRoots;
    }
}
```

