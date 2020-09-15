package main

import (
	"fmt"
)

var DP []int

func main() {
	var t int
	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		panic(err)
	} else if t < 1 || t > 45 { // 범위 예외
		panic("out of range")
	}

	DP = []int{1,0} // t = 0, A = 1 b = 0 (A)

	for i := 1; i < t + 1; i++ {
		DP = []int{DP[1], DP[1] + DP[0]}
	}
	fmt.Printf("%d %d\n", DP[0], DP[1])
}
