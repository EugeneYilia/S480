package yichen

class Gobang {

    var board = Array(100) { CharArray(100) { ' ' } }

    fun putNew(isBlack: Boolean, row: Int, column: Int): Boolean {
        if (board[row][column] != ' ') {
            throw Exception("Illegal argument row : $row column : $column")
        }

        if (isBlack) {
            board[row][column] = 'X'
        } else {
            board[row][column] = 'O'
        }

        return checkIsWin(board[row][column], row, column)
    }

    private fun checkIsWin(ch: Char, row: Int, column: Int): Boolean {
        var rowCount = 1
        var leftColumn = column - 1
        var rightColumn = column + 1
        while (leftColumn >= 0 && board[row][leftColumn] == ch) {
            rowCount++
            leftColumn--
        }

        while (rightColumn <= 99 && board[row][rightColumn] == ch) {
            rowCount++
            rightColumn++
        }

        if (rowCount == 5) {
            return true
        }


        var columnCount = 1
        var upRow = row - 1
        var downRow = row + 1
        while (upRow >= 0 && board[upRow][column] == ch) {
            columnCount++
            upRow--
        }

        while (downRow <= 99 && board[downRow][column] == ch) {
            columnCount++
            downRow++
        }

        if (columnCount == 5) {
            return true
        }


        var downStairCount = 1
        var downLeftRow = row + 1
        var downUpColumn = column - 1

        var downRightRow = row - 1
        var downDownColumn = column + 1
        while (downLeftRow <= 99 && downUpColumn >= 0 && board[downLeftRow][downUpColumn] == ch) {
            downStairCount++
            downLeftRow++
            downUpColumn--
        }

        while (downRightRow >= 0 && downDownColumn <= 99 && board[downRightRow][downDownColumn] == ch) {
            downRightRow--
            downDownColumn++
            downStairCount++
        }

        if (downStairCount == 5) {
            return true
        }


        var upStairCount = 1
        var upLeftRow = row - 1
        var upDownColumn = column - 1

        var upRightRow = row + 1
        var upUpColumn = column + 1
        while (upLeftRow >= 0 && upDownColumn >= 0 && board[upLeftRow][upDownColumn] == ch) {
            upStairCount++
            upLeftRow--
            upDownColumn--
        }

        while (upRightRow <= 99 && upUpColumn <= 99 && board[upRightRow][upUpColumn] == ch) {
            upStairCount++
            upRightRow++
            upUpColumn++

        }

        if (upStairCount == 5) {
            return true
        }


        return false
    }
}

fun main() {

    // 横向五连
    run {
        val game = Gobang()
        var win = false
        repeat(5) { c ->
            win = game.putNew(true, 10, c)
            if (c < 4) assert(!win) { "❌ 横向：第 ${c + 1} 次错误检测为胜利" }
        }
        assert(win) { "❌ 横向五连未正确识别为胜利" }
        println("✅ 横向五连判断正确")
    }

    // 纵向五连
    run {
        val game = Gobang()
        var win = false
        repeat(5) { r ->
            win = game.putNew(false, r, 20)
            if (r < 4) assert(!win) { "❌ 纵向：第 ${r + 1} 次错误检测为胜利" }
        }
        assert(win) { "❌ 纵向五连未正确识别为胜利" }
        println("✅ 纵向五连判断正确")
    }

    // 正斜线五连（↘）
    run {
        val game = Gobang()
        var win = false
        repeat(5) { i ->
            win = game.putNew(true, i, i)
            if (i < 4) assert(!win) { "❌ 正斜线：第 ${i + 1} 次错误检测为胜利" }
        }
        assert(win) { "❌ 正斜线五连未正确识别为胜利" }
        println("✅ 正斜线五连判断正确")
    }

    // 反斜线五连（↙）
    run {
        val game = Gobang()
        var win = false
        repeat(5) { i ->
            val row = 4 - i
            val col = i
            win = game.putNew(false, row, col)
            if (i < 4) assert(!win) { "❌ 反斜线：第 ${i + 1} 次错误检测为胜利" }
        }
        assert(win) { "❌ 反斜线五连未正确识别为胜利" }
        println("✅ 反斜线五连判断正确")
    }

    // 无胜负情况
    run {
        val game = Gobang()
        val moves = listOf(
            Triple(true, 10, 10),
            Triple(false, 10, 11),
            Triple(true, 10, 12),
            Triple(false, 11, 10),
            Triple(true, 12, 12)
        )
        // 黑 白 黑
        // 白
        //      黑
        moves.forEachIndexed { index, (isBlack, r, c) ->
            val win = game.putNew(isBlack, r, c)
            assert(!win) { "❌ 无胜负情况第 ${index + 1} 步错误检测为胜利 ($r, $c)" }
        }
        println("✅ 无胜负情况判断正确")
    }

    println("\n🎉 所有测试全部通过！")
}
