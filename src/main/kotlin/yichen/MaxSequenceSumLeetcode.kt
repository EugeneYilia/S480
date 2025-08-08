package yichen

import kotlin.math.max

class MaxSequenceSumLeetcode {
    fun maxSubArray(nums: IntArray): Int {
        var sum = 0
        var maxSum = Int.MIN_VALUE

        for (i in nums.indices) {
            if (sum < 0) {
                sum = 0
            }

            sum += nums[i]

            maxSum = max(maxSum, sum)
        }

        return maxSum
    }
}