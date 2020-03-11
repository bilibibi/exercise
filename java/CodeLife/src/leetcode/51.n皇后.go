package main

import "fmt"

/*
 * @lc app=leetcode.cn id=51 lang=golang
 *
 * [51] N皇后
 */

// @lc code=start
var res [][]string

func solveNQueens(n int) [][]string {
	res = [][]string{}
	board := make([][]string, n)

	for i := 0; i < n; i++ {
		board[i] = make([]string, n)
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			board[i][j] = "."
		}
	}

	backtrack(board, 0)

	return res
}

func backtrack(board [][]string, row int) {
	if row == len(board) {
		tmpa, tmps := make([]string, 0, len(board)), ""
		for i := 0; i < len(board); i++ {
			for j := 0; j < len(board[i]); j++ {
				tmps += board[i][j]
			}
			tmpa = append(tmpa, tmps)
			tmps = ""
		}
		res = append(res, tmpa)
		return
	}

	for col := 0; col < len(board[row]); col++ {
		// 排除不合法选择
		if !isValid(board, row, col) {
			continue
		}
		// 做选择
		board[row][col] = "Q"
		// 进入下一行决策
		backtrack(board, row+1)
		// 撤销选择
		board[row][col] = "."
	}
}

func isValid(board [][]string, row, col int) bool {
	n := len(board)
	// 检查列是否有皇后互相冲突
	for i := 0; i < n; i++ {
		if board[i][col] == "Q" {
			return false
		}
	}

	// 检查右上方是否有皇后互相冲突
	for i, j := row-1, col+1; i >= 0 && j < n; i, j = i-1, j+1 {
		if board[i][j] == "Q" {
			return false
		}
	}

	// 检查左上方是否有皇后互相冲突
	for i, j := row-1, col-1; i >= 0 && j >= 0; i, j = i-1, j-1 {
		if board[i][j] == "Q" {
			return false
		}
	}
	return true
}

// @lc code=end

func main() {
	fmt.Println(solveNQueens(1))
}
