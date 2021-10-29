## 题目

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

     	 3
    	/ \
       4   5
      / \
     1   2

给定的树 B：

```
       4
      / \
     1   2
```

返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

示例 1：

> 输入：A = [1,2,3], B = [3,1]
> 输出：false

示例 2：

> 输入：A = [3,4,5,1,2], B = [4,1]
> 输出：true

限制：
* 0 <= 节点个数 <= 10000

**链接：**

https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof

## 思路

先遍历二叉树找到子结构的根节点可能的位置（多个），然后从此处进行逐个节点的比对。

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
    private List<TreeNode> ans = new ArrayList<>();
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }

        searchRoot(A, B);
        if (ans.isEmpty()) {
            return false;
        } else {
            for (TreeNode r : ans) {
                if (isMathch(r, B)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isMathch(TreeNode node1, TreeNode node2) {
        if (node2 == null) {
            return true;
        }

        if (node1 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        }

        return isMathch(node1.left, node2.left) && isMathch(node1.right, node2.right);
    }

    public void searchRoot(TreeNode root, TreeNode target) {
        if (root == null) {
            return;
        }

        if (root.val == target.val) {
            ans.add(root);
        }

        searchRoot(root.left, target);
        searchRoot(root.right, target);
    }
}
```

