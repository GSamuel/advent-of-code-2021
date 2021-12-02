fun main() {
    ProblemDay02().solve()
}

class ProblemDay02 : Problem<Int>("Day02") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 150)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 900)
    }

    override fun part1(input: List<String>): Int {
        return input.map { it.toMove() }.fold(Pos(0,0)) {acc, move -> move.move(acc)}.multiply()
    }

    override fun part2(input: List<String>): Int {
        return input.map { it.toMove() }.fold(AimedPos()) { acc, move -> move.aim(acc)}.pos.multiply()
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

        fun aim(p:AimedPos):AimedPos = when (direction) {
            "forward" -> AimedPos(Pos(p.pos.x + value, p.pos.y + p.aim * value), p.aim)
            "down" -> AimedPos(p.pos, p.aim + value)
            "up" -> AimedPos(p.pos, p.aim - value)
            else -> error("invalid direction")
        }
    }

    data class Pos(val x: Int, val y: Int) {
        fun multiply() = x * y
    }

    data class AimedPos(val pos:Pos = Pos(0,0), val aim:Int = 0)
}