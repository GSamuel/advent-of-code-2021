fun main() {
    ProblemDay03().solve()
}

class ProblemDay03 : Problem<Int>("Day03") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 198)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 230)
    }

    override fun part1(input: List<String>): Int {
        val gamma = input.toMostCommonBits()
        val epsilon = gamma.invert()
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    override fun part2(input: List<String>): Int {
        return input.oxygen().toInt(2) * input.co2().toInt(2)
    }

    private fun List<String>.oxygen():String {
        var idx = 0
        var remaining:List<String> = this
        while(idx < remaining.first().length && remaining.size > 1) {
            val oxygenBit = remaining.mostCommonBit(idx,-1)
            remaining = remaining.filter { it[idx] == oxygenBit }
            idx++
        }
        return remaining.first()
    }

    private fun List<String>.co2():String {
        var idx = 0
        var remaining:List<String> = this
        while(idx < remaining.first().length && remaining.size > 1) {
            val co2Bit = remaining.mostCommonBit(idx,-1).invert()
            remaining = remaining.filter { it[idx] == co2Bit }
            idx++
        }
        return remaining.first()
    }

    private fun String.invert(): String {
        return this.map { if(it == '1') '0' else '1' }.joinToString(separator = "")
    }

    private fun List<String>.toMostCommonBits(offset: Int = 0): String {
        return (0 until this[0].length).map { mostCommonBit(it, offset) }.joinToString(separator = "")
    }

    private fun Char.invert():Char = when(this) {
        '1' -> '0'
        '0' -> '1'
        else -> error("Unknown binary bit")
    }

    private fun List<String>.mostCommonBit(pos: Int, offset:Int = 0): Char {
        return if (countOnes(pos) > (this.size + offset) / 2)
            '1'
        else '0'
    }


    private fun List<String>.countOnes(pos: Int): Int {
        return this.count { it.isOne(pos) }
    }

    private fun String.isOne(pos:Int) = this[pos] == '1'
}