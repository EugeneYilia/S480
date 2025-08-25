package yichen

class SmallestK {
    fun smallestK(arr: IntArray, k: Int): IntArray {
        quickSort(arr, 0, arr.size - 1)
        // 左闭右开
        return arr.copyOfRange(0, k)
    }

    fun quickSort(arr: IntArray, left: Int, right: Int) {
        if (left >= right) {
            return
        }

        val pivot = arr[left]

        var l = left + 1
        var r = right

        while (true) {
            while (r >= l && arr[r] >= pivot) {
                r--
            }
            while (r >= l && arr[l] < pivot) {
                l++
            }

            if (r >= l) {
                val temp = arr[r]
                arr[r] = arr[l]
                arr[l] = temp
            } else {
                val temp = arr[r]
                arr[r] = arr[left]
                arr[left] = temp
                break
            }
        }
        quickSort(arr, left, r - 1)
        quickSort(arr, r + 1, right)
    }
}