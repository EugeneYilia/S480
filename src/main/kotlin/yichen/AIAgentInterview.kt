package yichen

给定一个区间数组，每个区间由起始点和结束点组成，需要将所有重叠的区间合并，返回一个不重叠的区间数组。

例：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6


fun mergeArray(src: Array<IntArray>): Array<IntArray>{
    var result = Array<IntArray>()
    var start = src[0][0];
    var end = src[0][1];
    for(index in 1..src.length() - 1){
        var point = src[index]
        if(end > point[0]){
            end = math.max(point[1], end)
        } else {
            result.add(intarrayOf(start, end))
            start = point[0]
            end = point[1]
        }
    }
    result.add(intarrayOf(start, end))

    return result
}











给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
你可以按 任何顺序 返回答案。


输入：n = 4, k = 2
输出：
[
[2,4],
[3,4],
[2,3],
[1,2],
[1,3],
[1,4],
]

输入：n = 1, k = 1
输出：[[1]]


1 <= n <= 20
1 <= k <= n


fun getAllKNumber(range: Int, outerAmount: Int): ArrayList<ArrayList<Integer>>{

    var result : ArrayList<ArrayList<Integer>> = ArrayList()

    fun getCombinition(start: Int, end: Int, amount: Int, temp: ArrayList<Integer>){
        if(amount == 0 || start > end) {
            if(temp.size == outerAmount){
                result.add(temp)
            }
        }

        for(num in start..range){
            var cache = ArrayList(temp)
            cache.add(num)
            getCombinition(num + 1, end, amount - 1, cache)
        }
    }

    if(range < 1 || amount <= 0){
        return ArrayList()
    }

    for(num in 1..range){
        getCombinition(num + 1, range, outerAmount -1, ArrayList().also{it.add(num)})
    }


    return result
}















































a,b,c


a=1 and c=2
a =1 and b < 100
a =1 and b < 100 and c =3

