/*
 * @lc app=leetcode.cn id=119 lang=golang
 *
 * [119] 杨辉三角 II
 */

// @lc code=start
func getRow(rowIndex int) []int {
	result := []int{1}
	for i := 0; i < rowIndex; i++ {
		result = append(result, 1) // 每增加一行，在最后补1
		for j := i; j > 0; j-- { // j>0 确保第一位始终为1
			result[j] += result[j-1]
		}
	}
	return result
}
// @lc code=end

