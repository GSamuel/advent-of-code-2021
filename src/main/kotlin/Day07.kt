import kotlin.math.abs

fun main() {
    ProblemDay07().solve()
}

class ProblemDay07 : Problem<Int>("Day07") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 37)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 168)
    }

    override fun part1(input: List<String>): Int {
        return input.toCrabs().lowestFuel(LinearCost)
    }

    override fun part2(input: List<String>): Int {
        return input.toCrabs().lowestFuel(NonLinearCost)
    }

    private fun List<String>.toCrabs() = first().splitToInts()

    private fun List<Int>.lowestFuel(cost:CostFunction) = cost.cost(this, findOptimalPosition(cost))

    private fun List<Int>.findOptimalPosition(cost:CostFunction): Int = (0 until this.maxOf { it }).minByOrNull { cost.cost(this, it) }!!

    private fun String.splitToInts() = split(",").toInts()

    sealed class CostFunction {
        abstract fun cost(value:Int, target:Int): Int
        fun cost(positions:List<Int>, target:Int):Int = positions.sumOf { cost(it, target) }
    }

    object LinearCost:CostFunction() {
        override fun cost(value: Int, target: Int): Int = abs(value - target)
    }

    object NonLinearCost:CostFunction() {
        override fun cost(value: Int, target: Int): Int = cost(abs(value - target))

        private fun cost(n: Int): Int {
            return n * (n+1) /2
        }
    }

}