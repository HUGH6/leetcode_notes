## 题目

输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

示例 1:

![](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

> Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
> Output: [3,9,20,null,null,15,7]

示例 2:

> Input: preorder = [-1], inorder = [-1]
> Output: [-1]


限制：

* 0 <= 节点个数 <= 5000



**链接：**

https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof

## 思路

利用中序遍历序列和先序遍历序列的特点。

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        int root = preorder[0];
        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root) {
                index = i;
                break;
            }
        }

        TreeNode rootNode = new TreeNode(root);
        rootNode.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + index), Arrays.copyOfRange(inorder, 0, index));
        rootNode.right = buildTree(1 + index >= preorder.length ? null : Arrays.copyOfRange(preorder, 1 + index, preorder.length), index + 1 >= inorder.length ? null : Arrays.copyOfRange(inorder, index + 1, inorder.length));

        return rootNode;
    }
}
```

