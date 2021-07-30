## 题目

请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

函数 myAtoi(string s) 的算法如下：

* 读入字符串并丢弃无用的前导空格
* 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
* 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
* 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
* 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
* 返回整数作为最终结果。

**注意：**

* 本题中的空白字符只包括空格字符 ' ' 。
* 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。

**链接：**

https://leetcode-cn.com/problems/string-to-integer-atoi


## 思路

按照题目描述的过程处理字符串即可。

## 题解

```java
class Solution {
    public int myAtoi(String s) {
        int strLength = s.length();
        int index = 0;
        int res = 0;
        int minus = 1;

        while (index < strLength && s.charAt(index) == ' ') {
            index++;
        }

        if (index >= strLength) {
            return res;
        } 

        if (s.charAt(index) == '-') {
            minus = -1;
            index++;
        } else if (s.charAt(index) == '+') {
            index++;
        }

        long tempRes = res;
        while (index < strLength && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            tempRes = tempRes * 10 + convertCharToInt(s.charAt(index));

            if (checkIntOverflow(tempRes)) {
                if (minus == -1) {
                    res = Integer.MIN_VALUE;
                } else {
                    res = Integer.MAX_VALUE;
                }

                return res;
            }

            index++;
        }

        res = (int)tempRes;
        return res * minus;
    }

    private int convertCharToInt(char c) {
        return (int)(c - '0');
    }

    private boolean checkIntOverflow(long num) {
        int realNum = (int)num;
        return realNum == num ? false : true;
    }
}
```

## 要点

### int数据的溢出判断

可以使用long类型保存原始值，然后将其转换成int型，比较转换前后数值是否相同，如果不同，表示原始数据int型溢出。

```java
    private boolean checkIntOverflow(long num) {
        int realNum = (int)num;
        return realNum == num ? false : true;
    }
```

