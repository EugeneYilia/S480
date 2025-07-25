package yichen

class LargestValidFeatureSet {

    // 7/15
    fun findLargestValidFeatureSet(feature1: Array<Int>, feature2: Array<Int>): Int {
        val size = feature1.size

        val trendUp = IntArray(size){1}
        val trendDown = IntArray(size){1}

        for(i in 1 until size){
            for(j in 0 until i){
                val f1Delta = feature1[i] - feature1[j]
                val f2Delta = feature2[i] - feature2[j]

                if(f1Delta > 0 && f2Delta > 0){
                    trendUp[i] = maxOf(trendUp[i], trendUp[j] + 1)
                } else if(f1Delta < 0 && f2Delta < 0){
                    trendDown[i] = maxOf(trendDown[i], trendDown[j] + 1)
                }
            }
        }

        return maxOf(trendUp.max(), trendDown.max())
    }
}