package day06

import println
import readInputNoTrim

private const val FOLDER_NAME = "day06"
private const val FILE_NAME = "Day06"

fun main() {
    fun part1(input: List<String>): Int {
        val set = mutableSetOf<Char>()
        input.forEach { line ->
            for (i in 0 until line.length - 4) {
                set.clear()

                var foundSolution = true
                for (j in i until i+4) {
                    if (!set.add(line[j])) {
                        foundSolution = false
                        break
                    }
                }
                if (foundSolution) {
                    return i+4
                }
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val set = mutableSetOf<Char>()
        input.forEach { line ->
            for (i in 0 until line.length - 4) {
                set.clear()

                var foundSolution = true
                for (j in i until i+14) {
                    if (!set.add(line[j])) {
                        foundSolution = false
                        break
                    }
                }
                if (foundSolution) {
                    return i+14
                }
            }
        }
        return -1
    }

    val testInput = readInputNoTrim(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput).also { println("result=$it") } == 5)
    check(part2(testInput) == 23)

    val input = readInputNoTrim(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()


}
