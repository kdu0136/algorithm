import kotlin.math.ceil
import kotlin.math.max

class Input(
    val diffs: IntArray,
    val times: IntArray,
    val limit: Long,
    val output: Int,
)

fun main() {
    val s = Solution()

    val inputs = listOf(
        Input(
            intArrayOf(1, 5, 3),
            intArrayOf(2, 4, 7),
            30,
            3,
        ),
        Input(
            intArrayOf(1, 4, 4, 2),
            intArrayOf(6, 3, 8, 2),
            59,
            2,
        ),
        Input(
            intArrayOf(1, 328, 467, 209, 54),
            intArrayOf(2, 7, 1, 4, 3),
            1723,
            294,
        ),
//        Input(
//            intArrayOf(1, 99999, 100000, 99995),
//            intArrayOf(9999, 9001, 9999, 9001),
//            3456789012,
//            39354,
//        ),
//        Input(
//            intArrayOf(1, 1, 3),
//            intArrayOf(1, 1, 3),
//            50,
//            1,
//        ),
    )

    inputs.forEach { input ->
        val result = s.solution(input.diffs, input.times, input.limit)
        println("output: ${result}, expected output: ${input.output} => ${result == input.output}\n")
    }
}

class Solution {
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        val timeSums = times.mapIndexed { index, time ->
            if (index == 0) time
            else {
                times[index - 1] + time
            }
        }
//        println("timeSums: ${timeSums.joinToString(", ")}")
//        println("diffs: ${diffs.joinToString(", ")}")
//        println("times: ${times.joinToString(", ")}")
//        println("limit: $limit\n")

        var minLevel = diffs.minOf { it }
        var maxLevel = diffs.maxOf { it }
        var level: Int
        var enableLevel = 1
        do {
            level = minLevel + (maxLevel - minLevel) / 2
            println("maxLevel: $maxLevel, minLevel: $minLevel")
            val enable = enableLevel(diffs, times, timeSums, limit, level)
            println("level: $level is enable: $enable\n")
            if (minLevel == level) {
                enableLevel = if (enable) {
                    level
                } else {
                    maxLevel
                }
                break
            }

            if (enable) {
                maxLevel = level
            } else {
                minLevel = level + 1
            }
        } while (true)

        return enableLevel
    }

    private fun enableLevel(diffs: IntArray, times: IntArray, timeSums: List<Int>, limit: Long, level: Int): Boolean {
//        println("level: $level")
        var sum: Long = timeSums.first().toLong()
//        println("sum: $sum")
        for (i in 1 until timeSums.size) {
            sum += if (diffs[i] <= level) {
                times[i]
            } else {
                (diffs[i] - level) * timeSums[i] + times[i]
            }
//            println("sum: $sum")
        }

        return sum <= limit
    }
}

