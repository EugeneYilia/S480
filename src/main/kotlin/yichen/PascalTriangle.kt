package yichen

fun main(){
    println(PascalTriangle().generate(5))
}

class PascalTriangle {
    // 1
    // 1 1
    // 1
    fun generate(numRows: Int): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        for (i in 0 until numRows) {
            val currentList = mutableListOf<Int>()
            list.add(currentList)
            if(i == 0){
                currentList.add(1)
                continue
            }

            val aboveList = list.get(i - 1)

            currentList.add(1)

            for (j in 0..aboveList.size - 2) {
                currentList.add(aboveList[j] + aboveList[j + 1])
            }

            currentList.add(1)
        }

        return list
    }
}