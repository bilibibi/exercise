/*
 * @lc app=leetcode.cn id=503 lang=java
 *
 * [503] 下一个更大元素 II
 */
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int max = nums.length;
        int[] result = new int[max];
        for (int start = 0; start < max; start++) {
            int next = start + 1;
            while (true) {
                if (next >= max) {
                    next = 0; // 遍历重头开始
                }
                if (next == start) {
                    result[start] = -1;
                    break;
                }
                if (nums[next] > nums[start]) {
                    result[start] = nums[next];
                    break;
                }
                next++;
            }
        }
        return result;
    }
}

