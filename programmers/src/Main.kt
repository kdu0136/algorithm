class Input(
    val targets: Array<IntArray>,
    val output: Int,
)

fun main() {
    val s = Solution()

    val inputs = listOf(
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
            ),
            3,
        ),
        Input(
            arrayOf(
                intArrayOf(3, 7),
                intArrayOf(11, 13),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(11, 13),
                intArrayOf(4, 5),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(11, 13),
                intArrayOf(4, 5),
                intArrayOf(1, 3),
                intArrayOf(3, 4),
            ),
            4,
        ),
        Input(
            arrayOf(
                intArrayOf(11, 13),
                intArrayOf(4, 5),
                intArrayOf(1, 3),
                intArrayOf(3, 4),
                intArrayOf(4, 10),
            ),
            4,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(15, 16),
            ),
            4,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(15, 16),
                intArrayOf(16, 18),
            ),
            5,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3),
                intArrayOf(1, 10),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 2),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(14, 15),
            ),
            4,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(12, 13),
            ),
            4,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(12, 20),
            ),
            4,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(11, 13),
            ),
            3,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(4, 7),
                intArrayOf(8, 9),
            ),
            3,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 11),
                intArrayOf(10, 15),
                intArrayOf(10, 19),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 11),
                intArrayOf(10, 15),
                intArrayOf(10, 19),
                intArrayOf(17, 19),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(10, 14),
                intArrayOf(11, 13),
                intArrayOf(5, 12),
                intArrayOf(3, 7),
                intArrayOf(1, 4),
                intArrayOf(4, 100),
            ),
            3,
        ),
        Input(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 100),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(0, 1),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(15, 18),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(15, 20),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(19, 20),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(20, 21),
            ),
            2,
        ),
        Input(
            arrayOf(
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(10, 20),
                intArrayOf(9, 21),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 4),
                intArrayOf(2, 5),
                intArrayOf(2, 3),
            ),
            1,
        ),
        Input(
            arrayOf(
                intArrayOf(1, 4),
                intArrayOf(2, 5),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
            ),
            2,
        ),
    )

    inputs.forEach { input ->
        val result = s.solution(input.targets)
        println("output: ${result}, expected output: ${input.output} => ${result == input.output}")
    }
    val input = Input(
        arrayOf(
            intArrayOf(8, 10),
            intArrayOf(1, 3),
            intArrayOf(9, 11),
            intArrayOf(1, 10),
            intArrayOf(1, 9),
        ),
        2,
    )
    val result = s.solution(input.targets)
    println("output: ${result}, expected output: ${input.output} => ${result == input.output}")
}

class Solution {
    fun solution(targets: Array<IntArray>): Int {
        val newTargets = targets
            .sortedWith(compareBy({ it.first() }, { it.last() - it.first() }))//, { it.first() }))
            .toMutableList()

        newTargets.forEach {
            println("${it.joinToString(",")} size: ${it.last() - it.first()}")
        }

        var index = 0
        var point = 1
        while (index < newTargets.size) {
            if (point <= newTargets[index].first()) {
                index++
            }
            point++
        }



        var count = 0
        while (newTargets.isNotEmpty()) {
//            newTargets.forEach {
//                println("${it.joinToString(",")} size: ${it.last() - it.first()}")
//            }
            val aimTarget = newTargets.firstOrNull()
//            println("aimTarget: ${aimTarget?.joinToString(",")}")
            if (aimTarget != null) {
                val points = aimTarget.first() until aimTarget.last()
                var shotTargets = listOf<IntArray>()
                var shotPoint = 0
                for (point in points) {
                    val tempTargets = newTargets.filter {
                        point in it.first() until it.last()
                    }
//                    println("tmpShotPoint: $point, tempTargets: ${tempTargets.map { it.toList() }.joinToString(",")}")

                    if (tempTargets.size >= shotTargets.size) {
                        shotTargets = tempTargets
                        shotPoint = point
                    }

                    if (shotTargets.size == newTargets.size) {
                        break
                    }
                }
//                println("shotPoint: $shotPoint, shotTargets: ${shotTargets.map { it.toList() }.joinToString(",")}")
                newTargets.removeAll(shotTargets)
                count++
            }
        }

        return count
    }
}

