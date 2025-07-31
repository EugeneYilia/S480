package yichen

fun main(){
    println(CountHillValley().countHillValley(intArrayOf(2, 4, 1, 1 ,6, 5)))
}

class CountHillValley {
    fun countHillValley(nums: IntArray): Int {
        if(nums.size <=2) {
            return 0
        }

        var waveEdgeCount = 0

        var last = nums[0]
        var curr = nums[1]
        var next: Int

        for (i in 2..nums.size - 1) {
            if(curr == last){
                curr = nums[i]
                continue
            }

            next = nums[i]
            if(next == curr){
                continue
            } else {
                if(curr > last && curr > next){
                    waveEdgeCount += 1
                }

                if(curr < last && curr < next){
                    waveEdgeCount += 1
                }
                last = curr
                curr = next
            }
        }

        return waveEdgeCount
    }
}