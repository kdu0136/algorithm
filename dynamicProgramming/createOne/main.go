package main

import (
	"fmt"
)

func main() {
	var n int

	fmt.Print("정수 n 입력(1 < n <= 106):")
	if _, err := fmt.Scanf("%d", &n); err != nil {
		fmt.Println(err)
		return
	}

	if n < 1 || n > 106 {
		fmt.Println("범위 오류")
		return
	}

	fmt.Println("연산 수:", createOne(n))
}

// createOne - n 을 1 로 만드는 연산 task function
func createOne(n int) int {
	task := createOneTask(n) //  n 을 1 로 만드는 연산 task
	for {
		taskCnt, resultN := task()
		if resultN == 1 { // 연산 값이 1이면 연산 수 리턴
			return taskCnt
		}
	}
}

// createOneTask - n 을 1 로 만드는 연산 task function
func createOneTask(n int) func() (int, int) {
	taskCnt := 0 // 연산 수
	return func() (int, int) {
		switch true {
		case n%3 == 0: // 3으로 나누어 떨어지면 3으로 나눈다
			fmt.Printf("%d / 3 = %d\n", n, n/3)
			n /= 3
		case isPow(106, n-1, 3): // n - 1이 3의 x 승이면 1을 뺀다
			fmt.Printf("%d - 1 = %d\n", n, n-1)
			n -= 1
		case n%2 == 0: // 2로 나누어 떨어지면 2로 나눈다
			fmt.Printf("%d / 2 = %d\n", n, n/1)
			n /= 2
		default: // 1을 뺀다
			fmt.Printf("%d -1 = %d\n", n, n-1)
			n -= 1
		}
		taskCnt++ // 연산 수 증가
		return taskCnt, n
	}
}

// makePowNum - expected 값이 powNum 의 x 승 값인지 확인하는 function
func makePowNum(expected int, pow int) func() (int, bool) {
	num := 1
	return func() (int, bool) {
		num *= pow
		if num == expected {
			return num, true
		}
		return num, false
	}
}

// isPow - expected 값이 powNum 의 x 승 값인지 확인하는 function (maxNum 넘으면 무조건 false)
func isPow(maxNum int, expected int, powNum int) bool {
	pow := makePowNum(expected, powNum)
	for {
		num, ok := pow()
		if ok {
			return true
		} else if num > maxNum {
			return false
		}
	}
}
