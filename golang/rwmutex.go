package main

import (
	"sync"
	"time"
)

var m *sync.RWMutex
var m2 *sync.RWMutex

func main() {
	m = new(sync.RWMutex)
	m2 = new(sync.RWMutex)

	// 写的时候啥也不能干
	go write(1)
	go write2(11)
	go read(2)
	go write(3)

	time.Sleep(5 * time.Second)
}

func read(i int) {
	println(i, "read start")

	m.RLock()
	println(i, "reading")
	time.Sleep(1 * time.Second)
	defer m.RUnlock()

	println(i, "read over")
}

func write(i int) {
	println(i, "write start")

	m.Lock()
	println(i, "writing")
	time.Sleep(1 * time.Second)
	defer m.Unlock()

	println(i, "write over")
}

func write2(i int) {
	println(i, "write start")

	m2.Lock()
	println(i, "writing")
	time.Sleep(1 * time.Second)
	defer m2.Unlock()

	println(i, "write over")
}
