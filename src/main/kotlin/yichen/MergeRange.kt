package yichen

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