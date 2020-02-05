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

	// 暴力循环
	// i := 1
	// for i * i < num {
	// 	i++
	// }
	// return i*i==num

	// 数学定理 (1 + 3 + 5 + ... + (2n - 1) = n ^ 2)
	i := 1
	for num > 0 {
		num -= i
		i += 2
	}
	return num == 0

}
// @lc code=end

