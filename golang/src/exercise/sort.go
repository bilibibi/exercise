package main

import (
	"fmt"
)

var sli = []int{1, 43, 54, 62, 21, 66, 32, 78, 36, 76, 39}

// 冒泡排序 O(n)
func bubbleSort(sli []int) []int {
	len := len(sli)
	//该层循环控制 需要冒泡的轮数
	for i := 0; i < len; i++ {
		//该层循环用来控制每轮 冒出一个数 需要比较的次数
		for j := i + 1; j < len; j++ {
			if sli[i] > sli[j] {
				sli[i], sli[j] = sli[j], sli[i]
			}
		}
	}
	return sli
}

// 快速排序（第一种写法）
func quickSortA(values []int, left, right int) {
	if len(values) <= 1 {
		return
	}
	temp := values[left]
	p := left
	i, j := left, right

	for i <= j {
		for j >= p && values[j] >= temp {
			j--
		}
		if j >= p {
			values[p] = values[j]
			p = j
		}

		if i <= p && values[i] <= temp {
			i++
		}
		if i <= p {
			values[p] = values[i]
			p = i
		}
	}

	values[p] = temp
	//fmt.Println(values)

	if p-left > 1 {
		//fmt.Println("sort left")
		quickSortA(values, left, p-1)
	}
	if right-p > 1 {
		//fmt.Println("sort right")
		quickSortA(values, p+1, right)
	}
}

// 快速排序（第二种写法）
func quickSortB(values []int) {
	if len(values) <= 1 {
		return
	}
	mid, i := values[0], 1
	head, tail := 0, len(values)-1
	for head < tail {
		//fmt.Println(values)
		if values[i] > mid {
			values[i], values[tail] = values[tail], values[i]
			tail--
		} else {
			values[i], values[head] = values[head], values[i]
			head++
			i++
		}
	}
	values[head] = mid
	quickSortB(values[:head])
	quickSortB(values[head+1:])
}

func main() {
	fmt.Println(bubbleSort(sli))

	a := []int{1, 9, 2, 4, 3, 5, 7, 8, 6, 0}
	fmt.Println("a is ", a)
	quickSortA(a, 0, len(a)-1)
	fmt.Println("a is ", a)

	b := []int{1, 9, 2, 4, 3, 5, 7, 8, 6, 0}
	fmt.Println("b is ", b)
	quickSortB(b)
	fmt.Println("b is ", b)
}
