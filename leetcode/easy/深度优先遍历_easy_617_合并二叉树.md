## 题目

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

> 输入: 
> 	Tree 1                     Tree 2                  
>           1                         2                             
>          / \                       / \                            
>         3   2                     1   3                        
>        /                           \   \                      
>       5                             4   7                  
> 输出: 
> 合并后的树:
> 	     3
> 	    / \
> 	   4   5
> 	  / \   \ 
> 	 5   4   7

注意: 合并必须从两个树的根节点开始。

**链接：**

https://leetcode-cn.com/problems/merge-two-binary-trees

## 思路

深度优先遍历，同步遍历两棵树相同位置的节点，进行合并。

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
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            TreeNode root = new TreeNode();
            visit(root, root1, root2, 0);
            return root.left;
        }
    
        private void visit(TreeNode father, TreeNode node1, TreeNode node2, int position) {
            if (father == null) {
                return;
            }
    
            TreeNode node = null;
            if (node1 != null && node2 != null) {
                node = new TreeNode(node1.val + node2.val, null, null);
            } else if (node1 == null && node2 != null) {
                node = new TreeNode(node2.val);
            } else if (node2 == null && node1 != null) {
                node = new TreeNode(node1.val);
            }
    
            if (position == 0) {
                father.left = node;
            } else {
                father.right = node;
            }
    
            TreeNode n1l = node1 == null ? null : node1.left;
            TreeNode n1r = node1 == null ? null : node1.right;
            TreeNode n2l = node2 == null ? null : node2.left;
            TreeNode n2r = node2 == null ? null : node2.right;
            visit(node, n1l, n2l, 0);
            visit(node, n1r, n2r, 1);
        }
    }