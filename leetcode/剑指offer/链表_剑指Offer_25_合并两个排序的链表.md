## 题目

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：

> 输入：1->2->4, 1->3->4
> 输出：1->1->2->3->4->4

限制：

* 0 <= 链表长度 <= 1000

**链接：**

https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof

## 思路

同时遍历两个链表。

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2;

        ListNode ret = new ListNode(-1);
        ListNode n = ret;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                n.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                n.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            n = n.next;
        }

        while (l1 != null) {
            n.next = new ListNode(l1.val);
            l1 = l1.next;
            n = n.next;
        }

        while (l2 != null) {
            n.next = new ListNode(l2.val);
            l2 = l2.next;
            n = n.next;
        }

        return ret.next;
    }
}
```

