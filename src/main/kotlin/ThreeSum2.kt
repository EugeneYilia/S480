fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    val result = mutableListOf<MutableList<Int>>()

    val lastIndex = nums.lastIndex

    var lastNum: Int? = null
    for (index in 0..lastIndex - 2) {
        val num = nums[index]
        if (num == lastNum) {
            continue
        }

        var leftIndex = index + 1
        var rightIndex = lastIndex

        var lastLeftNum: Int? = null
        var lastRightNum: Int? = null

        while (true) {
            while (leftIndex < rightIndex && nums[leftIndex] == lastLeftNum) {
                leftIndex++
            }

            while (leftIndex < rightIndex && nums[rightIndex] == lastRightNum) {
                rightIndex--
            }

            if (leftIndex >= rightIndex) {
                break
            }

            val leftNum = nums[leftIndex]
            val rightNum = nums[rightIndex]
            val sum = leftNum + rightNum + num
            if (sum == 0) {
                result.add(mutableListOf(num, leftNum, rightNum))
                leftIndex++
                rightIndex--

                lastLeftNum = leftNum
                lastRightNum = rightNum
            } else if (sum < 0) {
                leftIndex++
                lastLeftNum = leftNum
            } else {
                rightIndex--
                lastRightNum = rightNum
            }
        }

        lastNum = num
    }

    return result
}