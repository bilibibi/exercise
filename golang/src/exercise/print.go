package main

import (
	"fmt"
	"strconv"
)

//常量声明
const (
	PI   = 3.14
	LOVE = 1314
)

//全局变量
var (
	name = "fangyj"
	sex  = "man"
)

type (
	//类型别名
	intType int
	floatType float32
	doubleType float64
	//结构
	structs struct {
	}
	//接口
	goland interface {
	}
)

func main() {
	var a int
	a = 100

	var b int = 101
	var c = 102
	d := 103

	fmt.Println("hello world! i'm go!")
	fmt.Println(a)
	fmt.Println(b)
	fmt.Println(c)
	fmt.Println(d)

	f := 104.0101
	g := int(f)

	fmt.Println(f)
	fmt.Println(g)

	var s int = 65
	ss := strconv.Itoa(s)
	//s = strconv.Atoi(ss)
	fmt.Println(ss)

}
