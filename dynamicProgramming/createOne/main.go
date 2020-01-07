package main

import (
	"fmt"
	"math"
)

func main() {
	var n int

	if _, err := fmt.Scanf("%d", &n); err != nil {
		fmt.Println(err)
		return
	}

	// 범위 예외
	if n < 1 || n > int(math.Pow10(6)) {
		return
	}

	fmt.Print(createOneDP(n))
}

// min - x, y 중 작은 수 찾는 함수 (둘 중 0인 값이 있으면 다른 값 return)
func min(x int, y int) int {
	if x == 0 {
		return y
	} else if y == 0 {
		return x
	}
	return int(math.Min(float64(x), float64(y)))
}

// createOneDP - 1로 만들수 있는 방법 수 찾기 (Bottom-up)
func createOneDP(n int) int {
	DP := make([]int, n+1) // 입력 받은 n+1 만큼 array 할당

	for i := 1; i <= n; i++ {
		var minusOne, divideTwo, divideThree int
		var minusOneDP, divideTwoDP, divideThreeDP int
		if i - 1  != 0 && i - 1 <= n { // i-1 array 값 계산
			minusOne = i - 1
			minusOneDP = DP[minusOne] + 1
		}
		if i % 2 == 0 && i / 2 <= n { // i/2 array 값 계산
			divideTwo = i / 2
			divideTwoDP = DP[divideTwo] + 1
		}
		if i % 3 == 0 &&  i / 3 <= n { // i/3 array 값 계산
			divideThree = i / 3
			divideThreeDP = DP[divideThree] + 1
		}

		switch min(min(minusOne, divideTwo), divideThree) {
		case 0:
			DP[i] = 0
		case 1:
			DP[i] = 1
		default:
			DP[i] = min(min(minusOneDP, divideTwoDP), divideThreeDP)
		}
	}
	return DP[n]
}
