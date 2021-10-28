## 题目

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：

> 输入：head = [1,3,2]
> 输出：[2,3,1]


限制：

* 0 <= 链表长度 <= 10000



**链接：**

https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof

## 思路

翻转链表，遍历链表。

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
    public int[] reversePrint(ListNode head) {

        int count = 0;

        ListNode pre = null;
        ListNode node = head;

        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;

            count++;
        }

        int [] ans = new int[count];

        node = pre;
        int index = 0;
        while (node != null) {
            ans[index++] = node.val;
            node = node.next;
        }

        return ans;
        
    }

}
```

