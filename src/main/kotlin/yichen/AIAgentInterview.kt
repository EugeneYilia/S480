//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//你可以按 任何顺序 返回答案。
//
//
//输入：n = 4, k = 2
//输出：
//[
//[2,4],
//[3,4],
//[2,3],
//[1,2],
//[1,3],
//[1,4],
//]
//
//输入：n = 1, k = 1
//输出：[[1]]
//
//
//1 <= n <= 20
//1 <= k <= n
//
//
//fun getAllKNumber(range: Int, outerAmount: Int): ArrayList<ArrayList<Integer>>{
//
//    var result : ArrayList<ArrayList<Integer>> = ArrayList()
//
//    fun getCombinition(start: Int, end: Int, amount: Int, temp: ArrayList<Integer>){
//        if(amount == 0 || start > end) {
//            if(temp.size == outerAmount){
//                result.add(temp)
//            }
//        }
//
//        for(num in start..range){
//            var cache = ArrayList(temp)
//            cache.add(num)
//            getCombinition(num + 1, end, amount - 1, cache)
//        }
//    }
//
//    if(range < 1 || amount <= 0){
//        return ArrayList()
//    }
//
//    for(num in 1..range){
//        getCombinition(num + 1, range, outerAmount -1, ArrayList().also{it.add(num)})
//    }
//
//
//    return result
//}
//
//
//
//
//
//
//
//a,b,c
//
//
//a=1 and c=2
//a =1 and b < 100
//a =1 and b < 100 and c =3
//


// n条路径，每条路径有一个成本函数f(x), 总和为m的离散值输入到成本函数中，其中m = x0 + x1 + x2 + ... + xn 求总成本最大（近似）的算法, 算法要从并行角度、近似最优解去考虑
