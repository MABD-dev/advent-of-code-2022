package day01

import println
import readInput

private const val FOLDER_NAME = "day01"
private const val FILE_NAME = "Day01"

fun main() {
    fun part1(input: List<String>): Long {
        return 0L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readInput(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput) == 0L)
    check(part2(testInput) == 0L)

    val input = readInput(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()

}
