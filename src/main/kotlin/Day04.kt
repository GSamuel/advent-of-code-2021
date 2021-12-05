fun main() {
    ProblemDay04().solve()
}

class ProblemDay04 : Problem<Int>("Day04") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 4512)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 1924)
    }

    override fun part1(input: List<String>): Int {
        val sequence = input.first().sequence()
        val bingoCards = input.subList(2, input.size).toBingoCards()

        var last:Int = 0
        sequence.forEach { next ->
            if(bingoCards.none { it.hasWon() }) {
                bingoCards.forEach { card ->
                    card.mark(next)
                }
                last = next
            }
        }

        val winner = bingoCards.first { it.hasWon() }

        return last * winner.countRemaining()
    }

    override fun part2(input: List<String>): Int {
        val sequence = input.first().sequence()
        val bingoCards = input.subList(2, input.size).toBingoCards()

        var remainingCards = bingoCards
        var last:Int = 0
        sequence.forEach { next ->
            if(remainingCards.size > 1) {
                remainingCards.forEach { card ->
                    card.mark(next)
                }
                remainingCards = remainingCards.filter { !it.hasWon() }
                last = next
            } else if(!remainingCards.first().hasWon()) {
                remainingCards.first().mark(next)
                last = next
            }
        }

        val winner = remainingCards.first { it.hasWon() }
        return last * winner.countRemaining()
    }

    private fun List<String>.toBingoCards(): List<BingoCard> {
        return split().map { it.toBingoCard() }
    }

    private fun List<String>.toBingoCard(): BingoCard {
        return BingoCard(map { row -> row.split(" ").filter { it.isNotBlank() }.map { it.toInt() } })
    }

    private fun String.sequence():Sequence<Int> = split(",").map { it.toInt() }.asSequence()

    data class BingoCard(val numbers: List<List<Int>>) {
        private val marks: MutableSet<Int> = mutableSetOf()

        fun hasWon(): Boolean {
            return (0 until height()).any { allMarked(Vector(0, it), Vector.RIGHT) } ||
                    (0 until width()).any { allMarked(Vector(it, 0), Vector.DOWN) }
        }

        fun mark(n:Int) {
            marks.add(n)
        }

        fun countRemaining():Int {
            return (0 until height()).sumOf { y ->
                (0 until width()).sumOf { x ->
                    if(isMarked(Vector(x,y)))
                        0
                    else
                        number(Vector(x,y))

                }
            }
        }

        private fun allMarked(start: Vector, dir: Vector): Boolean {
            return generateSequence(start) { (it + dir).onBoard() }.all { isMarked(it) }
        }

        fun isMarked(pos:Vector) = marks.contains(number(pos))

        fun number(pos: Vector) = numbers[pos.y][pos.x]

        private fun Vector.onBoard(): Vector? {
            if (onBoard(this))
                return this
            return null
        }

        private fun onBoard(pos: Vector): Boolean {
            return pos.x >= 0 && pos.y >= 0 && pos.x < width() && pos.y < height()
        }

        private fun width() = numbers.first().size
        private fun height() = numbers.size
    }

    data class Vector(val x: Int, val y: Int) {
        operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

        companion object {
            val ZERO = Vector(0, 0)
            val RIGHT = Vector(1, 0)
            val DOWN = Vector(0, 1)
        }
    }
}