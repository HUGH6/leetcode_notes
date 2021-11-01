## 题目

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。

数值（按顺序）可以分成以下几个部分：

若干空格
一个 小数 或者 整数
（可选）一个 'e' 或 'E' ，后面跟着一个 整数
若干空格
小数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
下述格式之一：
至少一位数字，后面跟着一个点 '.'
至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
一个点 '.' ，后面跟着至少一位数字
整数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
至少一位数字
部分数值列举如下：

["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
部分非数值列举如下：

["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]


示例 1：

> 输入：s = "0"
> 输出：true

示例 2：

> 输入：s = "e"
> 输出：false

示例 3：

> 输入：s = "."
> 输出：false

示例 4：

> 输入：s = "    .1  "
> 输出：true


提示：

* 1 <= s.length <= 20
* s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。

**链接：**

https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof

## 思路

使用有限状态机来对字符串进行解析。

## 题解

```java
class Solution {
    public static final char EOF = '@';
    public boolean isNumber(String s) {
        String str = s.trim().toLowerCase();

        int indexE = str.indexOf('e');

        if (indexE != -1 && str.indexOf(indexE + 1, 'e') != -1) {
            return false;
        }

        if (indexE == -1) {
            return isInteger(str) || isDecimal(str);
        } else {
            return (isInteger(str.substring(0, indexE)) || isDecimal(str.substring(0, indexE))) && isInteger(str.substring(indexE + 1, str.length()));
        }
    }

    public boolean isDecimal(String s) {
        int len = s.length();
        int index = 0;

        if (s == null || "".equals(s)) {
            return false;
        }
        
        char ch = s.charAt(index);

        if (ch == '-' || ch == '+') {
            return s1(s, index + 1);
        } else if (ch <= '9' && ch >= '0') {
            return s2(s, index + 1);
        } else if (ch == '.') {
            return s5(s, index + 1);
        } else {
            return false;
        }
    }

    private boolean s1(String s, int index) {
        // 表示终止符
        char ch = EOF;

        if (index < s.length()) {
            ch = s.charAt(index);
        }

        if (ch <= '9' && ch >= '0') {
            return s2(s, index + 1);
        } else if (ch == '.') {
            return s5(s, index + 1);
        } else {
            return false;
        }
    }

    private boolean s2(String s, int index) {
        // 表示终止符
        char ch = '@';

        if (index < s.length()) {
            ch = s.charAt(index);
        }

        if (ch <= '9' && ch >= '0') {
            return s2(s, index + 1);
        } else if (ch == '.') {
            return s3(s, index + 1);
        } else {
            return false;
        }
    }

    private boolean s3(String s, int index) {
        // 表示终止符
        char ch = EOF;

        if (index < s.length()) {
            ch = s.charAt(index);
        }

        if (ch <= '9' && ch >= '0') {
            return s4(s, index + 1);
        } else if (ch == EOF) {
            return true;
        } else {
            return false;
        }
    }

    private boolean s4(String s, int index) {
        // 表示终止符
        char ch = EOF;

        if (index < s.length()) {
            ch = s.charAt(index);
        }

        if (ch <= '9' && ch >= '0') {
            return s4(s, index + 1);
        } else if (ch == EOF) {
            return true;
        } else {
            return false;
        }
    }

    private boolean s5(String s, int index) {
        // 表示终止符
        char ch = EOF;

        if (index < s.length()) {
            ch = s.charAt(index);
        }

        if (ch <= '9' && ch >= '0') {
            return s4(s, index + 1);
        } else {
            return false;
        }
    }

    public boolean isInteger(String s) {
        int len = s.length();
        int index = 0;

        if (s == null || "".equals(s)) {
            return false;
        }

        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
        }

        if (index >= len) {
            return false;
        }

        while (index < len) {
            if (!(s.charAt(index) <= '9' && s.charAt(index) >= '0')) {
                return false;
            }
            index++;
        }

        return true;
    }
}
```

