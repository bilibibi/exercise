package main

import (
	"flag"
	"fmt"
	"hash/crc32"
)

var (
	mobile = flag.String("m", "", "mobile")
)

func main() {
	flag.Parse()
	if len(*mobile) > 0 {
		no := int(crc32.ChecksumIEEE([]byte(*mobile)) % 10)
		fmt.Println(fmt.Sprintf("user_%d_%d", no, 1))
		return
	}
}
