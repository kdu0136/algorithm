
// https://school.programmers.co.kr/learn/courses/30/lessons/135808

class SolutionFruits {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        return score
            .map {
                if (it > k) k
                else it
            }
            .asSequence()
            .sortedDescending()
            .chunked(m)
            .filter { it.size == m }
            .sumOf {
                (it.minOrNull() ?: 0) * m
            }
    }
}