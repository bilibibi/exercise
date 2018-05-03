package main

import (
	"fmt"
	"hash/crc32"
)

func main() {
	t, no := 0, 0
	t = 1
	no = int(crc32.ChecksumIEEE([]byte("13083985232")) % 10)
	fmt.Println(fmt.Sprintf("user_%d_%d", no, t))
}
