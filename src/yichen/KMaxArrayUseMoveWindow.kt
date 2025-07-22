package yichen

import java.util.ArrayList

fun main() {
    println(KMaxArrayUseMoveWindow().getMaxKByValue(intArrayOf(7, 9, 2, 9, 9, 8, 3, 7, 2), 3))
    println(KMaxArrayUseMoveWindow().getMaxKByIndex(intArrayOf(7, 9, 2, 9, 9, 8, 3, 7, 2), 3))
}

class KMaxArrayUseMoveWindow {
    fun getMaxKByValue(nums: IntArray, k: Int): ArrayList<Int>? {
        if (k <= 0) return null
        var size = nums.size
        var windowSize = minOf(nums.size, k)
        var left = 0
        var right = 0
        var window = nums.clone()
        for (i in 0 until windowSize) {
            while (right > left && window[right - 1] < nums[i]) {
                right--
            }
            window[right++] = nums[i]
        }
        var result = arrayListOf(window[left])
        for (i in windowSize until size) {
            if (window[left] == nums[i - windowSize]) {
                left++
            }
            while (right > left && window[right - 1] < nums[i]) {
                right--
            }
            window[right++] = nums[i]
            result.add(window[left])
        }
        return result
    }

    fun getMaxKByIndex(nums: IntArray, k: Int): ArrayList<Int>? {
        if (k <= 0) return null
        var size = nums.size
        var windowSize = minOf(nums.size, k)
        var left = 0
        var right = 0
        var window = nums.clone()
        for (i in 0 until windowSize) {
            while (right > left && nums[window[right - 1]] <= nums[i]) {
                right--
            }
            window[right++] = i
        }
        var result = arrayListOf(nums[window[left]])
        for (i in windowSize until size) {
            if (window[left] == i - windowSize) {
                left++
            }
            while (right > left && nums[window[right - 1]] <= nums[i]) {
                right--
            }
            window[right++] = i
            result.add(nums[window[left]])
        }
        return result
    }
}