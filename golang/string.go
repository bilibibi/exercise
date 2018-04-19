package main

import "fmt"

func main() {
	s := "abc"
	var c byte = s[1]
	fmt.Printf("%c, %x\n", c, c)
	// 按索引号访问
	bs := []byte(s) // 转换为 bytes，以便修改
	bs[1] = 'B'
	println(string(bs))
}
