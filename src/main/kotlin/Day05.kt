import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    ProblemDay05().solve()
}

class ProblemDay05 : Problem<Int>("Day05") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 5)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 12)
    }

    override fun part1(input: List<String>): Int {
        return input.toLineSegments().filter { it.isHorizontal() || it.isVertical() }
            .flatMap { it.points }.groupingBy { it }.eachCount()
            .filter { it.value > 1 }.count()
    }

    override fun part2(input: List<String>): Int {
        return input.toLineSegments()
            .flatMap { it.points }.groupingBy { it }.eachCount()
            .filter { it.value > 1 }.count()
    }

    private fun List<String>.toLineSegments() = map { it.toLineSegment() }

    private fun String.toLineSegment() =
        split(" -> ").let { (start, end) -> LineSegment(start.toVector(), end.toVector()) }

    private fun String.toVector(): Vector = split(",").map { it.toInt() }.let { (x, y) ->
        Vector(x, y)
    }

    data class Vector(val x: Int, val y: Int) {
        operator fun minus(other: Vector): Vector {
            return Vector(x - other.x, y - other.y)
        }

        operator fun plus(other: Vector): Vector {
            return Vector(x + other.x, y + other.y)
        }

        fun normalized() = Vector(if (x == 0) 0 else x.div(abs(x)), if (y == 0) 0 else y.div(abs(y)))
    }


    data class LineSegment(val start: Vector, val end: Vector) {
        fun isHorizontal() = start.y == end.y
        fun isVertical() = start.x == end.x

        val dir: Vector by lazy {
            (end - start).normalized()
        }

        val points: Sequence<Vector> by lazy {
            generateSequence(start) { prev -> if (prev == end) null else (prev + dir) }
        }
    }
}