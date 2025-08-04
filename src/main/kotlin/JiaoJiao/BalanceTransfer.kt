package JiaoJiao


fun main() {
    val s = BalanceTransfer()

    val testCases = listOf(
        // case 1: 简单案例（两个交易，刚好可以互相抵消）
        arrayOf(
            intArrayOf(0, 1, 10),
            intArrayOf(1, 0, 10)
        ) to 0,

        // case 2: 不成对，需要再加操作来结清
        arrayOf(
            intArrayOf(0, 1, 10),
            intArrayOf(2, 0, 5)
        ) to 2,

        // case 3: 三人循环转账，只有一笔可平
        arrayOf(
            intArrayOf(0, 1, 10),
            intArrayOf(1, 2, 10),
            intArrayOf(2, 0, 10)
        ) to 0,

        // case 4: 四人复杂互欠
        arrayOf(
            intArrayOf(0, 1, 10),
            intArrayOf(2, 0, 5),
            intArrayOf(3, 4, 7),
            intArrayOf(4, 2, 5),
            intArrayOf(1, 3, 2)
        ) to 3,

        // case 5: 所有人最后余额都是0（无需额外转账）
        arrayOf(
            intArrayOf(0, 1, 5),
            intArrayOf(1, 2, 5),
            intArrayOf(2, 0, 5)
        ) to 0,

        // case 6: 最少操作不等于人数 - 1（存在合并抵消的可能）
        arrayOf(
            intArrayOf(0, 1, 10),
            intArrayOf(2, 0, 5),
            intArrayOf(2, 1, 5)
        ) to 2,

        arrayOf(
            intArrayOf(3, 1, 3),
            intArrayOf(3, 0, 5),
            intArrayOf(2, 4, 1),
            intArrayOf(2, 5, 1),
        ) to 4,
    )

    for ((index, pair) in testCases.withIndex()) {
        val (transactions, expected) = pair
        val result = s.minTransfers(transactions)
        println("Test Case ${index + 1}: Expected=$expected, Got=$result => ${if (result == expected) "✅ PASS" else "❌ FAIL"}")
    }
}


class BalanceTransfer {
    fun minTransfers(transactions: Array<IntArray>): Int {
        val idToBalanceMap = mutableMapOf<Int, Int>()
        for ((from, to, amount) in transactions) {
            idToBalanceMap[from] = idToBalanceMap.getOrDefault(from, 0) - amount
            idToBalanceMap[to] = idToBalanceMap.getOrDefault(to, 0) + amount
        }

        val balances = idToBalanceMap.values.filter { it != 0 }.toIntArray()

        fun dfs(start: Int): Int {
            if (start < balances.size  && balances[start] == 0) {
                return dfs(start + 1)
            }

            if (start == balances.size) {
                return 0
            }

            var min = Int.MAX_VALUE

            for (i in start + 1 until balances.size) {
                if (balances[start] * balances[i] < 0) {
                    balances[i] += balances[start]
                    min = minOf(min, 1 + dfs(start + 1) )
                    balances[i] -= balances[start]
                }
            }
            return min
        }
        return dfs(0)
    }
}
