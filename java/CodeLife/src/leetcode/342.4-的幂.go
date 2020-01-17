package main

import "fmt"

/*
 * @lc app=leetcode.cn id=342 lang=golang
 *
 * [342] 4的幂
 */

// @lc code=start
func isPowerOfFour(num int) bool {
	if num < 1 || num & (num - 1) != 0 {
		return false
	}

	return 1 == num % 3
}
// @lc code=end

func main() {
	fmt.Println(isPowerOfFour(15))
}