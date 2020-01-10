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
	} else if n < 1 || n > 1000 { // 범위 예외
		return
	}

	// tiling way sum DP init
	DP = make([]int, n + 1)

	tilingSum(n)
	fmt.Println(DP[n])
}

// 2xn tiling way sum function
func tilingSum(n int) int {
	if n == 0 {
		return n
	}

	if DP[n] == 0 {
		if n <= 2 {
			DP[n] = n
		} else {
			DP[n] = (tilingSum(n - 1) + tilingSum(n - 2)) % 10007
		}
	}

	return DP[n]
}
