// https://school.programmers.co.kr/learn/courses/30/lessons/181187

class SolutionDotsBetweenTwoCircle {
    fun solution(r1: Int, r2: Int): Long {
        return circleInnerDotsCount(
            r2.toLong(),
            includeLine = true,
        ) - circleInnerDotsCount(
            r1.toLong(),
            includeLine = false
        )
    }

    private fun circleInnerDotsCount(r: Long, includeLine: Boolean): Long {
        var dotsCount = 0L
        val powOfR = r * r
        var maxY = r

        for (x in 1L..r) {
            for (y in maxY downTo 0) {
                if (isInnerDot(x, y, powOfR, includeLine)) {
                    dotsCount += y + 1
                    maxY = y
                    break
                }
            }
        }
        dotsCount *= 4
        dotsCount += 1 // 가운데 (0,0)

        return dotsCount
    }

    private val isInnerDot = fun(x: Long, y: Long, powOfR: Long, includeLine: Boolean): Boolean {
        return if (includeLine) y * y <= powOfR - x * x else y * y < powOfR - x * x
    }
}