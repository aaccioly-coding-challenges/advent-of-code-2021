fun main() {

    fun readInputAsMatrix(name: String): List<IntArray> =
        readInput(name).map { string -> string.toCharArray().map { digit -> digit.digitToInt() } }
            .map { it.toIntArray() }

    fun List<IntArray>.mostCommonBitInColumn(columnIndex: Int): UByte {
        tailrec fun countBitsInColumn(numberOfZeros: Int = 0, numberOfOnes: Int = 0, rowIndex: Int = 0): Pair<Int, Int> {
            if (rowIndex >= size) {
                return Pair(numberOfZeros, numberOfOnes)
            }
            return when (this[rowIndex][columnIndex]) {
                0 -> countBitsInColumn(numberOfZeros + 1, numberOfOnes, rowIndex + 1)
                1 -> countBitsInColumn(numberOfZeros, numberOfOnes + 1, rowIndex + 1)
                else -> countBitsInColumn(numberOfZeros, numberOfOnes, rowIndex)
            }
        }

        val (zeros, ones) = countBitsInColumn()
        return if (ones >= zeros) 1u else 0u
    }

    fun List<IntArray>.leastCommonBitInColumn(columnIndex: Int): UByte {
        return invertBit(mostCommonBitInColumn((columnIndex)))
    }

    fun part1(input: List<IntArray>): UInt {
        val nColumns = input[0].size
        val gamma = (0 until nColumns).map { input.mostCommonBitInColumn(it) }.joinToString(separator = "").toUInt(2)
        val epsilon = (0 until nColumns).map { input.leastCommonBitInColumn(it) }.joinToString(separator = "").toUInt(2)

        return gamma * epsilon
    }

    fun part2(input: List<IntArray>): UInt {
        val nColumns = input[0].size

        tailrec fun generatorRating(
            acc: UInt = 0u, columnIndex: Int = 0, input: List<IntArray>, bitFunction: List<IntArray>.(Int) -> UByte
        ): UInt {
            if (input.isEmpty() || columnIndex >= nColumns) {
                return acc
            }

            val bitForColumn = if (input.size == 1) {
                input[0][columnIndex].toUByte()
            } else {
                input.bitFunction(columnIndex)
            }

            val filteredInput = input.filter { it[columnIndex] == bitForColumn.toInt() }
            val newAcc = (acc shl 1) + bitForColumn
            return generatorRating(newAcc, columnIndex + 1, filteredInput, bitFunction)
        }

        val oxygen = generatorRating(input = input, bitFunction = List<IntArray>::mostCommonBitInColumn)
        val co2 = generatorRating(input = input, bitFunction = List<IntArray>::leastCommonBitInColumn)

        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsMatrix("Day03_test")

    check(part1(testInput) == 198u)
    check(part2(testInput) == 230u)

    val input = readInputAsMatrix("Day03")
    println(part1(input))
    println(part2(input))
}
