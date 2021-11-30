fun main() {
    ProblemDay02().solve()
}

class ProblemDay02:Problem("Day02") {
    override fun validateTestInput(result: Int) {
        check(result == 3)
    }

    override fun part1(input:List<String>):Int {
        return input.size
    }

    override fun part2(input:List<String>):Int {
        return input.size
    }
}