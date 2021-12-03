fun main() {
    ProblemDay01().solve()
}

class ProblemDay01:Problem<Int>("Day01") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 7)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 5)
    }

    override fun part1(input:List<String>):Int {
        return input.toInts().windowed(2).count { (a,b) -> a < b }
    }

    override fun part2(input:List<String>):Int {
        return input.toInts().windowed(3).windowed(2).count {
            (a,b) -> a.sum() < b.sum()
        }
    }
}