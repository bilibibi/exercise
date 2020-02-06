/*
 * @lc app=leetcode.cn id=121 lang=golang
 *
 * [121] 买卖股票的最佳时机
 */

// @lc code=start
func maxProfit(prices []int) int {
	const UINT_MAX = ^uint(0) // 无符号整型最大值
	const INT_MAX = int(^uint(0) >> 1) // 有符号整型最大值

	minPrice, maxProfit := INT_MAX, 0
	for _, p := range prices {
		if p < minPrice {
			minPrice = p
			continue
		}
		if p-minPrice > maxProfit {
			maxProfit = p-minPrice
		}
	}
	return maxProfit
}
// @lc code=end

