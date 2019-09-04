import java.util.*;

/*
 * @lc app=leetcode.cn id=217 lang=java
 *
 * [217] 存在重复元素
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        boolean result = false;
        if (nums == null || nums.length < 2) {
            return result;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                result = true;
                break;
            }
            set.add(nums[i]);
        }
        return result;
    }
}
