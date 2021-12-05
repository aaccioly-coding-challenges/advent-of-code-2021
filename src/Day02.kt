import Command.DOWN
import Command.FORWARD
import Command.UP

enum class Command {
    FORWARD,
    DOWN,
    UP;

    companion object {
        fun fromLowerCaseString(input: String) = valueOf(input.uppercase())
    }
}

data class Position(val depth: Int, val horizontalPos: Int, val aim: Int) {
    fun moveWithoutAim(command: Command, units: Int): Position = when (command) {
        FORWARD -> copy(horizontalPos = horizontalPos + units)
        DOWN -> copy(depth = depth + units)
        UP -> copy(depth = depth - units)
    }

    fun move(command: Command, units: Int): Position = when (command) {
        FORWARD -> copy(horizontalPos = horizontalPos + units, depth = depth + aim * units)
        DOWN -> copy(aim = aim + units)
        UP -> copy(aim = aim - units)
    }
}

fun main() {
    val initialPosition = Position(0, 0, 0)

    fun part1(input: List<Pair<Command, Int>>): Int {
        return input
            .fold(initialPosition) { currentPosition, (command, units) ->
                currentPosition.moveWithoutAim(command, units)
            }.let { it.depth * it.horizontalPos }
    }

    fun part2(input: List<Pair<Command, Int>>): Int {
        return input
            .fold(initialPosition) { currentPosition, (command, units) ->
                currentPosition.move(command, units)
            }.let { it.depth * it.horizontalPos }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsCommands("Day02_test")

    println(part1(testInput))
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInputAsCommands("Day02")
    println(part1(input))
    println(part2(input))
}

private fun readInputAsCommands(name: String) = readInput(name).map { line ->
    val (commandAsString, unitsAsString) = line.split(" ")
    Pair(Command.fromLowerCaseString(commandAsString), unitsAsString.toInt())
}
