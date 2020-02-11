package main

import "fmt"

/*
 * @lc app=leetcode.cn id=1103 lang=golang
 *
 * [1103] 分糖果 II
 */

// @lc code=start
func distributeCandies(candies int, num_people int) []int {
	kids := make([]int, num_people)
	num := 1
	for candies > 0 {
		for i := 0; i < num_people && candies > 0; i++ {
			if candies <= num {
				num = candies
			}
			kids[i] += num
			candies -= num
			num++
		}
	}
	return kids
}

// @lc code=end

func main() {
	fmt.Println(distributeCandies(1, 4))
}
