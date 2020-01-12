package main

import (
	"fmt"
	"math"
)

var price [][3]int
func main() {
	//var n int
	//
	//// 집의 수 입력
	//if _, err := fmt.Scanf("%d", &n); err != nil {
	//	fmt.Println(err)
	//	return
	//} else if n < 1 || n > 1000 { // 범위 예외
	//	return
	//}

	price = [][3]int{{26, 40, 83}, {49, 60, 57}, {13, 89, 99}}
	//price = make([][3]int, n)
	//for i := range price {
	//	// 집의 가격(R G B) 입력
	//	if _, err := fmt.Scanf("%d %d %d", &price[i][0], &price[i][1], &price[i][2]); err != nil {
	//		fmt.Println(err)
	//		return
	//	} else if n < 1 || n > 1000 { // 범위 예외
	//		return
	//	}
	//}

	fmt.Println(price)
	fmt.Println(houseUnder4(len(price)))
}

func min3(a int, b int, c int) int {
	return min2(a, min2(b, c))
}

func min2(a int, b int) int {
	if a < 0 {
		return b
	} else if b < 0 {
		return a
	}
	return int(math.Min(float64(a), float64(b)))
}

func houseUnder4(n int) (min int){
	if n < 1 {
		return 0
	}
	if n < 2 {
		min = min3(price[0][0], price[0][1], price[0][2])
		return
	}
	sum := make([]int, 6)

	sum[0] = price[0][1] + price[1][0]
	sum[1] = price[0][2] + price[1][0]
	sum[2] = price[0][0] + price[1][1]
	sum[3] = price[0][2] + price[1][1]
	sum[4] = price[0][0] + price[1][2]
	sum[5] = price[0][1] + price[1][2]

	min = -1
	if n < 3 {
		for i := range sum {
			min = min2(min, sum[i])
		}
		return
	}

	for i := range sum {
		var case1, case2 int
		switch i/2 {
		case 0:
			case1 = price[2][1]
			case2 = price[2][2]
		case 1:
			case1 = price[2][0]
			case2 = price[2][2]
		case 2:
			case1 = price[2][0]
			case2 = price[2][1]
		}
		fmt.Println(sum[i], "+", case1, "=", sum[i] + case1)
		fmt.Println(sum[i], "+", case2, "=", sum[i] + case2)
		min = min3(min, sum[i] + case1, sum[i] + case2)
		fmt.Println("min:", min)
	}

	return
}
