import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=268 lang=java
 *
 * [268] 缺失数字
 */
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length;
        int mid = (left + right) / 2;
        while (left < right) {
            if (nums[mid] > mid)
                right = mid;
            else
                left = mid + 1;
            mid = (left + right) / 2;
        }
        return left;
    }
}
