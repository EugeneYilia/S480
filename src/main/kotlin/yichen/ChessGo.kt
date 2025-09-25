// Deprecated
// 有问题

package yichen

//默认题目描述
//输入：
//board =
//[['X','X','X','X'],
// ['X','O','O','X'],
// ['X','X','O','X'],
// ['X','O','X','X']]
//
//输出：
//[['X','X','X','X'],
// ['X','X','X','X'],
// ['X','X','X','X'],
// ['X','O','X','X']]


fun main(args: Array<String>) {

}

// [
// ["O","X","X","O","X"],
// ["X","O","O","X","O"],
// ["X","O","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]
// ]

// output
// [
// ["O","X","X","O","X"],
// ["X","O","O","X","O"],
// ["X","O","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]]

// expected
// [
// ["O","X","X","O","X"],
// ["X","X","X","X","O"],
// ["X","X","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]]
fun solve(board: Array<CharArray>): Unit {
    val row = board.size;
    val column = board[0].size;

    if (row < 2 || column < 2) {
        return
    }

    val connectedAreas = IntArray(row + column)
    for (i in 0..<row + column) {
        connectedAreas[i] = i
    }

    for (rowIndex in 1..row - 2) {
        for (columnIndex in 1..column - 2) {
            if (board[rowIndex][columnIndex] == 'O') {
                updateConnectedAreas(connectedAreas, rowIndex, columnIndex)
            }
        }
    }

    val regions = ArrayList<Region>()

    for (rowIndex in 1..row - 2) {
        for (columnIndex in 1..column - 2) {
            if (board[rowIndex][columnIndex] == 'O') {
                val regionIndex = getConnectedArea(connectedAreas, columnIndex);
                var region = regions.firstOrNull { region -> region.head == regionIndex };
                if (region == null) {
                    region = Region()
                    region.head = regionIndex;
                    region.elements.add(Pair(rowIndex, columnIndex));
                    regions.add(region);
                } else {
                    region.elements.add(Pair(rowIndex, columnIndex));
                }

                if (rowIndex == 1) {
                    if (board[0][columnIndex] == 'O') {
                        region.isLive = true
                        continue
                    }
                }

                if (rowIndex == row - 2) {
                    if (board[row - 1][columnIndex] == 'O') {
                        region.isLive = true
                        continue
                    }
                }

                if (columnIndex == 1) {
                    if (board[rowIndex][0] == 'O') {
                        region.isLive = true
                        continue
                    }
                }

                if (columnIndex == column - 2) {
                    if (board[rowIndex][column - 1] == '0'){
                        region.isLive = true
                        continue
                    }
                }
            }
        }
    }

    for(region in regions) {
        if(!region.isLive){
            for((rowIndex, columnIndex) in region.elements){
                board[rowIndex][columnIndex] = 'X';
            }
        }
    }
}

fun updateConnectedAreas(src: IntArray, area1: Int, area2: Int) {
    if (src[area2] == area2) {
        src[area1] = area2
    } else {
        src[area2] = getConnectedArea(src, src[area2])
        src[area1] = src[area2]
    }
}

fun getConnectedArea(src: IntArray, areaId: Int): Int {
    return if (src[areaId] == areaId) {
        areaId
    } else {
        getConnectedArea(src, src[areaId])
    }
}

class Region {
    public var head: Int = -1
    public var elements: HashSet<Pair<Int, Int>> = HashSet()
    public var isLive = false
}