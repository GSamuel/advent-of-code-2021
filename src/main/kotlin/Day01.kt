fun main() {
    ProblemDay01().solve()
}

class ProblemDay01:Problem("Day01") {
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