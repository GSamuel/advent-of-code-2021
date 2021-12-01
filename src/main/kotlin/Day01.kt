fun main() {
    ProblemDay01().solve()
}

class ProblemDay01:Problem("Day01") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 7)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 5)
    }

    override fun part1(input:List<String>):Int {
        return part1Numbers(input.toNumbers())
    }

    private fun part1Numbers(input:List<Int>):Int {
        var totalIncreases = 0
        var prev = Int.MAX_VALUE
        input.forEach {
            if(prev < it) {
                totalIncreases++
            }
            prev = it
        }
        return totalIncreases
    }

    override fun part2(input:List<String>):Int {
        val n = input.toNumbers()
        return (0 until (n.size-2)).map { n[it] + n[it+1] + n[it+2] }.run {
            part1Numbers(this)
        }
    }
}