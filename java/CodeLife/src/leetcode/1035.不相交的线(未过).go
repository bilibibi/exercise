package main

import "fmt"

/*
 * @lc app=leetcode.cn id=1035 lang=golang
 *
 * [1035] 不相交的线
 */

// @lc code=start
func maxUncrossedLines(A []int, B []int) int {
	if len(A) == 0 || len(B) == 0 {
		return 0
	}
	linesNum, maxLinesNum, indexA, indexB := 0, 0, 0, 0
	lenA, lenB := len(A), len(B)
	for ; indexA < lenA; indexA++ {
		for ai := indexA; ai < lenA; ai++ {
			for bi := indexB; bi < lenB; bi++ {
				if A[ai] == B[bi] {
					linesNum++
					indexB = bi + 1
					break
				}
			}
		}
		if linesNum > maxLinesNum {
			maxLinesNum = linesNum
		}
		fmt.Println(linesNum)
		linesNum, indexB = 0, 0
	}
	return maxLinesNum
}

// @lc code=end
func main() {
	A, B := []int{3, 1, 4, 1, 1, 3, 5, 1, 2, 2}, []int{4, 1, 5, 2, 1, 1, 1, 5, 3, 1, 1, 1, 2, 3, 1, 4, 3, 5, 5, 3, 1, 2, 3, 2, 4, 1, 1, 1, 5, 3}
	fmt.Println(maxUncrossedLines(A, B))
}
