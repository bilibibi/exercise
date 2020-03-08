package main

import "fmt"

/*
 * @lc app=leetcode.cn id=914 lang=golang
 *
 * [914] 卡牌分组
 */

// @lc code=start
func hasGroupsSizeX(deck []int) bool {
	var count [10000]int
	for _, d := range deck {
		count[d]++
	}

	g := -1 // 计算最大公约数
	for _, c := range count {
		if g == 1 { // 最大公约数为1时结束循环
			// fmt.Println("let me out")
			break
		}
		if g == -1 {
			g = c
		} else if c > 0 {
			g = gcd(g, c)
		}
	}
	return g >= 2
}

// 计算x和y的最大公约数
func gcd(x, y int) int {
	if x == 0 {
		return y
	} else {
		return gcd(y%x, x)
	}
}

// @lc code=end

func main() {
	fmt.Println(hasGroupsSizeX([]int{1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3}))
}
