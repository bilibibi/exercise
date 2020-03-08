package main

import (
	"fmt"
	"strings"
)

/*
 * @lc app=leetcode.cn id=125 lang=golang
 *
 * [125] 验证回文串
 */

// @lc code=start
func isPalindrome(s string) bool {
	if len(s) == 0 {
		return true
	}

	s = strings.ToLower(s)
	l, r := 0, len(s)-1
	for l < r {
		sl := s[l]
		if !('a' <= sl && sl <= 'z' || '0' <= sl && sl <= '9') {
			l++
			continue
		}

		sr := s[r]
		if !('a' <= sr && sr <= 'z' || '0' <= sr && sr <= '9') {
			r--
			continue
		}

		if sl != sr {
			return false
		}

		l++
		r--
	}
	return true
}

// @lc code=end

func main() {
	fmt.Println(isPalindrome("123321"))
}
