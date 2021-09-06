## 题目

给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

> 输入: s = "abcabcbb"
> 输出: 3 
> 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:

> 输入: s = "bbbbb"
> 输出: 1
> 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:

> 输入: s = "pwwkew"
> 输出: 3
> 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
>      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

示例 4:

> 输入: s = ""
> 输出: 0


提示：

* 0 <= s.length <= 5 * 10^4
* s 由英文字母、数字、符号和空格组成

**链接：**

https://leetcode-cn.com/problems/longest-substring-without-repeating-characters

## 思路

滑动窗口

left指向最长子串左侧，right指向右侧，向右遍历。遍历过程中将遇到的不同字符存入map，如果遇到相同的字符，则更新left当这个重复字符第一次出现的位置之后，并删除map中这个调整区间内的所有字符。在迭代过错中一直通过right-left+1记录当前最大长度。

## 题解

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        int maxLen = 0;

        while (right < length) {
            if (map.get(s.charAt(right)) == null) {
                map.put(s.charAt(right), right);
                int len = right - left + 1;
                if (len > maxLen) {
                    maxLen = len;
                }
                right++;

            } else {
                int preIndex = map.get(s.charAt(right));
                int oldLeft = left;
                left = preIndex + 1;

                for (int i = oldLeft; i < left; i++) {
                    map.remove(s.charAt(i));
                }

                map.put(s.charAt(right), right);

                int len = right - left + 1;
                if (len > maxLen) {
                    maxLen = len;
                }
                right++;
            }
        }

        return maxLen;
    }
}
```
