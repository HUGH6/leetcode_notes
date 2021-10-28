## 题目

字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：

序列中第一个单词是 beginWord 。
序列中最后一个单词是 endWord 。
每次转换只能改变一个字母。
转换过程中的中间单词必须是字典 wordList 中的单词。
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。


示例 1：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

示例 2：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。


提示：

* 1 <= beginWord.length <= 10
* endWord.length == beginWord.length
* 1 <= wordList.length <= 5000
* wordList[i].length == beginWord.length
* beginWord、endWord 和 wordList[i] 由小写英文字母组成
* beginWord != endWord
* wordList 中的所有字符串 互不相同



**链接：**

https://leetcode-cn.com/problems/word-ladder

## 思路

广度优先遍历

由于两个单词间只有一处不同时才能变，那么，将单词视为节点，能变的关系视为边，那么进行一次广度优先遍历即可求出到目标节点的最短路径。

## 题解


    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Map<String, Integer> map = new HashMap<>();
            wordList.add(beginWord);
    
            int [][] g = new int[wordList.size()][wordList.size()];
            boolean [] inqueue = new boolean[wordList.size()];
    
            int index = 0;
            for (String s : wordList) {
                map.put(s, index++);
            }
    
            // 构图
            for (int i = 0; i < wordList.size(); i++) {
                for (int j = i + 1; j < wordList.size(); j++) {
                    if (canConnect(wordList.get(i), wordList.get(j))) {
                        g[map.get(wordList.get(i))][map.get(wordList.get(j))] = 1;
                        g[map.get(wordList.get(j))][map.get(wordList.get(i))] = 1;
                    }
                }
            }
    
            // bfs遍历
            Queue<Integer> queue = new LinkedList<>();
            int start = map.get(beginWord);
            Integer end = map.get(endWord);
    
            if (end == null) {
                return 0;
            }
    
            int pathLen = 0;
    
            queue.offer(start);
            inqueue[start] = true;
    
            int levelSize = 1;
            while (!queue.isEmpty()) {
                pathLen++;
                int levelCount = 0;
                for (int i = 0; i < levelSize; i++) {
                    int node = queue.poll();
    
                    if (node == end) {
                        return pathLen;               
                    }
    
    
                    for (int j = 0; j < wordList.size(); j++) {
                        if (g[node][j] == 1 && !inqueue[j]) {
                            queue.offer(j);
                            inqueue[j] = true;
                            levelCount++;
                        }
                    }
                }
                levelSize = levelCount;
            }
    
            return 0;
        }
    
        public boolean canConnect(String s1, String s2) {
            if (s1.length() != s2.length()) {
                return false;
            }
    
            int c = 0;
            for (int i = 0; i < s1.length();i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    c++;
                }
                if (c > 1) {
                    return false;
                }
            }
    
            if (c == 1) {
                return true;
            }
    
            return false;
        }
    }

