package day03

import println
import readInput
import javax.print.DocFlavor.STRING
import kotlin.math.max

private const val FOLDER_NAME = "day02"
private const val FILE_NAME = "Day02"

private enum class RoundState(val score: Int) {
    WIN(6),
    LOSE(0),
    DRAW(3);

    companion object {
        fun fromString(s: String): RoundState = when(s) {
            "X" -> LOSE
            "Y" -> DRAW
            else -> WIN
        }
    }
}

private enum class Move(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    fun didWin(other: Move): Boolean {
        return (this == PAPER && other == ROCK) || (this == SCISSORS && other == PAPER) || (this == ROCK && other == SCISSORS)
    }

    fun roundScore(other: Move): Int {
        if (this == other) return 3
        return if (this.didWin(other)) 6 else 0
    }

    fun moveToReachState(state: RoundState): Move {
        return when (state) {
            RoundState.DRAW -> this
            RoundState.WIN -> {
                when (this) {
                    ROCK -> PAPER
                    PAPER -> SCISSORS
                    SCISSORS -> ROCK
                }
            }
            RoundState.LOSE -> {
                when (this) {
                    ROCK -> SCISSORS
                    PAPER -> ROCK
                    SCISSORS -> PAPER
                }
            }
        }
    }

    companion object {
        fun fromString(s: String): Move = when (s) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            else -> SCISSORS
        }
    }

}

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { line ->
            val (otherMove, myMove) = line.split(" ").map { Move.fromString(it) }
            myMove.roundScore(otherMove) + myMove.score.toLong()
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { line ->
            val (otherMoveStr, targetStateStr) = line.split(" ")
            val otherMove = Move.fromString(otherMoveStr)
            val targetState = RoundState.fromString(targetStateStr)
            otherMove.moveToReachState(targetState).score + targetState.score.toLong()
        }
    }

    val testInput = readInput(FOLDER_NAME, "${FILE_NAME}_test")
    check(part1(testInput) == 15L)
    check(part2(testInput) == 12L)

    val input = readInput(FOLDER_NAME, FILE_NAME)
    part1(input).println()
    part2(input).println()

}
