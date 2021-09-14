## 题目

给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。

 

示例：

> 输入：S = "a1b2"
> 输出：["a1b2", "a1B2", "A1b2", "A1B2"]

> 输入：S = "3z4"
> 输出：["3z4", "3Z4"]

> 输入：S = "12345"
> 输出：["12345"]


提示：

* S 的长度不超过12。
* S 仅由数字和字母组成。



**链接：**

https://leetcode-cn.com/problems/letter-case-permutation

## 思路

**回溯**

对于每一位的字符，如果字符时字母，那么就有两种选择：大写或小写。因此，使用回溯的方法，尝试每一种可能。

对于数字，直接加入集合即可，但要注意，回溯时，这些数字也要回溯（即如果之前加入了数字，那之后也要删除这些数字）

## 题解

回溯

```java
class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        StringBuilder sb = new StringBuilder();
        backtracing(s, 0, sb);
        return ans;

    }  

    private void backtracing(String s, int index, StringBuilder sb) {
        if (index >= s.length()) {
            ans.add(sb.toString());
            return;
        }

        char c = s.charAt(index);
        if (Character.isDigit(c)) {
            sb.append(c);
            backtracing(s, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            
        } else if (c >= 'A' && c <= 'Z') {
            sb.append(c);
            backtracing(s, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            sb.append(Character.toLowerCase(c));
            backtracing(s, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(Character.toUpperCase(c));
            backtracing(s, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            sb.append(c);
            backtracing(s, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

