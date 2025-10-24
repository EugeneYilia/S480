package yichen

import java.util.LinkedList

fun newArray(src: IntArray): IntArray {
    val newArray = IntArray(src.size)

    for (index in 0 until src.size) {
        newArray[index] = src[index]
    }

    return newArray
}

fun permute(nums: IntArray): List<List<Int>> {
    val result = LinkedList<List<Int>>()

    fun getAllResultInternal(nums: IntArray, idxArray: IntArray, buffer: IntArray, index: Int) {
        for (idx in 0 until nums.size) {
            if (idxArray[idx] == 0) {
                val newIndexArray = newArray(idxArray)
                val newBuffer = newArray(buffer)

                newBuffer[index] = nums[idx]
                newIndexArray[idx] = 1

                if (index == nums.size - 1) {
                    result.add(newBuffer.toList())
                } else {
                    getAllResultInternal(nums, newIndexArray, newBuffer, index + 1)
                }
            }
        }
    }

    getAllResultInternal(nums, IntArray(nums.size), IntArray(nums.size), 0)

    return result
}


// 可以引⼊的库和版本相关请参考 “环境说明”
// Please refer to the "Environmental Notes" for the libraries and versions that can be introduced.

fun main() {
    for (result in getAllResult(intArrayOf(1, 2, 3))) {
        println(result.contentToString())
    }
}


fun getAllResult(nums: IntArray): List<IntArray> {
    val result = ArrayList<IntArray>()

    fun getAllResultInternal(nums: IntArray, idxArray: IntArray, buffer: IntArray, index: Int) {
        for (idx in 0 until nums.size) {
            if (idxArray[idx] == 0) {
                val newIndexArray = newArray(idxArray)
                val newBuffer = newArray(buffer)

                newBuffer[index] = nums[idx]
                newIndexArray[idx] = 1

                if (index == nums.size - 1) {
                    result.add(newBuffer)
                } else {
                    getAllResultInternal(nums, newIndexArray, newBuffer, index + 1)
                }
            }
        }
    }

    getAllResultInternal(nums, IntArray(nums.size), IntArray(nums.size), 0)

    return result
}



