package yichen

import java.util.LinkedList

// Design Snake game:
//
// The initial length is 3, and the length increases by 1 every 5 steps.
//
// The game ends if the snake hits a wall or bites itself.
//
// Implement a function: bool step(string direction).
// Write a few test cases to verify it

class SnakeGame {
    val rows = 100
    val columns = 100
    val currentPos = Pair(50, 50)
    val walls = HashSet<Pair<Int, Int>>()
    val snakes = LinkedList<Pair<Int, Int>>()
    val foods = HashSet<Pair<Int, Int>>()
    val foodReward = 3

    fun step(dir: String): Boolean {
        var currentRow = currentPos.first
        var currentCol = currentPos.second

        if (dir == "w") {
            currentRow--
            if (currentRow < 0) {
                return false
            }
        } else if (dir == "a") {
            currentCol--
            if (currentCol < 0) {
                return false
            }
        } else if (dir == "s") {
            currentRow++
            if (currentRow > rows - 1) {
                return false
            }
        } else if (dir == "d") {
            currentCol++
            if (currentCol > columns - 1) {
                return false
            }
        }

        snakes.remove(snakes.last())

        if (foods.contains(Pair(currentRow, currentCol))) {
            val body = snakes[snakes.size - 2]
            var tail = snakes[snakes.size - 1]

            val bodyRow = body.first
            val bodyCol = body.second
            val tailRow = tail.first
            val tailCol = tail.second

            if (bodyRow != tailRow) {
                if (bodyRow > tailRow) {
                    var currentRow = tailRow
                    val currentCol = tailCol
                    for (i in 0..<foodReward) {
                        currentRow--
                        val newTail = Pair(currentRow, currentCol)
                        if (walls.contains(newTail) || snakes.contains(newTail)) {
                            return false
                        }
                        snakes.add(newTail)
                    }
                } else {
                    var currentRow = tailRow
                    val currentCol = tailCol
                    for (i in 0..<foodReward) {
                        currentRow++
                        val newTail = Pair(currentRow, currentCol)
                        if (walls.contains(newTail) || snakes.contains(newTail)) {
                            return false
                        }
                        snakes.add(newTail)
                    }
                }
            } else {
                if (bodyCol > tailCol) {
                    val currentRow = tailRow
                    var currentCol = tailCol
                    for (i in 0..<foodReward) {
                        currentCol--
                        val newTail = Pair(currentRow, currentCol)
                        if (walls.contains(newTail) || snakes.contains(newTail)) {
                            return false
                        }
                        snakes.add(newTail)
                    }
                } else {
                    val currentRow = tailRow
                    var currentCol = tailCol
                    for (i in 0..<foodReward) {
                        currentCol++
                        val newTail = Pair(currentRow, currentCol)
                        if (walls.contains(newTail) || snakes.contains(newTail)) {
                            return false
                        }
                        snakes.add(newTail)
                    }
                }
            }
        }

        if (walls.contains(Pair(currentRow, currentCol)) || snakes.contains(Pair(currentRow, currentCol))) {
            return false
        }

        snakes.add(Pair(currentRow, currentCol))

        return true
    }
}