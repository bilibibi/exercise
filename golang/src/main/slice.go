package main

import "fmt"

var ChannelUserCache = make(map[string]int)

func main() {
	ChannelUserCache["one"] = 1
	ChannelUserCache["two"] = 2
	ChannelUserCache["three"] = 3

	s := &ChannelUserCache
	fmt.Println(ChannelUserCache)
	ChannelUserCache = make(map[string]int)
	fmt.Println(ChannelUserCache)

	fmt.Println(*s)
}
