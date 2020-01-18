package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

var triangle [][]int
var sumMax [][]int

func main() {
	var t int

	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		panic(err)
	} else if t < 1 || t > 500 { // 범위 예외
		panic("out of range")
	}

	triangle = make([][]int, t)
	sumMax = make([][]int, t)

	reader := bufio.NewReader(os.Stdin)
	for i := range triangle {
		str, _ := reader.ReadString('\n')

		triangle[i] = make([]int, i+1)
		sumMax[i] = make([]int, i+1)

		for index, v := range strings.Fields(str) {
			triangle[i][index], _ = strconv.Atoi(v)
		}
	}
	sumMax[t-1] = triangle[t-1]

	for i := t - 2; i >= 0; i-- {
		calSumMax(i)
	}
	fmt.Println(sumMax[0][0])
}

// calSumMax - sumMax array 값 계산
func calSumMax(n int) {
	for i := range sumMax[n] {
		sumMax[n][i] = triangle[n][i] + int(math.Max(float64(sumMax[n+1][i]), float64(sumMax[n+1][i+1])))
	}
}
