package yichen

fun main(){
    println(MaximumCount().getMaximumCount(arrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))
}

class MaximumCount {

    fun getMaximumCount(nums: Array<Int>, k: Int): Int {
        val size = nums.size
        val baseCount = nums.count {it == k}
        var maxCount = baseCount

        for (l in 0 until size){
            val freq = mutableMapOf<Int, Int>()
            var rangeKCount = 0

            for (r in l until size) {
                if(nums[r] == k) {
                    rangeKCount++
                }

                freq[nums[r]] = freq.getOrDefault(nums[r],0) + 1

                maxCount = maxOf(maxCount, baseCount - rangeKCount + freq[nums[r]]!!)
            }
        }

        return maxCount
    }
}