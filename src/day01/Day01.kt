package day01

import println
import readInput
import kotlin.math.max

private const val FOLDER_NAME = "day01"
private const val FILE_NAME = "Day01"

fun main() {
    fun part1(input: List<String>): Long {
        var maxCalories = 0L
        var currentCalories = 0L

        input.forEach { line ->
            if (line.isBlank()) {
                maxCalories = max(maxCalories, currentCalories)
                currentCalories = 0L
                return@forEach
            }
            currentCalories += line.toLong()
        }
        maxCalories = max(maxCalories, currentCalories)

        return maxCalories
    }

    fun part2(input: List<String>): Long {
        var calories = mutableListOf<Long>()
        var currentCalories = 0L

        input.forEach { line ->
            if (line.isBlank()) {
                calories.add(currentCalories)
                currentCalories = 0L
                return@forEach
            }
            currentCalories += line.toLong()
        }
        calories.add(currentCalories)

        calories.sortDescending()
        return calories.take(3).sum()
    }

    val testInput = readInput(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput) == 24000L)
    check(part2(testInput) == 45000L)

    val input = readInput(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()

}
