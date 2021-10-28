## 题目

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

> 给定一个链表: 1->2->3->4->5, 和 k = 2.
>
> 返回链表 4->5.



**链接：**

https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof

## 思路

遍历链表，将每个节点的位置i和其引用存入map，最后直接安装n - k计算倒数第k个节点对应的位置即可，直接从map中取出。

## 题解

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        Map<Integer, ListNode> map = new HashMap<>();

        ListNode node = head;

        int count = 0;
        while (node != null) {
            map.put(count, node);
            node = node.next;
            count++;
        }

        return map.get(count - k);

    }
}
```

