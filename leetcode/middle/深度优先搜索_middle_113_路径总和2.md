## 题目

给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。

 

示例 1：

![](https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg)

输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]

示例 2：

![](https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg)

输入：root = [1,2,3], targetSum = 5
输出：[]

示例 3：

输入：root = [1,2], targetSum = 0
输出：[]


提示：

* 树中节点总数在范围 [0, 5000] 内
* -1000 <= Node.val <= 1000
* -1000 <= targetSum <= 1000

**链接：**

https://leetcode-cn.com/problems/path-sum-ii

## 思路

广度优先遍历

## 题解



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
    private Stack<Integer> stack = new Stack<>();
    private int pathSum = 0;
    private int target;

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return result;
        }

        target = targetSum;
        dfs(root);

        return result;
    }

    public void dfs(TreeNode node) {
        stack.push(node.val);
        pathSum += node.val;

        if (node.left == null && node.right == null && pathSum == target) {
            List<Integer> path = new ArrayList<>(stack);
            result.add(path);
        }

        if (node.left != null) {
            dfs(node.left);
        }

        if (node.right != null) {
            dfs(node.right);
        }

        stack.pop();
        pathSum -= node.val;
        return;
    }
}
```

