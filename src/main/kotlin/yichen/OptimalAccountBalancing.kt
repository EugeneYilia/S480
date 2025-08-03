package yichen

class OptimalAccountBalancing {
    fun minTransfers(transactions: Array<IntArray>): Int{
        val idToBalanceMap = mutableMapOf<Int, Int>()
        for ((from, to, amount) in transactions){
            idToBalanceMap[from] = idToBalanceMap.getOrDefault(from, 0) - amount
            idToBalanceMap[to] = idToBalanceMap.getOrDefault(to, 0) + amount
        }

        val balances = idToBalanceMap.values.filter{it != 0}.toIntArray()


    }
}