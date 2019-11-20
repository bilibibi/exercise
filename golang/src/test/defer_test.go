package test

import (
	"fmt"
	"testing"
)

func surroundingFuncEvaluatedNotInvoked(init int) int {
	fmt.Printf("1.init=%d\n", init)

	defer func() {
		fmt.Printf("2.init=%d\n", init)

		init++

		fmt.Printf("3.init=%d\n", init)
	}()

	fmt.Printf("4.init=%d\n", init)

	return init
}

func noDeferFuncOrderWhenReturn() (result int) {
	func() {
		// 1. before : result = 0
		fmt.Printf("before : result = %v\n", result)

		result++

		// 2. after : result = 1
		fmt.Printf("after : result = %v\n", result)
	}()

	// 3. return : result = 1
	fmt.Printf("return : result = %v\n", result)

	return result
}

func deferFuncWithAnonymousReturnValue() int {
	var retVal int
	defer func() {
		retVal++
	}()
	return retVal
}

func deferFuncWithNamedReturnValue() (retVal int) {
	defer func() {
		retVal++
	}()
	return retVal
}

func TestDeferFuncWhenReturn(t *testing.T) {
	t.Log(surroundingFuncEvaluatedNotInvoked(1))
	t.Log(noDeferFuncOrderWhenReturn())
	t.Log(deferFuncWithAnonymousReturnValue())
	t.Log(deferFuncWithNamedReturnValue())
}
