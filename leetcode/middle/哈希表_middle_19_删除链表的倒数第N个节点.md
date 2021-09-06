## 题目

给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

进阶：你能尝试使用一趟扫描实现吗？

 

示例 1：

> 输入：head = [1,2,3,4,5], n = 2
> 输出：[1,2,3,5]

示例 2：

> 输入：head = [1], n = 1
> 输出：[]

示例 3：

> 输入：head = [1,2], n = 1
> 输出：[1]


提示：

* 链表中结点的数目为 sz
* 1 <= sz <= 30
* 0 <= Node.val <= 100
* 1 <= n <= sz



**链接：**

https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list

## 思路

遍历链表，将每个节点的位置i和其引用存入map，最后直接安装n - k计算倒数第k个节点对应的位置即可，直接从map中取出。然后执行相应的删除操作即可。

注意删除表头的特殊情况。

## 题解

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<>();

        ListNode node = head;
        int count = 0;
        while (node != null) {
            map.put(count, node);
            count++;
            node = node.next;
        }

        int index = count - n;
        int preIndex = index - 1;

        ListNode targetNode = map.get(index);
        ListNode preNode = map.get(preIndex);

        if (preNode == null) {
            head = targetNode.next;
            targetNode.next = null;
        } else {
            preNode.next = targetNode.next;
            targetNode.next = null;
        }

        return head;
    }
}
```

