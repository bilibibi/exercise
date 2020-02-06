package main

import "fmt"

/*
 * @lc app=leetcode.cn id=118 lang=golang
 *
 * [118] 杨辉三角
 */

// @lc code=start
func generate(numRows int) [][]int {
	var yanghui [][]int
	for i := 0; i < numRows; i++ {
		var tmp []int
		for j := 0; j <= i; j++ {
			switch {
			case j == 0:
				tmp = append(tmp, 1)
			case j == i:
				tmp = append(tmp, 1)
				break
			default:
				tmp = append(tmp, yanghui[i-1][j-1]+yanghui[i-1][j])
			}
		}
		yanghui = append(yanghui, tmp)
	}
	return yanghui
}

// @lc code=end

func main() {
	fmt.Println(generate(5))
}
