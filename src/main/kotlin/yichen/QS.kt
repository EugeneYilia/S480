package yichen

fun main() {
//    val src = intArrayOf(4, 2, 1, 6, 4, 8, 5, 7)
//    val src = intArrayOf(4,2)
//    val src = intArrayOf(0,0,1,1,2,5)
    val src = intArrayOf(5, 1, 1, 2, 0, 0)
    QS().quickSort(src)
    println(src.toList())
}

// [5,1,1,2,0,0]
// [0,1,1,2,0,5]
// [0,1,1,0,2,5]
// [0,0,1,1,2,5]
class QS {
    fun quickSort(src: IntArray) {
        quickSortInternal(src, 0, src.size - 1)
    }


    // 10  10 2 3 10 2 5 2 1 8 10 11
    // 21:18
    fun quickSortInternal(src: IntArray, start: Int, end: Int) {
        if (start >= end) {
            return
        }

        val pivot = src[start]

        // 路过的位置都是比pivot要小的，停的位置要么是backIndex，要么是比pivot要大
        var forwardIndex = start + 1
        // 路过的位置都是比pivot要大的，停的位置要么是forwardIndex，要么是比pivot要小
        var backIndex = end
        // 前往后走停了，直接交换终点位置
        // 后往前走停了，前走过，终点位置可以直接交换
        // 后往前走停了，前没走过，终点位置可能可以交换，也可能不能交换

        while (backIndex >= forwardIndex) {
            while (backIndex >= forwardIndex && src[backIndex] >= pivot) {
                backIndex--
            }

            while (backIndex >= forwardIndex && src[forwardIndex] <= pivot) {
                forwardIndex++
            }

            if (backIndex > forwardIndex) {
                val backValue = src[backIndex]
                src[backIndex] = src[forwardIndex]
                src[forwardIndex] = backValue
            }
        }

        // backIndex的位置要么停在一个比pivot的值小的地方，即为中间
        // 要么停在pivot本身的位置，此时要对后面的元素进行排序
        //
        // 在正常情况下，backIndex 在向前移动的过程中不会停在一个等于 pivot 的位置，
        // 它要么停在一个小于 pivot 的位置，要么一直走到 start（也就是 pivot 自己的位置），
        // 这时 src[backIndex] == pivot 只是因为已经退到起点，不代表比较时发现了相等。
        if(pivot > src[backIndex]) {
            src[start] = src[backIndex]
            src[backIndex] = pivot

            quickSortInternal(src, start, backIndex - 1)
            quickSortInternal(src, backIndex + 1, end)
        } else {
            quickSortInternal(src, start + 1, end)
        }
    }
}