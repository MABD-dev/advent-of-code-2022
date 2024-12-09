package day05

import println
import readInput
import readInputNoTrim

private const val FOLDER_NAME = "day05"
private const val FILE_NAME = "Day05"

val regex = "\\d+".toRegex()

private data class Move(
    val quantity: Int,
    val source: Int,
    val target: Int
)

private fun numberOfStacks(input: List<String>): Int = input
    .first { !it.contains('[') }
    .trim()
    .split("   ")
    .maxOfOrNull { it.toInt() }
    ?: 0

private fun fillUpStacks(
    input: List<String>,
    stacks: List<ArrayDeque<Char>>
) {
    input
        .takeWhile { it.contains("[") }
        .forEach { line ->
            for (i in 1 until line.length step 4) {
                val stackIndex = i / 4
                if (line[i].isLetter()) {
                    stacks[stackIndex].addLast(line[i])
                }
            }
        }
}

private fun readMoves(input: List<String>): List<Move> {
    return input
        .filter { it.startsWith("m") }
        .map { line ->
            val (quantity, stackSource, stackTarget) = regex.findAll(line).map { it.value.toInt() }.toList()
            Move(
                quantity = quantity,
                source = stackSource - 1,
                target = stackTarget - 1
            )
        }
}


fun main() {
    fun part1(input: List<String>): String {
        fun doOperations(moves: List<Move>, stacks: List<ArrayDeque<Char>>) {
            moves.forEach { move ->
                repeat(move.quantity) {
                    stacks[move.target].addFirst(stacks[move.source].removeFirst())
                }
            }
        }

        val numberOfStacks = numberOfStacks(input)
        val stacks = List(size = numberOfStacks, init = { ArrayDeque<Char>() })

        fillUpStacks(input, stacks)

        val moves = readMoves(input)
        doOperations(moves, stacks)

        return stacks.map { it.first() }.joinToString(separator = "")
    }

    fun part2(input: List<String>): String {
        fun doOperations(moves: List<Move>, stacks: List<ArrayDeque<Char>>) {
            moves.forEach { move ->
                stacks[move.source].subList(0, move.quantity).asReversed()
                    .map { stacks[move.target].addFirst(it) }
                    .map { stacks[move.source].removeFirst() }
            }
        }

        val numberOfStacks = numberOfStacks(input)
        val stacks = List(size = numberOfStacks, init = { ArrayDeque<Char>() })
        fillUpStacks(input, stacks)

        val moves = readMoves(input)
        doOperations(moves, stacks)

        return stacks.map { it.first() }.joinToString(separator = "")
    }

    val testInput = readInputNoTrim(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInputNoTrim(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()


}
