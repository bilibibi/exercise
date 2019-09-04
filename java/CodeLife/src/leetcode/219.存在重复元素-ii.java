import java.util.*;

/*
 * @lc app=leetcode.cn id=219 lang=java
 *
 * [219] 存在重复元素 II
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        boolean result = false;
        if (nums == null || nums.length < 2 || k < 0) {
            return result;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                result = true;
                break;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return result;
    }
}
