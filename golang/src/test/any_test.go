package test

import "testing"

func TestInitSlice(t *testing.T) {
	var x = []int{2: 5, 6, 0: 7}
	t.Log(x)
}

func TestMakeAndCopy(t *testing.T) {
	a := []int{1, 2, 3, 4, 5, 6}
	b := make([]int, len(a))
	copy(b, a)
	t.Log(b)
}

func TestAppend(t *testing.T) {
	a := []int{1, 2, 3, 4, 5, 6}
	b := append(a[:0:0], a...)
	t.Log(b)

}
