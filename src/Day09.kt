fun main() {
    fun readInputAsMatrix(name: String): Array<IntArray> {
        return readInput(name)
            .map { lineAsString -> lineAsString.map { it.digitToInt() }.toIntArray() }
            .toTypedArray()
    }

    fun part1(input: Array<IntArray>): Int {
        return TODO()
    }

    fun part2(input: Array<IntArray>): Int {
        return TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsMatrix("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsMatrix("Day01")
    println(part1(input))
    println(part2(input))

}
