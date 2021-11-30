abstract class Problem(private val fileName:String) {
    abstract fun validateTestInput(result:Int)
    abstract fun part1(input:List<String>):Int
    abstract fun part2(input:List<String>):Int

    fun solve() {
        validateTestInput(part1(readTestInput()))

        val input = readInput()
        println(part1(input))
        println(part2(input))
    }

    private fun readInput() = Reader.readInput(fileName)
    private fun readTestInput() = Reader.readInput("${fileName}_test")
}