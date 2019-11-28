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


	/*
	  &符号的意思是对变量取地址，如：变量a的地址是&a
	  *符号的意思是对指针取值，如:*&a，就是a变量所在地址的值，当然也就是a的值了
	*/
	var a int = 1    //a=1
	var b *int = &a  //b=0xc
	var c **int = &b //c=0xc
	var x int = *b   //x=1

	fmt.Println(a, b, c, x)

	fmt.Println("a = ", a)                       //1
	fmt.Println("&a = ", &a)                     //0xc
	fmt.Println("*&a = ", *&a)                   //1 a
	fmt.Println("b = ", b)                       //0xc &a
	fmt.Println("&b = ", &b)                     //0xc
	fmt.Println("*&b = ", *&b)                   //0xc &a
	fmt.Println("*b = ", *b)                     //1 a
	fmt.Println("c = ", c)                       //0xc &b
	fmt.Println("*c = ", *c)                     //0xc &a
	fmt.Println("&c = ", &c)                     //0xc
	fmt.Println("*&c = ", *&c)                   //0xc &b
	fmt.Println("**c = ", **c)                   //1 a
	fmt.Println("***&*&*&*&c = ", ***&*&*&*&*&c) //1 a
	fmt.Println("x = ", x)                       //1 a
}
