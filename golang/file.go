package main

import "fmt"

type Data struct {
	x int
}

func (self Data) ValueTest() {
	fmt.Printf("Value: %p\n", &self)
}

func (self *Data) PointTest() {
	fmt.Printf("Point: %p\n", self)
}

func main() {
	d := Data{}
	p := &d
	fmt.Printf("Data:  %p\n", p)

	d.ValueTest()
	d.PointTest()

	p.ValueTest()
	p.PointTest()
}
