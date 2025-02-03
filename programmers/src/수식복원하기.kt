import kotlin.math.max
import kotlin.math.pow

// https://school.programmers.co.kr/learn/courses/30/lessons/340210

class SolutionUnknownMethod {
    fun solution(expressions: Array<String>): Array<String> {
        val numberSystems = ArrayList<Int>(enableNumberSystems(expressions))

        val newExpressions = expressions.map { expression ->
            expression
                .split("=")
                .let { it.first().trim().split(" ") to it.last().trim() }
        }

        for ((method, result) in newExpressions) {
            val numberSystemSize = numberSystems.size
            val disableNumberSystem = mutableSetOf<Int>()
            for (i in 0 until numberSystemSize) {
                if (result != "X") {
                    val enable = enableCalculateNumberSystem(
                        numberSystem = numberSystems[i],
                        method = method,
                        result = result,
                    )
                    if (!enable) {
                        disableNumberSystem.add(numberSystems[i])
                    }
                }
            }
            numberSystems.removeAll(disableNumberSystem)
        }
//        println("사용가능 numberSystems: $numberSystems")

        var answer: Array<String> = arrayOf<String>()
        for ((method, result) in newExpressions.filter { it.second == "X" }) {
            val methodResult = calculateUnknownResultMethods(numberSystems, method)
            answer += method.joinToString(" ") + " = " + methodResult
        }

        return answer
    }

    private fun enableNumberSystems(expressions: Array<String>): List<Int> {
        var maxDigit = 0
        for (expression in expressions) {
            for (char in expression) {
                try {
                    val digit = char.digitToInt()
                    maxDigit = max(digit, maxDigit)
                } catch (e: IllegalArgumentException) {
                    continue
                }
            }
        }

        return (maxDigit + 1..9).toList()
    }

    private fun Int.pow(n: Int): Int {
        return this.toDouble().pow(n).toInt()
    }

    private val operators = listOf("+", "-")

    private fun String.toDecimalSystem(numberSystem: Int): Int {
        var result = 0
        reversed().forEachIndexed { index, c ->
            result += c.digitToInt() * numberSystem.pow(index)
        }
        return result
    }

    private fun calculateNumberSystemMethodToDecimalValue(numberSystem: Int, method: List<String>): Int {
        var result = 0
        var operator = "+"
        var temp = 0
        for (m in method) {
            if (m in operators) {
                result = calculateOperator(
                    result,
                    temp,
                    operator,
                )
                operator = m
                temp = 0
            } else {
                temp += m.toDecimalSystem(numberSystem)
            }
        }
        return calculateOperator(
            result,
            temp,
            operator,
        )
    }

    private fun calculateNumberSystemMethod(numberSystem: Int, method: List<String>): String {
        var methodDecimalResult = calculateNumberSystemMethodToDecimalValue(numberSystem, method)
        var result = ""
        do {
            result += methodDecimalResult % numberSystem
            methodDecimalResult /= numberSystem
        } while (methodDecimalResult != 0)

        result = result.reversed()
        if (result.startsWith("0") && result != "0") {
            result = result.drop(1)
        }

        return result
    }

    private fun enableCalculateNumberSystem(numberSystem: Int, method: List<String>, result: String): Boolean {
        val methodDecimalResult = calculateNumberSystemMethodToDecimalValue(numberSystem, method)
        val decimalResult = result.toDecimalSystem(numberSystem)

        return methodDecimalResult == decimalResult
    }

    private fun calculateOperator(result: Int, number: Int, operator: String): Int {
        return when (operator) {
            "+" -> result + number
            "-" -> result - number
            else -> result
        }
    }

    private fun calculateUnknownResultMethods(numberSystems: List<Int>, method: List<String>): String {
        var result: String? = null
        for (numberSystem in numberSystems) {
            val numberSystemResult = calculateNumberSystemMethod(numberSystem, method)
            if (result == null) {
                result = numberSystemResult
            } else if (result != numberSystemResult){
                return "?"
            }
//            println("calculate ${numberSystem}진법 $method = $numberSystemResult")
        }

        return result.toString()
    }
}