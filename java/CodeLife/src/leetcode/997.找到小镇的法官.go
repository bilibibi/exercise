package main

/*
 * @lc app=leetcode.cn id=997 lang=golang
 *
 * [997] 找到小镇的法官
 */

// @lc code=start
func findJudge(N int, trust [][]int) int {
	cnt := make([]int, N+1) // 统计出入度
	for _, index := range trust {
		cnt[index[0]]-- // 出度--
		cnt[index[1]]++ // 入度++
	}
	for i := 1; i <= N; i++ {
		if cnt[i] == N-1 {
			return i
		}
	}
	return -1
}

// @lc code=end

func main() {
	findJudge(2, [][]int{{1, 2}})
}
