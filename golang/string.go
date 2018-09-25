package main

import (
	"fmt"
	"strings"
)

func main() {
	// s := "abc"
	// var c byte = s[1]
	// fmt.Printf("%c, %x\n", c, c)
	// // 按索引号访问
	// bs := []byte(s) // 转换为 bytes，以便修改
	// bs[1] = 'B'
	// println(string(bs))

	// s := "fr_118060212323500495725"
	// println(s)
	// println(s[0:21])
	// println(s[21:])
	//
	// if len(orderId) > 21 {
	// 	orderId = orderId[21:] // 截掉订单号，只保留支付记录id
	// }

	// s := "小B店铺测试微信会员卡模板"
	// rs := []rune(s)
	// println(string(rs[:12]))

	cardNo := "T168100091180600001"
	cardNo = strings.Replace(cardNo, "T", "", 1)
	fmt.Println(cardNo)
}
