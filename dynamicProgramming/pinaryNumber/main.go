package main

import (
	"fmt"
)

var DP []int
func main() {
	var n int

	if _, err := fmt.Scanf("%d", &n); err != nil {
		fmt.Println(err)
		return
	}
	// 범위 예외
	if n < 0 || n > 90 {
		return
	}

	DP = make([]int, n+1)
	pinaryNumber(n)
	fmt.Println(DP[n])
}

// pinary number count function
func pinaryNumber(n int) int {
	if n == 0 {
		return n
	}

	if DP[n] == 0 {
		if n == 1 {
			DP[n] = n
		} else {
			DP[n] = pinaryNumber(n - 1) + pinaryNumber(n - 2)
		}
	}

	return DP[n]
}

