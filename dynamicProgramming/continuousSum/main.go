package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	var t int

	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		panic(err)
	} else if t < 0 || t > 100000 { // 범위 예외
		panic("out of range")
	}

	continuousInt := make([]int, t)

	reader := bufio.NewReader(os.Stdin)
	str, _ := reader.ReadString('\n')
	for index, v := range strings.Fields(str) {
		if index >= len(continuousInt) {
			break
		}
		continuousInt[index], _ = strconv.Atoi(v)
	}

	sum := calSum(continuousInt)
	fmt.Println(findMax(sum))
}

// calSum - values 연속된 합을 구해서 저장한 array 반환
func calSum(values []int) (sum []int) {
	c := -1001
	sum = make([]int, 0)
	for _, value := range values {
		if c < 0 {
			c = value
		} else {
			c += value
			if c < 0 { // 합이 0보다 작을 경우 0으로 리셋
				c = 0
			}
		}
		sum = append(sum, c)
	}
	return
}

// findMax - array 값중 가장 큰 값을 반환
func findMax(sum []int) (max int) {
	max = -1001
	for _, value := range sum {
		if max < value {
			max = value
		}
	}
	return
}
