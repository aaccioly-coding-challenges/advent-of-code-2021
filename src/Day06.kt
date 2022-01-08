import java.io.File



fun main() {

    val DAYS_IN_CICLE = 7

    fun readCSVInputAsInts(name: String) = File("src", "$name.txt").readText().split(",").map { it.toInt() }

    fun nDescendents(initialState: Int, days: Int): Long {
        val nDirectChildren = ((days - initialState) % DAYS_IN_CICLE) + 1

        return nDirectChildren.toLong()
    }

    fun simulate(input: List<Int>, days: Int): Long {
        val lanternFishAge = input.toMutableList()
        val lanternFishToAdd = mutableListOf<Int>()
        for (day in 1 .. days) {
            lanternFishAge.removeAll { it == 0 }
            lanternFishAge.replaceAll(Int::dec)
            lanternFishAge.addAll(lanternFishToAdd)
            lanternFishToAdd.clear()

            val numberFishesSpawning = lanternFishAge.count { it == 0 }
            for (fish in 0 until numberFishesSpawning) {
                lanternFishToAdd.add(6)
                lanternFishToAdd.add(8)
            }
        }

        return lanternFishAge.size.toLong()
    }
    fun part1(input: List<Int>): Long {
        return simulate(input, 80)
    }

    fun part2(input: List<Int>): Long {
        return simulate(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readCSVInputAsInts("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539)

    val input = readCSVInputAsInts("Day06")
    println(part1(input))
   // println(part2(input))
}
