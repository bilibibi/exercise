package main

import "fmt"

func binarySearch(arr []int, des int) (result int) {
	low, high := 0, len(arr)-1
	for low <= high {
		mid := (low + high) / 2
		//fmt.Printf("low: %d, high: %d, mid: %d\n", low, high, mid)

		if des == arr[mid] {
			result = mid
			return
		}

		if des < arr[mid] {
			high = mid - 1
		} else {
			low = mid + 1
		}
	}

	result = -1
	return
}

// 1.查找最后一个小于key的元素
func findLastLess(arr []int, des int) (result int) {
	low, high := 0, len(arr)-1
	for low <= high {
		mid := (low + high) / 2
		//fmt.Printf("low: %d, high: %d, mid: %d\n", low, high, mid)
		if des <= arr[mid] {
			high = mid - 1
		} else {
			low = mid + 1
		}

	}
	result = high
	return
}

// 2.查找第一个大于等于key的元素
func findFirstGreaterEqual(arr []int, des int) (result int) {
	left, right := 0, len(arr)-1
	for left <= right {
		mid := (left + right) / 2
		if des <= arr[mid] {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	result = left
	return
}

// 3.查找最后一个小于等于key的元素
func findLastLessEqual(arr []int, des int) (result int) {
	left, right := 0, len(arr)-1
	for left <= right {
		mid := (left + right) / 2
		if des < arr[mid] {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	result = right
	return
}

// 4.查找第一个大于key的元素
func findFirstGreater(arr []int, des int) (result int) {
	left, right := 0, len(arr)-1
	for left <= right {
		mid := (left + right) / 2
		if des < arr[mid] {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	result = left
	return
}

// 5.查找第一个与key相等的元素
func findFirstEqual(arr []int, des int) (result int) {
	left, right := 0, len(arr)-1
	for left <= right {
		mid := (left + right) / 2
		if des <= arr[mid] {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	// arr[right] < key <= arr[left]
	// right是最后一个小于key的
	// left是第一个大于等于key的
	if left < len(arr) && arr[left] == des {
		result = left
	} else {
		result = -1
	}
	return
}

// 6.查找最后一个与key相等的元素
func findLastEqual(arr []int, des int) (result int) {
	left, right := 0, len(arr)-1
	for left <= right {
		mid := (left + right) / 2
		if des < arr[mid] {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	// arr[right] <= des < arr[left]
	// right是最后一个小于等于key的
	// left是第一个大于key的
	if right >= 0 && arr[right] == des {
		result = right
	} else {
		result = -1
	}
	return
}

func main() {
	arr := []int{1, 2, 3, 4, 5, 6, 6, 6, 8, 9, 10}
	des := 7
	fmt.Println("des index is ", binarySearch(arr, des))
	fmt.Println("1.last less index is ", findLastLess(arr, des))
	fmt.Println("2.first greater equal index is ", findFirstGreaterEqual(arr, des))
	fmt.Println("3.last less equal index is ", findLastLessEqual(arr, des))
	fmt.Println("4.first greater index is ", findFirstGreater(arr, des))

	fmt.Println("5.first equal index is ", findFirstEqual(arr, des))
	fmt.Println("6.last equal index is ", findLastEqual(arr, des))

}
