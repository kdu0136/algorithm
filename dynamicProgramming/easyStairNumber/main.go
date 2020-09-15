package main

import (
	"fmt"
)

var DP [][]int

func main() {
	var t int

	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		panic(err)
	} else if t < 1 || t > 100 { // 범위 예외
		panic("out of range")
	}

	DP = make([][]int, t)

	DP[0] = []int{1,2,3,4,5,6,7,8,9}

	//fmt.Println(len(DP[0]))
	for i := 1; i < t; i++ {
		var cnt = 0
		for _, t := range DP[i-1] {
			if t == 0 {
				cnt++
				DP[i] = append(DP[i], t + 1)
			} else if t == 9 {
				cnt++
				DP[i] = append(DP[i], t - 1)
			} else {
				DP[i] = append(DP[i], t + 1)
				DP[i] = append(DP[i], t - 1)
			}
		}
		fmt.Println(cnt, "/", len(DP[i]))
		cnt = 0
		//fmt.Println(i,"end length", len(DP[i]))
	}
	fmt.Println("end")

	//for _, t := range DP {
	//	fmt.Println(len(t))
	//}
}
