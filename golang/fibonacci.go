package main

import "fmt"

// fibonacci is a function that returns
// a function that returns an int.
func fibonacci() func() int {
	f0, f1, f2 := 0, 1, 0
	index := 0
	return func() int {
		if index == 0 {
			index++
			return f0
		} else if index == 1 {
			index++
			return f1
		} else {
			f2 = f0 + f1
			f0 = f1
			f1 = f2
			return f2
		}
	}
}

func main() {
	f := fibonacci()
	for i := 0; i < 10; i++ {
		fmt.Println(f())
	}
}
