package yichen

fun main(){
//    println(MaximumCount().getMaximumCount(arrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))
//    println(MaximumCount().getMaximumCount2(arrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))


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

    fun getMaximumCount2(nums: Array<Int>, k: Int): Int {
        val baseCount = nums.count { it == k }
        val unique = nums.toSet() - k
        var maxGain = 0

        for (x in unique) {
            var gain = 0
            var best = 0

            for (num in nums) {
                if (num == x) gain += 1
                else if (num == k) gain -= 1

                // 如果 gain 变负数就重置（意味着前面贡献变差）
                if (gain < 0) gain = 0
                best = maxOf(best, gain)
            }

            maxGain = maxOf(maxGain, best)
        }

        return baseCount + maxGain
    }
}