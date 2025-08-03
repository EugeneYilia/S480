package yichen

data class Transaction(val from: Int, val to: Int, val amount: Int)
data class Result(
    val minTransfers: Int,
    val path: List<Transaction>,
    val finalBalances: List<Pair<Int, Int>>
)

fun main() {

    //                     0  1   2   3   4   5   6   7
    val creditors = listOf(5, 10, 20, 30, 40, 50, 60, 70)
    //                   8  9  10  11   12  13  14  15
    val debtors = listOf(5, 10, 20, 25, 35, 40, 80, 90)

    // 构造净余额（positive = 多的钱, negative = 少的钱）
    val balances = mutableListOf<Pair<Int, Int>>()
    for ((i, amt) in creditors.withIndex()) balances.add(i to amt)
    for ((i, amt) in debtors.withIndex()) balances.add(i + creditors.size to -amt)

    val result = dfsWithTrace(0, balances)

    println("✅ 最小转账次数: ${result.minTransfers}")
    println("📋 转账明细:")
    result.path.forEach {
        println("Person#${it.from} → Person#${it.to}: ${it.amount}")
    }

    println("📌 未结清的人余额状态:")
    result.finalBalances.filter { it.second != 0 }.forEach {
        println("Person#${it.first} 最终净额: ${it.second}")
    }
}

fun dfsWithTrace(
    start: Int,
    balances: MutableList<Pair<Int, Int>>
): Result {
    var idx = start
    while (idx < balances.size && balances[idx].second == 0) idx++
    if (idx == balances.size) return Result(0, emptyList(), balances.map { it.copy() })

    var min = Int.MAX_VALUE
    var bestPath: List<Transaction> = emptyList()
    var bestBalances: List<Pair<Int, Int>> = emptyList()

    for (i in idx + 1 until balances.size) {
        val a = balances[idx]
        val b = balances[i]

        if (a.second * b.second < 0) {
            val amount = minOf(kotlin.math.abs(a.second), kotlin.math.abs(b.second))

            // 原地修改
            balances[idx] = a.copy(second = a.second - if (a.second > 0) amount else -amount)
            balances[i] = b.copy(second = b.second + if (a.second > 0) amount else -amount)

            val subResult = dfsWithTrace(idx + 1, balances)

            // 回溯
            balances[idx] = a
            balances[i] = b

            if (subResult.minTransfers != Int.MAX_VALUE && subResult.minTransfers + 1 < min) {
                min = subResult.minTransfers + 1
                bestPath = listOf(
                    Transaction(
                        from = if (a.second > 0) a.first else b.first,
                        to = if (a.second > 0) b.first else a.first,
                        amount = amount
                    )
                ) + subResult.path
                bestBalances = subResult.finalBalances
            }
        }
    }

    return if (min == Int.MAX_VALUE) {
        Result(0, emptyList(), balances.map { it.copy() }) // 没找到路径也要保留状态
    } else {
        Result(min, bestPath, bestBalances)
    }
}
