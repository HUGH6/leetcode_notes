## 题目

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入

        4
       /   \
      2     7
     / \   / \
    1   3 6   9

镜像输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

**示例 1：**

```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

**限制：**

```
0 <= 节点个数 <= 1000
```

**链接：**

https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/

## 思路

以后续遍历的顺序，先翻转左右子节点的子树，然后翻转该节点本身，以此自底向上地进行翻转。

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
    public TreeNode mirrorTree(TreeNode root) {
        mirror(root);
        return root;
    }

    public void mirror(TreeNode node) {
        if (node == null) {
            return;
        }

        mirror(node.left);
        mirror(node.right);
        
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
}
```

