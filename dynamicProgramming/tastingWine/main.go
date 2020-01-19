package main

import (
	"fmt"
	"math"
)

var wine, maxDP []int

func main() {
	var t int
	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		fmt.Println(err)
		return
	} else if t < 1 || t > 10000 { // 범위 예외
		return
	}

	wine = make([]int, t)
	maxDP = make([]int, t)
	for i := range wine {
		if _, err := fmt.Scanf("%d", &wine[i]); err != nil {
			fmt.Println(err)
			return
		} else if wine[i] < 0 || wine[i] > 1000 { // 범위 예외
			return
		}
		maxDP[i] = -1
	}

	maxWineSum(t - 1)
	fmt.Print(maxDP[t-1])
}

// max - a,b,c 중 큰 값을 찾아내는 함수
func max(a, b, c int) int {
	return int(math.Max(math.Max(float64(a), float64(b)), float64(c)))
}

// maxWineSum - maxDP 구하는 함수
func maxWineSum(n int) {
	if n == 0 { // 잔이 한개인 경우
		if maxDP[n] == -1 {
			maxDP[n] = wine[n]
		}
		return
	}

	if maxDP[n-1] == -1 {
		maxWineSum(n - 1)
	}
	case1 := maxDP[n-1]

	case2 := wine[n]
	if n > 1 {
		if maxDP[n-2] == -1 {
			maxWineSum(n - 2)
		}
		case2 += maxDP[n-2]
	}

	case3 := wine[n] + wine[n-1]
	if n > 2 {
		if maxDP[n-3] == -1 {
			maxWineSum(n - 3)
		}
		case3 += maxDP[n-3]
	}

	maxDP[n] = max(case1, case2, case3)

	return
}
