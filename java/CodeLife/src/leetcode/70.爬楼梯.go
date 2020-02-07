package main

import "fmt"

/*
 * @lc app=leetcode.cn id=70 lang=golang
 *
 * [70] 爬楼梯
 */

// @lc code=start
func climbStairs(n int) int {
	if n == 1 {
		return 1
	}
	first, second := 1, 2

	for i := 3; i <= n; i++ {
		third := first + second
		first, second = second, third
	}
	return second
}

// @lc code=end

func main() {
	fmt.Println(climbStairs(4))
}
