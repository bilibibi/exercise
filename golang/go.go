package main

func main() {
	// m := make(map[int]string)
	/*var m map[int]map[int]string
	m = make(map[int]map[int]string)
	a, ok := m[1][1]

	if !ok {
		m[1] = make(map[int]string)
	}
	m[1][1] = "ko"
	a, ok = m[1][1]

	fmt.Println(a, ok)*/

	// data := [...]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	// fmt.Println(data[:6:8]) //[0 1 2 3 4 5]
	// fmt.Println(data[3:])   //[5 6 7 8 9]
	// fmt.Println(data[:3])   //[0 1 2]
	// fmt.Println(data[:])    //[0123456789]

	/*d := [5]struct {
		x int
		y string
		z float32
	}{}

	d[1].x = 100
	d[2].y = "++"

	fmt.Println(d)
	fmt.Println(d[0])
	fmt.Printf("%p, %p\n", &d, &d[0])*/

	/* data := [...]int{0, 1, 2, 3, 4, 10: 0}
	s := data[:2:3]
	s = append(s, 100)
	fmt.Println(s, data)
	fmt.Println(&s[0], &data[0])*/

	/*data := [...]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	s := data[8:]
	s2 := data[:5]
	copy(s, s2) // dst:s2, src:s
	fmt.Println(s)
	fmt.Println(data)*/

	/*type People struct {
		age int
	}
	type Man struct {
		People
		jj int
	}

	u := Man{
		People{28},
		20,
	}

	println(u.age)
	println(u.jj)*/

	a := 0
	println(1 << 3)
	a = a | (1 << 3) // 在 bit3 上设置标志位 (从 bit0 开始算)
	a = a | (1 << 6) // 在 bit6 上设置标志位

	println(a)        // a = 72 = 0100 1000
	a = a &^ (1 << 6) // 清除 bit6 上的标志位，a = 8 = 0000 0100
	println(a)
}
