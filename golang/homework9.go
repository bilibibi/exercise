package main

import "fmt"

func main() {
	fs := [4]func(){}

	for i := 0; i < 4; i++ {
		defer fmt.Println("defer i = ", i)                      // 值拷贝
		defer func() { fmt.Println("defer_closure i = ", i) }() // 闭包
		fs[i] = func() { fmt.Println("closure i = ", i) }       // 闭包
	}

	for _, f := range fs {
		f()
	}
}
