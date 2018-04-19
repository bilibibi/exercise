package main

import "fmt"

// 第一种写法
func quickSort(values []int, left, right int) {
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
	fmt.Println(values)

	if p-left > 1 {
		fmt.Println("sort left")
		quickSort(values, left, p-1)
	}
	if right-p > 1 {
		fmt.Println("sort right")
		quickSort(values, p+1, right)
	}
}

func QuickSort(values []int) {
	if len(values) <= 1 {
		return
	}
	quickSort(values, 0, len(values)-1)
}

// 第二种写法
func Quick2Sort(values []int) {
	if len(values) <= 1 {
		return
	}
	mid, i := values[0], 1
	head, tail := 0, len(values)-1
	for head < tail {
		fmt.Println(values)
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
	Quick2Sort(values[:head])
	Quick2Sort(values[head+1:])
}

func main() {
	a := []int{1, 9, 2, 4, 3, 5, 7, 8, 6, 0}
	fmt.Println("a is ", a)
	QuickSort(a)

	b := []int{1, 9, 2, 4, 3, 5, 7, 8, 6, 0}
	fmt.Println("b is ", b)
	Quick2Sort(b)
	fmt.Println("b is ", b)
}
