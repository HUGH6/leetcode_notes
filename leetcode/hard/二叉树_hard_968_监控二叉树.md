## 题目

给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

计算监控树的所有节点所需的最小摄像头数量。

 

示例 1：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/29/bst_cameras_01.png)

输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。

示例 2：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/29/bst_cameras_02.png)

输入：[0,0,null,0,null,0,null,null,0]
输出：2
解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。

提示：

* 给定树的节点数的范围是 [1, 1000]。
* 每个节点的值都是 0。

**链接：**

https://leetcode-cn.com/problems/binary-tree-cameras

## 思路

**后续遍历+分治**

时间：O（n）

空间：O（1）

可以讲问题分解，自底向上地解决子问题。

先从底层开始分配摄像头，然后层层向上逐渐分配。这个自底向上的过程可以用后序遍历实现。

node.val值可以用来作为标识，设置为1，表示该节点可以被摄像头覆盖。

在每个子问题中，可以根据一个节点其孩子的情况和是否有父节点来判断是否需要设置摄像头：

* 如果当前节点有孩子不在监控范围，则应该在这个节点设置摄像头（同时将父节点、孩子都标识为1）
* 如果当前节点没有孩子或孩子已经在覆盖范围，那么可以根据当前节点有无父节点判断：
  * 若有父节点，那应该在父节点设置摄像头
  * 若无父节点，那么当前节点必须设置摄像头

**后序遍历，更简洁**

每个节点有三种状态，只用想清楚这个就行了，可惜比赛的时候没有考虑清楚三种状态



## 题解

**后序遍历+分治**


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
        Map<TreeNode, TreeNode> father = new HashMap<>();
    
        public int minCameraCover(TreeNode root) {
            lrd(root);
            return count;
        }
    
        public void lrd(TreeNode node) {
    
    
            if (node.left != null) {
                father.put(node.left, node);
                lrd(node.left);
            }
            if (node.right != null) {
                father.put(node.right, node);
                lrd(node.right);
            }
    
            if (node.val == 1) {
                if (node.left != null && node.left.val == 0 || node.right != null && node.right.val == 0) {
                    // 
                } else {
                    return;
                }
            }
    
            if (node.left == null && node.right == null) {
                if (father.get(node) != null) {
                    return;
                } else {
                    node.val = 1;
                    count++;
                }
            } else {
                if (node.left != null && node.left.val == 0 || node.right != null && node.right.val == 0) {
                    count++;
                    node.val = 1;
                    if (father.get(node) != null) {
                        father.get(node).val = 1;
                    }
                    if (node.left != null && node.left.val == 0) {
                        node.left.val = 1;
                    }
                    if (node.right != null && node.right.val == 0) {
                        node.right.val = 1;
                    }
                } else {
                    if (father.get(node) != null) {
                        return;
                    } else {
                        node.val = 1;
                        count++;
                    }
                }
            }
        }
    }


​    

**后序遍历简化**

```
class Solution {
    private int ans = 0;
    
    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        if (dfs(root) == 2) ans++;
        return ans;
    }
    
    // 0：该节点安装了监视器 1：该节点可观，但没有安装监视器 2：该节点不可观
    private int dfs(TreeNode node) {
        if (node == null)
            return 1;       
        int left = dfs(node.left), right = dfs(node.right);        
        if (left == 2 || right == 2) {
            ans++;
            return 0;
        } else if (left == 0 || right == 0){            
            return 1;
        } else 
            return 2;
    }
}
```

