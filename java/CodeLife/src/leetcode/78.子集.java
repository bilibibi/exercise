import java.util.*;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        // System.out.println("res==>" + res);
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            // System.out.println("i==>" + i + " j==>" + j + " tmp==>" + tmp);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
