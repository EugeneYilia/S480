package yichen

fun twoSum(nums: IntArray, target: Int): IntArray {
    val value2IndexMap = HashMap<Int, Int>()

    for ((index, num) in nums.withIndex()) {
        val needValue = target - num

        if (value2IndexMap.contains(needValue)) {
            return intArrayOf(value2IndexMap[needValue]!!, index)
        } else {
            value2IndexMap[num] = index
        }
    }

    return intArrayOf()
}