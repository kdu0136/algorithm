
// https://school.programmers.co.kr/learn/courses/30/lessons/181832

enum class Step {
    RIGHT,
    DOWN,
    LEFT,
    UP,
}

data class Points(
    var step: Step,
    var columnIndex: Int,
    var rowIndex: Int,
)

class SolutionNtoSpiral {
    private fun Points.calculateNextPoints(board: Array<IntArray>) {
        when (step) {
            Step.RIGHT -> {
                rowIndex += 1

                try {
                    if (board[columnIndex][rowIndex] != 0) {
                        step = Step.DOWN
                        rowIndex -= 1
                        columnIndex += 1
                    }
                } catch (e: IndexOutOfBoundsException) {
                    step = Step.DOWN
                    rowIndex -= 1
                    columnIndex += 1
                }
            }

            Step.DOWN -> {
                columnIndex += 1

                try {
                    if (board[columnIndex][rowIndex] != 0) {
                        step = Step.LEFT
                        columnIndex -= 1
                        rowIndex -= 1
                    }
                } catch (e: IndexOutOfBoundsException) {
                    step = Step.LEFT
                    columnIndex -= 1
                    rowIndex -= 1
                }
            }

            Step.LEFT -> {
                rowIndex -= 1

                try {
                    if (board[columnIndex][rowIndex] != 0) {
                        step = Step.UP
                        rowIndex += 1
                        columnIndex -= 1
                    }
                } catch (e: IndexOutOfBoundsException) {
                    step = Step.UP
                    rowIndex += 1
                    columnIndex -= 1
                }
            }

            Step.UP -> {
                columnIndex -= 1

                try {
                    if (board[columnIndex][rowIndex] != 0) {
                        step = Step.RIGHT
                        columnIndex += 1
                        rowIndex += 1
                    }
                } catch (e: IndexOutOfBoundsException) {
                    step = Step.RIGHT
                    columnIndex += 1
                    rowIndex += 1
                }
            }
        }
    }

    fun solution(n: Int): Array<IntArray> {
        val temp = Array(n) { IntArray(n) { 0 } }

        val points = Points(Step.RIGHT, 0, 0)

        for (value in 1..n * n) {
            temp[points.columnIndex][points.rowIndex] = value

            points.calculateNextPoints(temp)
        }

        return temp
    }
}