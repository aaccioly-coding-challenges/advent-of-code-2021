import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file as Strings.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads lines from the given input txt file as ints.
 */
fun readInputAsInts(name: String) = readInput(name).map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Returns 0 when [bit] is 1 and 1 when [bit] is 0.
 */
fun invertBit(bit: UByte): UByte {
    val inverted = (bit.toInt() - 1) * -1
    return inverted.toUByte()
}
