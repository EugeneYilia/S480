package yichen

import java.util.LinkedList

fun main() {
    val snakeGame = SnakeGameByHashSet(2, 2, arrayOf(intArrayOf(1, 1), intArrayOf(0, 0)))
    // ["R"],["D"],["R"],["U"],["L"],["U"]
    println(snakeGame.move("R"))
    println(snakeGame.move("D"))
    println(snakeGame.move("L"))
    println(snakeGame.move("U"))
    println(snakeGame.move("R"))
}

class SnakeGameByHashSet(width: Int, height: Int, food: Array<IntArray>) {
    val rows = height
    val columns = width
    val foods = food

    var head = 0
    val snakesSet = HashSet<Int>().apply { add(0) }
    val snakesList = LinkedList<Int>().apply { add(0) }

    var score = 0
    var foodIndex = 0
    // 列数进制
    // 9 0-8

    fun move(direction: String): Int {
        var currentRow = head / columns
        var currentCol = head % columns

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
                snakesSet.remove(snakesList.removeLast())
            }
        } else {
            snakesSet.remove(snakesList.removeLast())
        }

        head = currentRow * columns + currentCol
        if (snakesSet.contains(head)) {
            return -1
        }

        snakesSet.add(head)
        snakesList.addFirst(head)

        return score
    }
}