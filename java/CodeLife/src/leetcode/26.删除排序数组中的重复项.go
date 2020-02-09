package main

/*
 * @lc app=leetcode.cn id=26 lang=golang
 *
 * [26] 删除排序数组中的重复项
 */

// @lc code=start
func removeDuplicates(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	pos := 1
	for _, num := range nums {
		if num != nums[pos-1] {
			nums[pos] = num
			pos++
		}
	}
	nums = nums[:pos]
	return pos
}

// @lc code=end

func main() {
	removeDuplicates([]int{1, 1, 2})
}
