fun main() {
    ProblemDay06().solve()
}

class ProblemDay06 : Problem<Long>("Day06") {
    override fun validatePart1TestInput(result: Long) {
        check(result == 5934L)
    }

    override fun validatePart2TestInput(result: Long) {
        check(result == 26984457539L)
    }

    override fun part1(input: List<String>): Long {
        return input.toSchoolOfCuteLittleLanternFishies().countCuteLittleLanternFishiesAfterDays(80)
    }

    override fun part2(input: List<String>): Long {
        return input.toSchoolOfCuteLittleLanternFishies().countCuteLittleLanternFishiesAfterDays(256)
    }

    private fun SchoolOfLanternFishies.countCuteLittleLanternFishiesAfterDays(days:Int): Long {
        var fishies = this
        repeat(days) {
            fishies = fishies.nextDay()
        }

        return fishies.count()
    }

    private fun List<String>.toSchoolOfCuteLittleLanternFishies(): SchoolOfLanternFishies {
        return first().split(",")
            .map { it.toInt() }
            .groupingBy { it }.eachCount()
            .mapValues { it.value.toLong() }
            .let { SchoolOfLanternFishies(it) }
    }

    data class SchoolOfLanternFishies(val fishies: Map<Int, Long>) {
        fun nextDay():SchoolOfLanternFishies {
            return fishies.entries.fold(mutableMapOf<Int,Long>()) { result, el ->
                result.apply {
                    if(el.key > 0) {
                        this[el.key-1] = (this[el.key - 1] ?: 0) + el.value
                    } else {
                        this[6] = (this[6] ?: 0) + el.value
                        this[8] = el.value
                    }
                }
            }.let { SchoolOfLanternFishies(it) }
        }

        fun count(): Long = fishies.entries.sumOf { it.value }
    }
}