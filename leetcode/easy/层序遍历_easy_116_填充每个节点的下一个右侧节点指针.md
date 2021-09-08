## 题目

给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

> struct Node {
>   int val;
>   Node *left;
>   Node *right;
>   Node *next;
> }

填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

 

进阶：

* 你只能使用常量级额外空间。
* 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。



示例：

![](https://assets.leetcode.com/uploads/2019/02/14/116_sample.png)

> 输入：root = [1,2,3,4,5,6,7]
> 输出：[1,#,2,3,#,4,5,6,7,#]
> 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。


提示：

* 树中节点的数量少于 4096
* -1000 <= node.val <= 1000



**链接：**

https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node

## 思路

思路1：

层序遍历，每一层逐层访问，对于每一层，只需将前一个node的next设置为下一个node即可。

时间：O（N）

空间：O（N）



思路2：

可以借助node的next指针：当上一层的节点都以next指针进行连接时，那么，直接通过next指针即可进行层序遍历，而无需借助队列结构，因此可以降低空间复杂度。

时间：O（N）

空间：O（1）

## 题解

思路1：队列


    /*
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    */
    
    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            
            Queue<Node> queue = new LinkedList<>();
            int levelCount = 0;
            queue.offer(root);
            int sum = 1;
    
            while (!queue.isEmpty()) {
                Node pre = null;
                levelCount = sum;
                
                sum = 0;
                for (int i = 0; i < levelCount; i++) {
                    Node node = queue.poll();
    
                    if (node.left != null) {
                        queue.offer(node.left);
                        sum++;
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                        sum++;
                    }
    
                    if (pre != null) {
                        pre.next = node;
                    }
                    pre = node;
                }
            }
            
            return root;
        }
    }

思路2：next指针

```
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        

        Node rowHead = root;
        Node curNode = null;
        while (rowHead != null) {
            curNode = rowHead;
            Node pre = null;
            Node curRowHead = null;
            while (curNode != null) {
                if (curNode.left != null) {
                    if (pre == null) {
                        curRowHead = curNode.left;
                    } else {
                        pre.next = curNode.left;
                    }
                    pre = curNode.left;
                }
                if (curNode.right != null) {
                    if (pre == null) {
                        curRowHead = curNode.right;
                    } else {
                        pre.next = curNode.right;
                    }
                    pre = curNode.right;
                }

                curNode = curNode.next;
            }
            rowHead = curRowHead;
        }

        return root;
    }
}
```

