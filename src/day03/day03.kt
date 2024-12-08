package day03

import println
import readInput

private const val FOLDER_NAME = "day03"
private const val FILE_NAME = "Day03"

private val lowerCaseCharRanges = 'a'.code..'z'.code

private fun charToPriority(char: Char): Int {
    return if (char.code in lowerCaseCharRanges) {
        char.code - 'a'.code + 1
    } else {
        char.code - 'A'.code + 27
    }
}

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { line ->
            val s1 = line.take(line.length/2).toCharArray().toSet()
            val s2 = line.takeLast(line.length - line.length/2).toCharArray().toSet()

            s1.intersect(s2).sumOf { charToPriority(it) }.toLong()
        }
    }

    fun part2(input: List<String>): Long {
        return input.chunked(3).sumOf { shit ->
            val common = shit.map { it.toCharArray().toSet() }.reduce { acc, chars -> acc.intersect(chars) }
            common.sumOf { charToPriority(it) }.toLong()
        }
    }

    val testInput = readInput(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput) == 157L)
    check(part2(testInput) == 70L)

    val input = readInput(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()

}
