package main

import (
	"fmt"
	"time"
)

func fortest(i int) {
	for {
		fmt.Println(i, "a")
		func() {
			fmt.Println(i, "b")
			defer recover()
			fmt.Println(i, "c")
			for {
				time.Sleep(time.Second * 2)
				fmt.Println(i, "inner for")
				panic("test error")
			}

			fmt.Println(i, "4")
		}()
		fmt.Println(i, "5")
		time.Sleep(time.Second * 1)
	}
	fmt.Println(i, "d")

}

func main() {
	go fortest(1)
	// go fortest(2)

	time.Sleep(time.Second * 10)
}
