package day04

import println
import readInput

private const val FOLDER_NAME = "day04"
private const val FILE_NAME = "Day04"

private fun IntRange.fullyContains(other: IntRange): Boolean {
    return this.intersect(other).size == (other.last - other.first + 1)
}

private fun IntRange.overlap(other: IntRange): Boolean {
    return this.intersect(other).size != 0
}


fun main() {
    fun part1(input: List<String>): Int {
        return input.count { line ->
            val (range1, range2) = line.split(",").map { pairStr ->
                val (x, y) = pairStr.split("-").map { it.toInt() }
                x..y
            }
            range1.fullyContains(range2) || range2.fullyContains(range1)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val (range1, range2) = line.split(",").map { pairStr ->
                val (x, y) = pairStr.split("-").map { it.toInt() }
                x..y
            }
            range1.overlap(range2)
        }
    }

    val testInput = readInput(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()

}
