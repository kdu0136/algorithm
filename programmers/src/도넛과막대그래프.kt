
// https://school.programmers.co.kr/learn/courses/30/lessons/258711

enum class GraphType {
    DONUT,
    STICK,
    FIGURE_EIGHT,
    ;

    companion object {
        fun findType(nodeCount: Int, lineCount: Int): GraphType {
            return when (nodeCount) {
                lineCount -> DONUT
                lineCount + 1 -> STICK
                else -> FIGURE_EIGHT
            }
        }
    }
}

data class Node(
    val number: Int,
    val forwardNodeNumbers: ArrayList<Int> = arrayListOf(),
    var forwardCount: Int = 0,
    var destinationCount: Int = 0,
)

class SolutionDonutStickGraph {
    fun solution(edges: Array<IntArray>): IntArray {
        val nodesMap = HashMap<Int, Node>()
        for (edge in edges) {
            val startNode = edge.first()
            if (nodesMap[startNode] == null) {
                nodesMap[startNode] = Node(startNode)
            }
            val endNode = edge.last()
            if (nodesMap[endNode] == null) {
                nodesMap[endNode] = Node(endNode)
            }

            nodesMap[startNode]?.run {
                forwardNodeNumbers.add(endNode)
                forwardCount += 1
            }

            nodesMap[endNode]?.run {
                destinationCount += 1
            }
        }

        val lastNode = nodesMap.values.first { it.destinationCount == 0 && it.forwardCount > 1 }
        nodesMap.remove(lastNode.number)
        lastNode.forwardNodeNumbers.forEach {
            nodesMap[it]!!.destinationCount -= 1
        }
//        nodesMap.values.forEach {
//            println(it)
//        }
//        println("graph count: ${lastNode.forwardCount}")
//        println("lastNode: $lastNode")
//        println("endPointNodes: $endPointNodes")

        var stickCount = 0
        var figureEightCount = 0
        for (node in nodesMap.values) {
            when {
                (node.forwardCount == 1 || node.forwardCount == 0)
                        && node.destinationCount == 0 -> stickCount++
                node.forwardCount == 2 && node.destinationCount == 2 -> figureEightCount++
            }
//            println(node)
        }
        val donutCount = lastNode.forwardCount - stickCount - figureEightCount

        return intArrayOf(
            lastNode.number,
            donutCount,
            stickCount,
            figureEightCount,
        )


//        val graphMap = hashMapOf(
//            GraphType.DONUT to 0,
//            GraphType.STICK to 0,
//            GraphType.FIGURE_EIGHT to 0,
//        )

//        println("nodesMap")
//        nodesMap.forEach { (_, node) ->
//            println(node)
//        }

//        do {
//            val initNode = nodesMap.values.find { it.forwardCount > 0 } ?: let {
//                nodesMap.values.first()
//            }
////            if (initNode == null) {
////                break
////            } else {
//            val type = drawGraph(
//                initNode = initNode,
//                nodesMap = nodesMap,
//            )
//            graphMap[type] = graphMap[type]!! + 1
//
////            println("nodesMap after draw")
////            nodesMap.forEach { (_, node) ->
////                println(node)
////            }
//        } while (nodesMap.isNotEmpty())

//        println("graphMap: $graphMap")
//        return intArrayOf(
//            lastNode.number,
//            graphMap[GraphType.DONUT]!!,
//            graphMap[GraphType.STICK]!!,
//            graphMap[GraphType.FIGURE_EIGHT]!!
//        )
    }

    private fun drawGraph(initNode: Node, nodesMap: HashMap<Int, Node>): GraphType {
        var currentNode = initNode
        var lineCount = 0
        val graph = mutableSetOf(currentNode.number)
        val destinationNode = mutableSetOf<Node>()
        println("draw graph")
        print("${currentNode.number}")
        while (true) {
            val nextNode = currentNode.forwardNodeNumbers.removeFirstOrNull()?.let {
                nodesMap[it]
            }
            if (nextNode != null) {
                destinationNode.add(nextNode)
                graph.add(nextNode.number)
                lineCount++
                currentNode = nextNode
                print("->${currentNode.number}")
            } else {
                val existLineNode = destinationNode.find { it.forwardNodeNumbers.isNotEmpty() }
                if (existLineNode != null) {
                    currentNode = existLineNode
                    print(" ${currentNode.number}")
                } else {
                    break
                }
            }
        }
        println()
        graph.forEach { nodesMap.remove(it) }

        val type = GraphType.findType(
            nodeCount = graph.size,
            lineCount = lineCount
        )
        println("nodeCount: ${graph.count()}, lineCount: $lineCount, type: $type\n")

        return type
    }
}