package yichen

import java.util.LinkedList

fun main() {
    val snakeGame = SnakeGame(2, 2, arrayOf(intArrayOf(1, 1), intArrayOf(0, 0)))
    // ["R"],["D"],["R"],["U"],["L"],["U"]
    println(snakeGame.move("R"))
    println(snakeGame.move("D"))
    println(snakeGame.move("L"))
    println(snakeGame.move("U"))
    println(snakeGame.move("R"))
}

class SnakeGame(width: Int, height: Int, food: Array<IntArray>) {
    val rows = height
    val columns = width
    val foods = food

    var currentPos = Pair(0, 0)
    val snakes = LinkedList<Pair<Int, Int>>().apply { add(Pair(0, 0)) }

    var score = 0
    var foodIndex = 0

    fun move(direction: String): Int {
        var currentRow = currentPos.first
        var currentCol = currentPos.second

        if (direction == "U") {
            currentRow--
            if (currentRow < 0) {
                return -1
            }
        } else if (direction == "L") {
            currentCol--
            if (currentCol < 0) {
                return -1
            }
        } else if (direction == "D") {
            currentRow++
            if (currentRow > rows - 1) {
                return -1
            }
        } else if (direction == "R") {
            currentCol++
            if (currentCol > columns - 1) {
                return -1
            }
        }

        if (foodIndex < foods.size) {
            val curFood = foods[foodIndex]
            if (currentRow == curFood[0] && currentCol == curFood[1]) {
                score++
                foodIndex++
            } else {
                snakes.remove(snakes.last())
            }
        } else {
            snakes.remove(snakes.last())
        }

        if (snakes.contains(Pair(currentRow, currentCol))) {
            return -1
        }

        val newPosition = Pair(currentRow, currentCol)
        currentPos = newPosition
        snakes.addFirst(newPosition)

        return score
    }
}