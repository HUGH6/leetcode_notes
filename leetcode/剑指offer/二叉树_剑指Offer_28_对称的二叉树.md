## 题目

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    	1
       / \
      2   2
     / \ / \
    3  4 4  3

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    	1
       / \
      2   2
       \   \
       3    3


示例 1：

> 输入：root = [1,2,2,3,4,4,3]
> 输出：true

示例 2：

> 输入：root = [1,2,2,null,3,null,3]
> 输出：false

**链接：**

https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof

## 思路

对称二叉树定义： 对于树中 任意两个对称节点 L 和 R ，一定有：

* L.val = R.val：即此两对称节点值相等。

* L.left.val = R.right.val：即 L 的 左子节点 和 R 的 右子节点 对称；

* L.right.val = R.left.val：即 L 的 右子节点 和 R 的 左子节点 对称。

  根据以上规律，考虑从顶至底递归，判断每对节点是否对称，从而判断树是否为对称二叉树。



## 题解

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }

    public boolean recur(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }

        if (l == null || r == null || l.val != r.val) {
            return false;
        }

        return recur(l.left, r.right) && recur(l.right, r.left);
    }
}
```

