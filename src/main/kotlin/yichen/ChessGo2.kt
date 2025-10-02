package yichen

class ChessGo2 {
    fun solve(board: Array<CharArray>) {
        val rows = board.size
        val columns = board[0].size

        val visited = Array(rows) { BooleanArray(columns) }

        if (rows < 2 || columns < 2) {
            return
        }

        val rowBottomNodes = HashSet<Pair<Int, Int>>()
        val rowMaxNodes = HashSet<Pair<Int, Int>>()
        val columnBottomNodes = HashSet<Pair<Int, Int>>()
        val columnMaxNodes = HashSet<Pair<Int, Int>>()
        for (col in 1..<columns - 1) {
            if (board[0][col] == 'O') {
                rowBottomNodes.add(Pair(1, col))
            }

            if (board[rows - 1][col] == 'O') {
                rowMaxNodes.add(Pair(rows - 2, col))
            }
        }

        for (r in 1..<rows - 1) {
            if (board[r][0] == 'O') {
                columnBottomNodes.add(Pair(r, 1))
            }

            if (board[r][columns - 1] == 'O') {
                columnMaxNodes.add(Pair(r, columns - 2))
            }
        }

        val connectedNode = HashSet<Pair<Int, Int>>()

        fun searchFromRootNode(node: Pair<Int, Int>) {
            val row = node.first
            val column = node.second

            fun canBeAccess(row: Int, column: Int): Boolean {
                if (row !in 1..<rows - 1) {
                    return false
                }

                if (column !in 1..<columns - 1) {
                    return false
                }

                if (visited[row][column]) {
                    return false
                }

                visited[row][column] = true
                return true
            }

            val canBeAccess = canBeAccess(row, column)
            if (canBeAccess && board[row][column] == 'O') {
                connectedNode.add(Pair(row, column))

                searchFromRootNode(Pair(row + 1, column))
                searchFromRootNode(Pair(row - 1, column))
                searchFromRootNode(Pair(row, column + 1))
                searchFromRootNode(Pair(row, column - 1))
            }
        }

        for (node in rowBottomNodes) {
            searchFromRootNode(node)
        }
        for (node in rowMaxNodes) {
            searchFromRootNode(node)
        }
        for (node in columnBottomNodes) {
            searchFromRootNode(node)
        }
        for (node in columnMaxNodes) {
            searchFromRootNode(node)
        }

        for (row in 1..<rows - 1) {
            for (col in 1..<columns - 1) {
                if (board[row][col] == 'O' && !connectedNode.contains(Pair(row, col))) {
                    board[row][col] = 'X'
                }
            }
        }
    }
}