package main

import "fmt"

func main() {
	for i := 0; i < 10; i++ {
		fmt.Println("i = ", i)
		for j := 0; j < 10; j++ {
			if j == i {
				fmt.Println("break")
				break
			}
			fmt.Println("j = ", j)
		}
		fmt.Println("after break")
	}
}
