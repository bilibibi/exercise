package main

import (
	"fmt"
	"strconv"
)

func main() {
	balance, _ := strconv.ParseFloat("100.01", 64)
	fmt.Println(balance)
	fmt.Println(int64(balance))
}
