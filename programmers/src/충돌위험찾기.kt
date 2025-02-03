import kotlin.math.abs
import kotlin.math.max

// https://school.programmers.co.kr/learn/courses/30/lessons/340211

data class Point(
    val r: Int,
    val c: Int,
) {
    fun calculateRoutes(end: Point): List<Point> {
        val routes = arrayListOf(this)

        val rDiff = abs(r - end.r)
        for (i in 1..rDiff) {
            val current = routes.last()
            if (r > end.r) {
                routes.add(current.copy(r = current.r - 1))
            } else {
                routes.add(current.copy(r = current.r + 1))
            }
        }

        val cDiff = abs(c - end.c)
        for (i in 1..cDiff) {
            val current = routes.last()
            if (c > end.c) {
                routes.add(current.copy(c = current.c - 1))
            } else {
                routes.add(current.copy(c = current.c + 1))
            }
        }

        return routes
    }
}

data class Robot(
    val routes: List<Point>,
)

class SolutionCrash {
    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        var maxRoutes = 0
        val robots = routes.map { route ->
            val robotPoints = route.map {
                val point = points[it - 1]
                Point(point.first(), point.last())
            }
            val robotRoutes = (0  until robotPoints.size - 1).flatMap {
                val result = robotPoints[it].calculateRoutes(robotPoints[it + 1])
                if (it != 0) result.drop(1)
                else result
            }

            maxRoutes = max(robotRoutes.size, maxRoutes)

            Robot(robotRoutes)
        }

        var count = 0
        for (i in 0 until maxRoutes) {
            val map = HashMap<Point, Unit>()
            val crash = mutableSetOf<Point>()
            for (robot in robots) {
                try {
                    val point = robot.routes[i]
                    if (map[point] == null) {
                        map[point] = Unit
                    } else {
                        crash.add(point)
                    }
                } catch (e: IndexOutOfBoundsException) {
                    continue
                }
            }
            count += crash.size
        }

        return count
    }
}