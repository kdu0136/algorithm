
// https://school.programmers.co.kr/learn/courses/30/lessons/340212?language=kotlin

class SolutionPuzzle {
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        // times[i] + time[i-1] 값 계산
        val timeSums = times.mapIndexed { index, time ->
            if (index == 0) time
            else {
                times[index - 1] + time
            }
        }

        // step1. 퍼즐의 난이도 min, max 찾기
        var minLevel = diffs.minOf { it }
        var maxLevel = diffs.maxOf { it }
        var level: Int
        var result = 1
        do {
            // step2. 중간값 찾기
            level = minLevel + (maxLevel - minLevel) / 2
            val enable = enableLevel(diffs, times, timeSums, limit, level)
            // 계산한 중간값이 현재 min 값이랑 동일하면 반복 중단
            if (minLevel == level) {
                // 만족하면 현재 중간값, 만족하지 않으면 max값이 찾고자하는 최소 level
                result = if (enable) {
                    level
                } else {
                    maxLevel
                }
                break
            }

            // step3. 수식에 만족하면 max를 중간값, 만족하지 않는다면 min을 중간값+1로 설정
            if (enable) {
                maxLevel = level
            } else {
                minLevel = level + 1
            }
        } while (true)

        return result
    }

    // 수식 계산
    private fun enableLevel(diffs: IntArray, times: IntArray, timeSums: List<Int>, limit: Long, level: Int): Boolean {
        var sum: Long = timeSums.first().toLong()
        for (i in 1 until timeSums.size) {
            sum += if (diffs[i] <= level) {
                times[i]
            } else {
                (diffs[i] - level) * timeSums[i] + times[i]
            }
        }

        return sum <= limit
    }
}