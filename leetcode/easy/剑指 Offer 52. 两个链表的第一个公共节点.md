## 题目

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

 

示例 1：

![](https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png)

> 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
> 输出：Reference of the node with value = 8
> 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。


示例 2：

![](https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png)

> 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
> 输出：Reference of the node with value = 2
> 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。


示例 3：

![](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

> 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
> 输出：null
> 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
> 解释：这两个链表不相交，因此返回 null。


注意：

* 如果两个链表没有交点，返回 null.
* 在返回结果后，两个链表仍须保持原有的结构。
* 可假定整个链表结构中没有循环。
* 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
* 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/


**链接：**https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof

## 思路

**基础解法：**

时间复杂度O（n），空间复杂度O（n）

首先遍历a链表，将出现过的节点存入一个Set，然后遍历b链表，如果b链表当前节点已经出现在set中，说明这个节点是公共节点。

**进阶解法：**

由于有空间复杂度O（1）的限制，所以通过记录出现过的节点的方式无法使用，需要别的方法。

由于两个链表长度可能不一，因此也无法知道每个链表在距离多远处可能相遇，一种直观的想法是：如果让两个链表在公共节点前的部分“一样长”，那么，两个链表同时进行遍历，首次出现的相同点一定就是交汇点。

那么就需要计算出两个链表公共节点前的长度差，一种简单的方式就是从两个链表头同时遍历两个链表，当其中一个到达末尾时，另一个链表距离链表尾的长度就是这个长度差。

在求出长度差之后，只需要在较长的链表上先跳过这个长度差的节点，然后再同时开始遍历两个链表，此时，两个链表的长度就是一致的，如果有公共节点，就会被同时遍历到。



## 题解

基础解法

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();

        while (headA != null) {
            visited.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (visited.contains(headB)) {
                return headB;
            }

            headB = headB.next;
        }

        return null;
    }
}
```

进阶解法

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodea = headA, nodeb = headB;

        while (nodea != null && nodeb != null) {
            if (nodea == nodeb) {
                return nodea;
            }

            nodea = nodea.next;
            nodeb = nodeb.next;
        }

        ListNode shortList = null, longList = null;
        if (nodea == null) {
            shortList = headA;
            longList = headB;

            while (nodeb != null) {
                nodeb = nodeb.next;
                longList = longList.next;
            }
        } else if (nodeb == null) {
            shortList = headB;
            longList = headA;

            while (nodea != null) {
                nodea = nodea.next;
                longList = longList.next;
            }
        }

        while (shortList != null && longList != null) {
            if (shortList == longList) {
                return shortList;
            }

            shortList = shortList.next;
            longList = longList.next;
        }

        return null;
    }
}
```

