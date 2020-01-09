package main

import (
	"fmt"
	"math"
)

var stair, maxDP []int

func main() {
	var t int

	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		fmt.Println(err)
		return
	} else if t < 1 || t > 300 { // 범위 예외
		return
	}

	stair = make([]int, t)
	maxDP = make([]int, t)
	for i := range stair {
		if _, err := fmt.Scanf("%d", &stair[i]); err != nil {
			fmt.Println(err)
			return
		} else if stair[i] < 1 || stair[i] > 10000 { // 범위 예외
			return
		}
		maxDP[i] = -1
	}
	stair = append([]int{0}, stair...)
	maxDP = append([]int{0}, maxDP...)

	fmt.Println(maxStairSum(t))
}

func maxStairSum(n int) int {
	if n > 2 { // n 이 2보다 클 경우 -> 2가지 케이스 고려
		if maxDP[n-2] < 0 {
			maxDP[n-2] = maxStairSum(n - 2)
		}
		if maxDP[n-3] < 0 {
			maxDP[n-3] = maxStairSum(n - 3)
		}

		// case1: n + (n-1) + (n-3)계단 까지 최대 합 -> n, n-1 계단 밟았으므로 n-2 계단 불가
		case1 := float64(stair[n] + stair[n-1] + maxDP[n-3])
		// case1: n + (n-2)계단 까지 최대 합
		case2 := float64(stair[n] + maxDP[n-2])

		if maxDP[n] < 0 {
			maxDP[n] = int(math.Max(case1, case2))
		}
		return maxDP[n]
	}

	if n == 2 {
		maxDP[n] = stair[n] + stair[n-1]
	} else {
		maxDP[n] = stair[n]
	}

	return maxDP[n]
}

