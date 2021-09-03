## 题目

给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。

进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？

 

示例 1：

> 输入：root = [1,3,null,null,2]
> 输出：[3,1,null,null,2]
> 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。

示例 2：

> 输入：root = [3,1,4,null,null,2]
> 输出：[2,1,4,null,null,3]
> 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。


提示：

* 树上节点的数目在范围 [2, 1000] 内
* -231 <= Node.val <= 231 - 1



**链接：**

https://leetcode-cn.com/problems/recover-binary-search-tree

## 思路

普通解法：

空间复杂度O(n)

正常二叉搜索树的先序遍历序列是递增的。因此我们可以直接按照先序遍历得到当前序列，然后与正确的序列对比，看哪个位置的数不正确，则可以确定，该位置的两个序列中的数对应的节点调换了位置。

为了方便调整节点，可以使用一个map，在遍历过程中，将每个节点存入map，以val为key，node为节点，最后只需从map中取出两个节点，交换val值即可。



优化解法：

空间复杂度O（h）

![](C:\Users\胡子涵\AppData\Roaming\Typora\typora-user-images\image-20210903163900973.png)



## 题解

普通解法

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
    Map<Integer, TreeNode> map = new HashMap<>();
    List<Integer> preOrder = new ArrayList<>();

    public void recoverTree(TreeNode root) {
        // 123 321
        // 1234 1324


        preOrderVisit(root);

        List<Integer> correctPreOrder = new ArrayList<>(preOrder);
        Collections.sort(correctPreOrder);

        int length = preOrder.size();
        int [] swapIndex = new int [2];
        for (int i = 0; i < length; i++) {
            int e1 = preOrder.get(i);
            int e2 = correctPreOrder.get(i);

            if (e1 != e2) {
                swapIndex[0] = e1;
                swapIndex[1] = e2;
                break;
            }
        }

        TreeNode n1 = map.get(swapIndex[0]);
        TreeNode n2 = map.get(swapIndex[1]);
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }

    private void preOrderVisit(TreeNode node) {
        if (node == null) {
            return;
        }

        preOrderVisit(node.left);
        preOrder.add(node.val);
        map.put(node.val, node);

        preOrderVisit(node.right);
    }
}
```



优化方法

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
    TreeNode pre = null;
    int count = 0;
    TreeNode [][] det = new TreeNode[2][2];

    public void recoverTree(TreeNode root) {
        visit(root);
        
        TreeNode x = null;
        TreeNode y = null;
        
        if (count == 2) {
        	x = det[0][0];
        	y = det[1][1];
        } else if (count == 1) {
        	x = det[0][0];
        	y = det[0][1];
        }
        
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    private void preOrderVisit(TreeNode node) {
        if (node == null) {
            return;
        }

        visit(node.left);
        
        if (pre != null && pre.val > node.val) {
        	det[count][0] = pre;
        	det[count][1] = node;
        	count++;
        }
        
        pre = node;

        visit(node.right);
    }
}
```

