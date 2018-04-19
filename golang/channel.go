package main

import (
	"fmt"
	"os"
)

func main() {
	/*data := make(chan int, 3)
	exit := make(chan bool)

	data <- 1
	data <- 2
	data <- 3

	fmt.Println("begin new thread")
	go func() {
		for {
			if d, ok := <-data; ok {
				println("ok is true : ", d)
			} else {
				println("channel was closed")
				break
			}
		}

		for d := range data {
			println(d)
		}
		exit <- true
	}()

	fmt.Println("wait for")
	data <- 4
	data <- 5
	close(data)

	<-exit //通过阻塞等待协程执行完成*/

	/*d1 := make(chan int)
	d2 := make(chan int, 3)
	d2 <- 1
	fmt.Println(len(d1), cap(d1)) // 0  0
	fmt.Println(len(d2), cap(d2)) // 1  3*/

	/*data := make(chan int, 3)
	exit := make(chan bool)

	data <- 1
	data <- 2
	data <- 3
	//close(data)

	go func() {
		for true {
			select {
			case i := <-data:
				fmt.Println(i)
			case <-time.After(time.Duration(3) * time.Second):
				data <- 4

				//ch := make(chan string)
				//go Afunction(ch)
				//ch <- "ok"

				fmt.Println("timeout")
				exit <- true
				break
			}
		}
	}()
	<-exit
	fmt.Println("close(exit)")
	close(exit)*/

	a, b := make(chan int, 3), make(chan int)

	fmt.Println("new thread start...")
	go func() {
		v, ok, s := 0, false, ""
		for true {
			select {
			case v, ok = <-a:
				s = "a"
			case v, ok = <-b:
				s = "b"
			}

			if ok {
				fmt.Println(s, v)
			} else {
				os.Exit(0)
			}
		}
	}()

	//fmt.Println("for 5")
	for i := 0; i < 5; i++ {
		select {
		case a <- i:
		case b <- i:
		}
	}

	//fmt.Println("close(a)")
	close(a)

	//fmt.Println("select{}")
	select {}

}

func Afunction(c chan string) {
	fmt.Println("finish")
	msg := <-c
	fmt.Println("end: " + msg)
}
