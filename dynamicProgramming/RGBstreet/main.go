package main

import (
	"fmt"
	"math"
)

var housePrice, minSumDP [][3]int

func main() {
	var n int

	//집의 수 입력
	if _, err := fmt.Scanf("%d", &n); err != nil {
		fmt.Println(err)
		return
	} else if n < 1 || n > 1000 { // 범위 예외
		return
	}

	housePrice = make([][3]int, n)
	minSumDP = make([][3]int, n)
	for i := range housePrice {
		// 집의 가격(R G B) 입력
		if _, err := fmt.Scanf("%d %d %d", &housePrice[i][0], &housePrice[i][1], &housePrice[i][2]); err != nil {
			fmt.Println(err)
			return
		} else if housePrice[i][0] < 1 || housePrice[i][0] > 1000 ||
			housePrice[i][1] < 1 || housePrice[i][1] > 1000 ||
			housePrice[i][2] < 1 || housePrice[i][2] > 1000 { // 범위 예외
			return
		}
	}

	minSum(len(housePrice) - 1)
	fmt.Println(min(len(housePrice) - 1))
}

// min - minSumDP 의 index n 값들 중 작은 수 찾는 함수
func min(n int) int {
	return int(math.Min(math.Min(float64(minSumDP[n][0]), float64(minSumDP[n][1])), float64(minSumDP[n][2])))
}

// minSum - minSumDP 값 구하는 함수
func minSum(n int) {
	if n == 0 {
		minSumDP[n][0] = housePrice[n][0]
		minSumDP[n][1] = housePrice[n][1]
		minSumDP[n][2] = housePrice[n][2]
		return
	}
	if minSumDP[n-1][0] == 0 || minSumDP[n-1][1] == 0 || minSumDP[n-1][2] == 0 {
		minSum(n - 1)
	}
	minSumDP[n][0] = housePrice[n][0] + int(math.Min(float64(minSumDP[n-1][1]), float64(minSumDP[n-1][2])))
	minSumDP[n][1] = housePrice[n][1] + int(math.Min(float64(minSumDP[n-1][0]), float64(minSumDP[n-1][2])))
	minSumDP[n][2] = housePrice[n][2] + int(math.Min(float64(minSumDP[n-1][0]), float64(minSumDP[n-1][1])))
	return
}
