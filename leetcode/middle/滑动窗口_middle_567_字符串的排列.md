## 题目

给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，s1 的排列之一是 s2 的 子串 。

 

示例 1：

> 输入：s1 = "ab" s2 = "eidbaooo"
> 输出：true
> 解释：s2 包含 s1 的排列之一 ("ba").

示例 2：

> 输入：s1= "ab" s2 = "eidboaoo"
> 输出：false


提示：

* 1 <= s1.length, s2.length <= 104
* s1 和 s2 仅包含小写字母



**链接：**

https://leetcode-cn.com/problems/permutation-in-string

## 思路

滑动窗口

由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，一个字符串才是另一个字符串的排列。

因此，用一个26长度的哈希表表示s1的字符出现序列，然后对s2，从头开始，按s1的长度匹配字串，统计与s1的哈希表是否相同。

以这个s1长度的窗口，向右滑动，不断更新窗口内对应的哈希表值，如果能匹配成功，则返回true，否则返回false。

## 题解

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) {
            return false;
        }

        int [] hash1 = new int[26];
        int [] hash2 = new int[26];

        for (int i = 0; i < len1; i++) {
            hash1[s1.charAt(i) - 'a']++;
            hash2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(hash1, hash2)) {
            return true;
        }

        for (int right = len1; right < len2; right++) {
            hash2[s2.charAt(right) - 'a']++;
            hash2[s2.charAt(right - len1) - 'a']--;

            if (Arrays.equals(hash1, hash2)) {
                return true;
            }
        }

        return false;
    }
}
```
