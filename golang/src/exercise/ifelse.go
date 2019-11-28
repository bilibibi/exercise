package main

import "fmt"

func main() {

	switch a := 3; {
	case a > 2:
		fmt.Println("a>2")
		fallthrough
	case a > 3:
		fmt.Println("a>3")
	default:
		fmt.Println("default")
	}

	LABEL:
		for i := 0; i < 10; i++ {
			for {
				fmt.Println(i)
				goto LABEL
			}
		}
		fmt.Println("done")
}
