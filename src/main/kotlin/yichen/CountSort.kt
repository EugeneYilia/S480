package yichen

//[-50000,50000]
// [0,100000]
fun sortArray(nums: IntArray): IntArray {
    val countMap = IntArray(100001)

    for (num in nums) {
        countMap[num + 50000]++
    }

    val srcSize = nums.size
    val result = IntArray(srcSize)
    var resultIndex = 0

    for (i in 0..100000) {
        val curValue = i - 50000
        repeat(countMap[i]) {
            result[resultIndex++] = curValue
        }

        if (resultIndex == srcSize) {
            return result
        }
    }

    return result
}