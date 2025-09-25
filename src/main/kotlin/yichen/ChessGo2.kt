package yichen

class ChessGo2 {
    class positionNode {
        var row = 0
        var column = 0
        var isVisited = false
    }
    fun solve(board: Array<CharArray>): Unit {
        val row = board.size
        val column = board[0].size

        if (row < 2 || column < 2) {
            return
        }

        val rootNodes = HashSet<Pair<Int, Int>>()

        for (col in 0..<column) {
            if (board[0][col] == 'O') {
                rootNodes.add(Pair(0, col))
            }

            if (board[row - 1][col] == 'O') {
                rootNodes.add(Pair(row - 1, col))
            }
        }

        for(r in 0..<row){
            if(board[r][0] == 'O'){
                rootNodes.add(Pair(r, 0))
            }

            if(board[r][column - 1] == 'O') {
                rootNodes.add(Pair(r, column - 1))
            }
        }


    }
}