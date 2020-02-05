/*
 * @lc app=leetcode.cn id=367 lang=golang
 *
 * [367] 有效的完全平方数
 */

// @lc code=start
func isPerfectSquare(num int) bool {
	if num < 1 {
		return false
	}
	i := 1
	for i * i < num {
		i++
	}
	return i*i==num
}
// @lc code=end

