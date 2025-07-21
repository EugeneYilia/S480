package yichen

fun main() {
    println(CoinChange().change(5, intArrayOf(1 ,2 ,5)))
}

class CoinChange {

    fun change(amount: Int, coins: IntArray): Int {
        val combinations = IntArray(amount + 1)
        combinations[0] = 1
        for(coin in coins) {
            for (index in coin..amount){
                combinations[index] += combinations[index - coin]
            }
        }

        return combinations[amount]
    }
}