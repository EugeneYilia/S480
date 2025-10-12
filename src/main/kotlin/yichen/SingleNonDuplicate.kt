package yichen

// LeetCode 540: Single Element in a Sorted Array
// 中文链接（力扣中国）：
// https://leetcode.cn/problems/single-element-in-a-sorted-array
// 英文链接（LeetCode 国际站）：
// https://leetcode.com/problems/single-element-in-a-sorted-array

// [11, 11, 22, 22, 33, 33, 4, 66, 66]
//  0   1   2   3   4   5   6  7   8

// [11, 11, 22, 33, 33, 4, 4, 66, 66]           // 4 偶数 中间元素和后面不等 -> 单元素在前
//  0   1   2   3   4   5  6  7   8

// [11, 11, 22, 22, 33, 4, 4, 66, 66]           // 4 偶数 中间元素和后面不等 -> 单元素在前
//  0   1   2   3   4   5  6  7   8

// [11, 11, 22, 22, 33, 33, 44, 44, 66]         // 4 偶数 中间元素和后面相等 -> 单元素在后
//  0   1   2   3   4   5   6   7   8

// [1,2,2,3,3,4,4,8,8]

//  0,1,2,3,4,5,6      3 奇数 中间位置和后面相等 -> 单元素在前
// [0,1,1,2,2,5,5]

//  0,1,2,3,4,5,6      3 奇数 中间元素和后面不等 -> 单元素在后
// [0,0,1,1,2,5,5]
// [0,0,1,1,2,2,5]

// 数组递增而不连续
// 奇数个元素，每次查询的middle位置为偶数位置
// 比如0-8，9个数，查询4，之后查询2或者6
// 如果偶数位置的元素和后面紧跟着的元素都是相同的值，那么这个有问题的奇数在后面，否则在前面
// 奇数元素一定出现在偶数位上
fun singleNonDuplicate(nums: IntArray): Int {
    return singleNonDuplicateInternal(nums, 0, nums.size - 1)
}

// [1,1,2,3,3,4,4,8,8]
fun singleNonDuplicateInternal(nums: IntArray, start: Int, end: Int): Int {
    if (end == start) {
        return nums[end]
    }

    val middle = (start + end) / 2
    val isOdd = (end - start) / 2 % 2 == 1

    return if (isOdd) {
        if (nums[middle] == nums[middle + 1]) {
            singleNonDuplicateInternal(nums, start, middle - 1)
        } else {
            singleNonDuplicateInternal(nums, middle + 1, end)
        }
    } else {
        if (nums[middle] == nums[middle + 1]){
            singleNonDuplicateInternal(nums, middle + 2, end)
        } else {
            singleNonDuplicateInternal(nums, start, middle)
        }
    }
}

fun main() {
    println(singleNonDuplicate(intArrayOf(0, 1, 1, 2, 2, 5, 5)))
}