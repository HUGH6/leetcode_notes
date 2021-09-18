## 题目

给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。

在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。

返回使每个结点上只有一枚硬币所需的移动次数。

 

示例 1：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree1.png)

> 输入：[3,0,0]
> 输出：2
> 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。

示例 2：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree2.png)

> 输入：[0,3,0]
> 输出：3
> 解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。

示例 3：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree3.png)

> 输入：[1,0,2]
> 输出：2

示例 4：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree4.png)

> 输入：[1,0,0,null,3]
> 输出：4


提示：

* 1<= N <= 100
* 0 <= node.val <= N

**链接：**

https://leetcode-cn.com/problems/distribute-coins-in-binary-tree

## 思路

**分治**

时间：O（n）

空间：O（n）

可以把问题分解成小问题，再讲小问题的结果合并为大问题的结果：

对于单个节点和其左右孩子的移动次数很容易计算，只要使左右孩子都为1，而讲多余的硬币或缺少的硬币都算入这个父节点即可。

而一棵树就是有许多这样的父子节点层层组合而成的。

因此，可以自底向上地逐层计算，当地下一层计算完成时，我们再计算更上一层时，原本底下一层的父节点又变成了上面一层的子节点，因此，我们只需要重复上述单个父子节点组的计算，知道计算到最顶层根节点即可。

由于需要自底向上计算，因此，在实现时使用了栈和队列。

**后续遍历**

时间：O（n）

空间：O（1）

还是采用如上的分治思路，但其实可以直接结合后续遍历，来实现自底向上的计算，会节省大量的时间空间。

## 题解

**分治**


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
        public int distributeCoins(TreeNode root) {
            Map<TreeNode, Integer> map = new HashMap<>();
    
            Queue<TreeNode> queue = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
    
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                stack.push(node);
                map.put(node, node.val);
    
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
    
            int moveCount = 0;
    
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
    
                int nodeCoins = map.get(node);
    
                int leftCoins = node.left == null ? 1 : map.get(node.left);
                int rightCoins = node.right == null ? 1 : map.get(node.right);
    
                int leftMove = Math.abs(leftCoins - 1);
                int rightMove = Math.abs(rightCoins - 1);
                moveCount = moveCount + leftMove + rightMove;
    
                int nodeCoinsAfterMove = nodeCoins + (leftCoins - 1) + (rightCoins - 1);
                map.put(node, nodeCoinsAfterMove);
                if (node.left != null) {
                    map.put(node.left, 1);
                } 
                if (node.right != null) {
                    map.put(node.right, 1);
                }
            }
    
            return moveCount;
        }
    }


​    

**后序遍历**

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
    int ans = 0;
    public int distributeCoins(TreeNode root) {
        lrd(root);
        return ans;
    }

    public int lrd(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left != null) {
            node.val += lrd(node.left);
        }
        if (node.right != null) {
            node.val += lrd(node.right);
        }

        ans += Math.abs(node.val - 1);
        return node.val - 1;
    }
}
```

