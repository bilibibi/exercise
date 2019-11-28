package main

import (
	"runtime"
	"sync"
)

/*func main() {
	wg := new(sync.WaitGroup)
	wg.Add(2)

	go func() {
		defer wg.Done()

		for i := 0; i < 10; i++ {
			println(i)
			if i == 3 {
				runtime.Gosched()
			}
		}
	}()

	go func() {
		defer wg.Done()
		println("fyj")
	}()

	fmt.Println(" Before Wait()")
	wg.Wait()
	fmt.Println(" After Wait()")
}*/

func main() {
	wg := new(sync.WaitGroup)
	wg.Add(3)
	go func() {
		defer wg.Done()
		for i := 0; i < 6; i++ {
			println(i)
			if i == 3 {
				println("runtime.Gosched()")
				runtime.Gosched() //无效？
			}
		}
	}()
	go func() {
		defer wg.Done()
		println("Hello, World!")
	}()
	go func(){
		defer wg.Done()
		println("last")
	}()
	wg.Wait()
}