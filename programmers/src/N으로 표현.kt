
//https://school.programmers.co.kr/learn/courses/30/lessons/42895

class SolutionN {
    fun solution(N: Int, number: Int): Int {
        if (number == N) return 1

        val temp = Array(2) { arrayOf<String>() }
        temp[0] = arrayOf(N.toString(), (-N).toString())

        temp[1] = temp[0].flatMap {
            createMethod(
                n = N,
                method = it,
            ).toList()
        }.toTypedArray()

        if (isExistValueInResults(value = number, methods = temp[1])) {
            return 2
        }

        for (i in 3..8) {
            var methods = arrayOf<String>()
            temp[1].forEach {
                val tempMethods = createMethod(
                    n = N,
                    method = it
                )
                println("tempMethods: ${tempMethods.joinToString(",")}")
                if (isExistValueInResults(value = number, methods = tempMethods)) {
                    return i
                }

                methods += tempMethods
            }
            temp[1] = methods
            methods = arrayOf()
        }

        return -1
    }

    private fun isExistValueInResults(value: Int, methods: Array<String>): Boolean {
        return methods
            .find { calculate(it) == value } != null
    }

    private fun createMethod(n: Int, method: String): Array<String> {
        val nStr = n.toString()
        var values = arrayOf<String>()
        values += method + nStr
        values += "$method+$n"
        values += "$method-$n"
        values += "$method*$n"
        values += "$method/$n"

        return values
    }

    private val operators = listOf('+', '-', '*', '/')
    private fun calculate(method: String): Int {
        var currentNumber = ""
        var operator = '+'
        var result = 0

        try {
            result = method.toInt()
            return result
        } catch (e: NumberFormatException) {
            for (char in method) {
                if (char in operators && currentNumber.isNotEmpty()) {
                    result = calculate(result, currentNumber.toInt(), operator)
                    currentNumber = ""
                    operator = char
                } else {
                    currentNumber += char
                }
            }
            result = calculate(result, currentNumber.toInt(), operator)
            return result
        }
    }

    private fun calculate(result: Int, number: Int, operator: Char): Int {
        return when (operator) {
            '+' -> result + number
            '-' -> result - number
            '*' -> result * number
            '/' -> result / number
            else -> result
        }
    }
}