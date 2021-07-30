## 题目

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
示例 1:

> 输入: "A"
> 输出: 1

示例 2:

> 输入: "AB"
> 输出: 28

示例 3:

> 输入: "ZY"
> 输出: 701



**链接：**

https://leetcode-cn.com/problems/excel-sheet-column-number

## 思路

从左往右，逐位将字符转换为数字，但增加位数时注意是以26进制增加的。

## 题解

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            char c = columnTitle.charAt(i);
            num = num * 26 + toNum(c);
        }
        return num;
    }

    private int toNum(char c) {
        return c - 'A' + 1;
    }
}
```

