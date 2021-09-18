## 题目

给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

 

示例 1：

![](https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg)

> 输入：root = [3,1,4,null,2], k = 1
> 输出：1

示例 2：

![](https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg)

> 输入：root = [5,3,6,2,4,null,null,1], k = 3
> 输出：3




提示：

* 树中的节点数为 n 。
* 1 <= k <= n <= 104
* 0 <= Node.val <= 104

**链接：**

https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst

## 思路

**递归**

时间：O（n）

空间：O（1）

中序遍历序列就是从小到大的序列，则在遍历过程中找到第k个即可。

而且一旦遍历到第k个节点后，我们就找到了目标节点，因此之后的后续递归可以不用继续，可以直接返回，因此可以进行一定的减枝。

**迭代**

时间：O（n）

空间：O（n）

上述过程可以用迭代的方式实现。

## 题解

**递归**


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
        int count = 0;
        int target = 0;
        int ans = -1;
        public int kthSmallest(TreeNode root, int k) {
            target = k;
            inOrder(root);
            return ans;
        }
    
        public void inOrder(TreeNode node) {
            if (node == null) {
                return;
            }
    
            inOrder(node.left);
    
            count++;
            if (count == target) {
                ans = node.val;
                return;
            }
    
            inOrder(node.right);
        }
    }


​    

**枚举1：单调栈找2和3**

```
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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode n = stack.pop();
            if (--k == 0) {
                return n.val;
            }
            node = n.right;
        }
    }
}
```

