package main

func main() {
	a := 0
	a = a | (1 << 3) // 在 bit3 上设置标志位 (从 bit0 开始算) a = a | (1 << 6)    // 在 bit6 上设置标志位

	println(a)        // a = 72 = 0100 1000
	a = a &^ (1 << 6) // 清除 bit6 上的标志位，a = 8 = 0000 0100
	println(a)
}
