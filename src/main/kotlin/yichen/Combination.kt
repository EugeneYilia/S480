package yichen

// 77. 组合
//已解答
//中等
//相关标签
//premium lock icon
//相关企业
//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
//你可以按 任何顺序 返回答案。
//
//
//
//示例 1：
//
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
//示例 2：
//
//输入：n = 1, k = 1
//输出：[[1]]
//
//
//提示：
//
//1 <= n <= 20
//1 <= k <= n
fun combine(n: Int, k: Int): List<List<Int>> {
    if (n < 1 || k <= 0) {
        return ArrayList()
    }

    val result: ArrayList<ArrayList<Int>> = ArrayList()

    fun getCombination(start: Int, amount: Int, temp: ArrayList<Int>) {
        if (amount == 0 || start > n) {
            if (temp.size == k) {
                result.add(temp)
            }
        }

        for (num in start..n) {
            val cache = ArrayList(temp).also { it.add(num) }
            getCombination(num + 1, amount - 1, cache)
        }
    }

    for (num in 1..n) {
        getCombination(num + 1, k - 1, ArrayList<Int>().also { it.add(num) })
    }

    return result
}