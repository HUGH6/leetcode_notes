## 题目

给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：

> 输入：nums = [1,2,3]
> 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：

> 输入：nums = [0,1]
> 输出：[[0,1],[1,0]]

示例 3：

> 输入：nums = [1]
> 输出：[[1]]


提示：

* 1 <= nums.length <= 6
* -10 <= nums[i] <= 10
* nums 中的所有整数 互不相同



**链接：**

https://leetcode-cn.com/problems/permutations

## 思路

**回溯**

对于数组nums=[1...n]，要返回所有的全排列，那么久用一个list表示一个排列，那么排列中的第一位可以从1~n中任选一位，以此类推，第k位则从nums中剩下n-k个中任选其一。

因此，采用回溯的方法，当选择某个数作为排列中的第k位时，就遍历nums数组，将所有没有被选的元素都进行尝试，将其放入排列，并进行k+1个选择。每个选择结束后都要将list和nums的选择状态恢复选择前的原装。

这个过程中，需要一个pos数组，记录nums中每个数是否被选择的情况。

## 题解

回溯

```java
class Solution {
    int [] pos;
    int [] num;
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        pos = new int[nums.length];
        num = nums;
        Arrays.fill(pos, -1);
        ArrayList<Integer> list = new ArrayList<>();
        backtracing(0, list);
        return ans;
    }

    private void backtracing(int index, List<Integer> list) {
        if (list.size() == num.length) {
            ans.add(new ArrayList(list));
            return;
        }

        if (index >= num.length) {
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (pos[i] == -1) {
                list.add(num[i]);
                pos[i] = index;
                backtracing(index + 1, list);
                list.remove(list.size() - 1);
                pos[i] = -1;
            }


        }
    } 
}
```

