package main

import "fmt"

/*
 * @lc app=leetcode.cn id=134 lang=golang
 *
 * [134] 加油站
 */

// @lc code=start
func canCompleteCircuit(gas []int, cost []int) int {
	pos := -1
	if sum(gas) >= sum(cost) {
		for start, _ := range gas {
			if gas[start] < cost[start] {
				continue
			}

			end := run(gas, cost, start)
			if end == start {
				pos = start
				break
			}
		}
	}

	return pos
}

func sum(nums []int) int {
	var result int
	for _, num := range nums {
		result += num
	}
	return result
}

func run(gas []int, cost []int, start int) int {
	end := start
	tank := gas[start] - cost[start]

	for tank >= 0 {
		end++
		if end == len(gas) {
			end = 0
		}
		if end == start {
			return end
		}
		tank += gas[end] - cost[end]
	}

	return end
}

// @lc code=end

func main() {
	gas, cost := []int{1, 2, 3, 4, 5}, []int{3, 4, 5, 1, 2}
	fmt.Println(canCompleteCircuit(gas, cost))
}
