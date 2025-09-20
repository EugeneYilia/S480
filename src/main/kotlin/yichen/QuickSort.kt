fun main() {
    val src = intArrayOf(3, 2, 1, 2, 3, 5, 4, 2, 1, 1, 2, 4, 1, 6)
//    val src = intArrayOf(1,2,1,2,1)
//    val src = intArrayOf(1,2,1)
    quickSort(src)
    println(src.contentToString())
}

fun quickSort(array: IntArray) {
    quickSortInternal(array, 0, array.size - 1)
}

fun quickSortInternal(array: IntArray, start: Int, end: Int) {
    if (start >= end) return

    val pivot = array[start]

    var backIndex = end
    var frontIndex = start + 1

    while (backIndex >= frontIndex) {
        while (backIndex >= frontIndex && array[backIndex] >= pivot) {
            backIndex--;
        }

        while (backIndex >= frontIndex && array[frontIndex] < pivot) {
            frontIndex++;
        }

        if (backIndex > frontIndex) {
            val frontValue = array[frontIndex]
            array[frontIndex] = array[backIndex]
            array[backIndex] = frontValue
        }
    }

    if (pivot > array[backIndex]) {
        array[start] = array[backIndex]
        array[backIndex] = pivot
    }

    quickSortInternal(array, start, backIndex - 1)
    quickSortInternal(array, backIndex + 1, end)
}