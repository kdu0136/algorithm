package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var continuousInt []int

func main() {
	var t int

	// 테스트 케이스 입력
	if _, err := fmt.Scanf("%d", &t); err != nil {
		panic(err)
	} else if t < 0 || t > 100000 { // 범위 예외
		panic("out of range")
	}

	continuousInt = make([]int, t)

	reader := bufio.NewReader(os.Stdin)
	str, _ := reader.ReadString('\n')

	for index, v := range strings.Fields(str) {
		if index >= len(continuousInt) {
			break
		}
		continuousInt[index], _ = strconv.Atoi(v)
	}

	fmt.Println(continuousInt)
}
