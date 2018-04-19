package main

import (
	"fmt"
)

//接口定义
type Personer interface {
	Run()
	Name() string
}

//实现接口，注意实现接口的不一定只是结构体，也可以是函数对象
type person struct {
	name string
	age  int
}

func (person) Run() {
	fmt.Println("running...")
}

//接收参数person不可以是指针类型，否则不认为是实现了接口
func (p person) Name() string {
	return p.name
}

func main() {
	//接口类型的变量定义
	var p Personer
	fmt.Println(p) //值<nil>
	//实例化结构体，并赋值给interface
	p = person{"taozs", 18} //或者：&person{"taozs", 18}
	p.Run()
	fmt.Println(p.Name())
	var p2 person = p.(person) //类型断言，接口类型断言到具体类型
	fmt.Println(p2.name, p2.age)
}

/*
另外，类型断言返回值也可以有第二个bool值，表示断言是否成功，如下：

if p2, ok := p.(person); ok {//断言成功ok值为true
	fmt.Println(ok)
	fmt.Println(p2.age)
}
*/
