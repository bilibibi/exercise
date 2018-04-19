package main

import (
	"fmt"
	"time"
)

func main() {
	TimeFormat := "2006-01-02 15:04:05"
	// DateFormat := "2006-01-02"
	// now := time.Now().Format(DateFormat)
	//now, _ := time.Parse(format, time.Now().Format(format))
	a, _ := time.Parse(TimeFormat, "2014-03-13 00:00:00")
	b, _ := time.Parse(TimeFormat, "2015-03-13 00:00:00")

	// fmt.Println(now.Format(format), a.Format(format), b.Format(format))
	// fmt.Println(now.After(a))
	// fmt.Println(now.Before(a))
	// fmt.Println(now.After(b))
	// fmt.Println(now.Before(b))
	fmt.Println(a, b)
	fmt.Println(a.Before(b))
	// fmt.Println(now.Format(format), a.Format(format), b.Format(format))
	// fmt.Println(now.Unix(), a.Unix(), b.Unix())
}
