/*
 * @lc app=leetcode.cn id=575 lang=golang
 *
 * [575] 分糖果
 */

// @lc code=start
func distributeCandies(candies []int) int {
	candyMap := make(map[int]int)
	for _, candy := range candies {
		if _, ok := candyMap[candy]; !ok {
			candyMap[candy] = candy
		}
	}

	if len(candyMap) > len(candies) / 2 {
		return len(candies) / 2
	}
	return len(candyMap)
}
// @lc code=end

