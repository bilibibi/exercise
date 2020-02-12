package main

import "fmt"

/*
 * @lc app=leetcode.cn id=1041 lang=golang
 *
 * [1041] 困于环中的机器人
 */

// @lc code=start
func isRobotBounded(instructions string) bool {
	dest := []int{0, 0} // 当前位置
	d := []int{0, 1}    // 方向向量

	for _, v := range instructions {
		if v == 'G' {
			dest[0] += d[0]
			dest[1] += d[1]
			continue
		}
		if v == 'L' {
			if d[0] == 0 {
				d[0], d[1] = -1*d[1], -1*d[0]
			} else {
				d[0], d[1] = d[1], d[0]
			}
			continue
		}
		if v == 'R' {
			if d[0] == 0 {
				d[0], d[1] = d[1], d[0]
			} else {
				d[0], d[1] = -1*d[1], -1*d[0]
			}
		}
	}

	if dest[0] == 0 && dest[1] == 0 {
		return true
	}
	if d[0] == 0 && d[1] == 1 {
		return false
	}
	return true
}

// @lc code=end

func main() {
	fmt.Println(isRobotBounded("GL"))
}
