package main

import "fmt"

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
	// i := 1
	// for num > 0 {
	// 	num -= i
	// 	i += 2
	// }
	// return num == 0

	// 二分查找
	left, right := 1, num
	for left < right {
		mid := (left + right) / 2
		if mid*mid == num {
			return true
		} else if mid*mid < num {
			left = mid + 1
		} else {
			right = mid
		}
	}
	return left*left == num
}

// @lc code=end

func main() {
	fmt.Println(isPerfectSquare(16))
}
