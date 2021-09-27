## 题目

给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。

若无答案，则返回空字符串。

 

示例 1：

> 输入：
> words = ["w","wo","wor","worl", "world"]
> 输出："world"
> 解释： 
> 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。

示例 2：

> 输入：
> words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
> 输出："apple"
> 解释：
> "apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。


提示：

* 所有输入的字符串都只包含小写字母。
* words数组长度范围为[1,1000]。
* words[i]的长度范围为[1,30]。

**链接：**

https://leetcode-cn.com/problems/longest-word-in-dictionary

## 思路

暴力解法：

对于每个单词，我们可以检查它的全部前缀是否存在，可以通过 Set 数据结构来加快查找

算法：

* 当我们找到一个单词它的长度更长且它的全部前缀都存在，我们将更改答案。
* 或者，我们可以事先将单词排序，这样当我们找到一个符合条件的单词就可以认定它是答案



前缀树/字典树：

将所有单词插入 `trie`，然后从 `trie` 进行深度优先搜索，每找到一个单词表示该单词的全部前缀均存在，我们选取长度最长的单词

## 题解

暴力解法

```
class Solution {
    public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        for (String word: words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }    
        }
        return ans;
    }
}
```




    class Solution {
        public int[] sortedSquares(int[] nums) {
            int [] ans = new int[nums.length];
            int left = 0, right = nums.length - 1;
            int index = right;
    
            while (left <= right) {
                int leftAbs = Math.abs(nums[left]);
                int rightAbs = Math.abs(nums[right]);
                if (leftAbs > rightAbs) {
                    ans[index] = leftAbs * leftAbs;
                    left++;
                } else if (leftAbs < rightAbs) {
                    ans[index] = rightAbs * rightAbs;
                    right--;
                } else {
                    ans[index] = leftAbs * leftAbs;
                    left++;
                }
                index--;
            }
    
            return ans;
        }
    }