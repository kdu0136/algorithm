import kotlin.math.max

// https://school.programmers.co.kr/learn/courses/30/lessons/258707

data class CardCombination(
    val index1: Int,
    val index2: Int,
    val round: Int,
    val useCoin: Int,
)

class SolutionNPlusOneCard {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        val sum = n + 1

        val cardCombinations = ArrayList<CardCombination>()
        val initCards = cards.take(n / 3)

        cards.forEachIndexed { index, card ->
            val indexes = cardCombinations
                .map { it.index1 to it.index2 }
                .flatMap { listOf(it.first, it.second) }

            if (index !in indexes) {
                val index2 = cards.indexOfFirst { card + it == sum }
                var useCoin = 0

                if (card !in initCards) {
                    useCoin++
                }
                if (cards[index2] !in initCards) {
                    useCoin++
                }

                val round = (max(index, index2) - (n / 3)) / 2 + 1

                cardCombinations.add(
                    CardCombination(
                        index1 = index,
                        index2 = index2,
                        round = round,
                        useCoin = useCoin,
                    )
                )
            }
        }

        cardCombinations.forEach {
            println(it)
        }

        val maxRound = n / 3
        var remainCoin = coin
        for (round in 1..maxRound) {
            val temp = cardCombinations.filter { it.round <= round }

            val use = temp.filter { it.useCoin <= remainCoin }.minByOrNull { it.useCoin }
            if (use == null) {
                return round
            }

            remainCoin -= use.useCoin
            cardCombinations.remove(use)
        }

        return maxRound + 1
    }
}