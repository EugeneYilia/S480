package yichen

import kotlin.rem

class ReverseArrayLastK {
    // 最快 但是费空间
    fun reverseKArray(src: IntArray, k: Int): IntArray {
        if (src.size <= 1) {
            return src
        }
        val reverseCount = k % src.size
        return src.copyOfRange(src.size - reverseCount, src.size) + src.copyOfRange(0, src.size - reverseCount)
    }

    // count记录
    // 空间复杂度O(1)  时间复杂度最差O(n*k)
    fun rotate(nums: IntArray, k: Int): Unit {
        if (nums.size <= 1 || k == nums.size || k <= 0) {
            return
        }

        val rotateCount = k % nums.size
        for (index in 0 until rotateCount) {
            var newIndex = index + rotateCount
            var lastElement = nums[index]
            while (newIndex != index) {
                val newElement = nums[newIndex]
                nums[newIndex] = lastElement
                newIndex = (newIndex + rotateCount) % nums.size

                lastElement = newElement
            }
            nums[index] = lastElement
        }
    }
}

fun main() {
//    ReverseArrayLastK().reverseKArray(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 3).contentToString()

    val src = intArrayOf(1,2,3,4,5,6,7)
    ReverseArrayLastK().rotate(src, 3)
    println(src.contentToString())
}
