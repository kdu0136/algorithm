package main

import (
	"fmt"
	"math"
)

var DP []int

func main() {
	var t int

	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		fmt.Println(err)
		return
	}

	var inputN = make([]int, t)
	var max int
	for i := range inputN {
		if _, err := fmt.Scanf("%d", &inputN[i]); err != nil {
			fmt.Println(err)
			return
		}

		// 범위 예외
		if inputN[i] < 0 || inputN[i] > 41 {
			return
		}

		max = int(math.Max(float64(max), float64(inputN[i])))
	}

	//fibonacci DP init
	DP = make([]int, max+1)
	fibonacci(max)
	DP = append([]int{1}, DP...)

	for _, n := range inputN {
		fmt.Println(DP[n], DP[n+1])
	}
}

// fibonacci function
func fibonacci(n int) int {
	if n == 0 {
		return n
	}

	if DP[n] == 0 {
		if n == 1 {
			DP[n] = n
		} else {
			DP[n] = fibonacci(n-1) + fibonacci(n-2)
		}
	}

	return DP[n]
}
