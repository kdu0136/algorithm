package main

import (
	"fmt"
	"math"
)

func main() {
	var n int

	fmt.Print("정수 n 입력(1 <= n <= 10^6):")
	if _, err := fmt.Scanf("%d", &n); err != nil {
		fmt.Println(err)
		return
	}

	if n < 1 || n > int(math.Pow10(6)) {
		fmt.Println("범위 오류")
		return
	}

	fmt.Print(createOneDP(n))
}

// min - x, y 중 작은 수 찾는 함수 (x 가 0일경우 무조건 y)
func min(x int, y int) int {
	if x == 0 {
		return y
	}
	return int(math.Min(float64(x), float64(y)))
}

// createOneDP - 1로 만들수 있는 방법 수 찾기 (Bottom-up)
func createOneDP(n int)  int {
	var DP = make([]int, n+1) // 입력 받은 n+1 만큼 array 할당
	for i := 1; i <= n; i++ {
		if i + 1 <= n { // i+1 array 값 계산
			DP[i+1] = min(DP[i+1], DP[i]+1)
		}
		if i * 2 <= n { // i*2 array 값 계산
			DP[i*2] = min(DP[i*2], DP[i]+1)
		}
		if i * 3 <= n { // i*3 array 값 계산
			DP[i*3] = min(DP[i*3], DP[i]+1)
		}
	}
	return DP[n]
}
