package main

import "fmt"

func main() {
	a := [...]int{1, 2, 3}

	b := [2][3]int{
		{1, 2, 3}, {4, 5, 6}}

	fmt.Println(a)
	fmt.Println(b)

	c := [...]int{4, 7, 1, 2, 5, 9, 8, 6, 3}

	fmt.Println(c)

	num := len(c)
	for i := 0; i < num; i++ {
		for j := i + 1; j < num; j++ {
			if c[i] > c[j] {
				temp := c[j]
				c[j] = c[i]
				c[i] = temp
			}
		}
	}
	fmt.Println(c)
}
