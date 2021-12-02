fun main() {
    ProblemDay02().solve()
}

class ProblemDay02 : Problem<Int>("Day02") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 150)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 3)
    }

    override fun part1(input: List<String>): Int {
        return input.map { it.toMove() }.fold(Pos(0,0)) {acc, move -> move.move(acc)}.multiply()
    }

    override fun part2(input: List<String>): Int {
        return 0
    }

    private fun String.toMove(): Move {
        val (dir, value) = split(" ")
        return Move(dir, value.toInt())
    }

    data class Move(val direction: String, val value: Int) {
        fun move(pos: Pos): Pos = when (direction) {
            "forward" -> Pos(pos.x + value, pos.y)
            "down" -> Pos(pos.x, pos.y + value)
            "up" -> Pos(pos.x, pos.y - value)
            else -> error("invalid direction")
        }
    }

    data class Pos(val x: Int, val y: Int) {
        fun multiply() = x * y
    }
}