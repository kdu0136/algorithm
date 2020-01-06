package main

import (
	"fmt"
	"math"
)

func main() {
	var t int

	fmt.Print("테스트 케이스 t:")
	if _, err := fmt.Scanf("%d", &t); err != nil {
		fmt.Println(err)
		return
	}

	var inputN = make([]int, t)
	var max int
	for i := range inputN {
		fmt.Print("정수 n 입력(1 <= n <= 10):")
		if _, err := fmt.Scanf("%d", &inputN[i]); err != nil {
			fmt.Println(err)
			return
		}

		if inputN[i] < 1 || inputN[i] > 10 {
			fmt.Println("범위 오류")
			return
		}

		max = int(math.Max(float64(max), float64(inputN[i])))
	}

	fmt.Println("inputN:", inputN)
	DP := plusOneTwoThreeDP(max)
	for _, n := range inputN {
		fmt.Println(n, "case", DP[n-1])
	}
}

// plusOneTwoThreeDP - 1,2,3 합으로 나타내는 방법 수 찾기
func plusOneTwoThreeDP(max int) []int {
	DP := make([]int, max)
	for i := 0; i < max; i++ {
		switch i {
		case 0:
			DP[i] = 1
		case 1:
			DP[i] = 2
		case 2:
			DP[i] = 4
		default:
			DP[i] = DP[i - 1] + DP[i - 2] + DP[i - 3]
		}
	}
	return DP
}
