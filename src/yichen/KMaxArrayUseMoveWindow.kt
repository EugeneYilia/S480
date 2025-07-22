package yichen

import java.util.ArrayList

fun main() {
    println(KMaxArrayUseMoveWindow().getMaxKByValue(intArrayOf(7, 9, 2, 9, 9, 8, 3, 7, 2), 3))
    println(KMaxArrayUseMoveWindow().getMaxKByIndex(intArrayOf(7, 9, 2, 9, 9, 8, 3, 7, 2), 3))
}

class KMaxArrayUseMoveWindow {
    fun getMaxKByValue(src: IntArray, k: Int): ArrayList<Int>? {
        if (k <= 0) return null
        var size = src.size
        var windowSize = minOf(src.size, k)
        var left = 0
        var right = 0
        var window = src.clone()
        for (i in 0 until windowSize) {
            while (right > left && window[right - 1] < src[i]) {
                right--
            }
            window[right++] = src[i]
        }
        var result = arrayListOf(window[left])
        for (i in k until size) {
            if (window[left] == src[i - windowSize]) {
                left++
            }
            while (right > left && window[right - 1] < src[i]) {
                right--
            }
            window[right++] = src[i]
            result.add(window[left])
        }
        return result
    }

    fun getMaxKByIndex(src: IntArray, k: Int): ArrayList<Int>? {
        if (k <= 0) return null
        var size = src.size
        var windowSize = minOf(src.size, k)
        var left = 0
        var right = 0
        var window = src.clone()
        for (i in 0 until windowSize) {
            while (right > left && src[window[right - 1]] <= src[i]) {
                right--
            }
            window[right++] = i
        }
        var result = arrayListOf(src[window[left]])
        for (i in k until size) {
            if (window[left] == i - windowSize) {
                left++
            }
            while (right > left && src[window[right - 1]] <= src[i]) {
                right--
            }
            window[right++] = i
            result.add(src[window[left]])
        }
        return result
    }
}