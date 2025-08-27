package yichen

// https://leetcode.cn/problems/merge-intervals
//
// 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
//
//
//
//示例 1：
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//示例 2：
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//提示：
//
//1 <= intervals.length <= 104
//intervals[i].length == 2
//0 <= starti <= endi <= 104
class MergeRange {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        return mergeArray(intervals.sortedBy { it[0] }.toTypedArray())
    }

    private fun mergeArray(src: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var start = src[0][0];
        var end = src[0][1];
        for (index in 1..<src.size) {
            val point = src[index]
            if (end >= point[0]) {
                end = maxOf(point[1], end)
            } else {
                result.add(intArrayOf(start, end))
                start = point[0]
                end = point[1]
            }
        }
        result.add(intArrayOf(start, end))

        return result.toTypedArray()
    }

}