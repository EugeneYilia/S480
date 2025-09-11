package JiaoJiao

fun main(){
    println(removeDuplicateLetters("bcabc"))
}

fun removeDuplicateLetters(s: String): String {

    val slowFindList = mutableListOf<Int>()
    val quickFindList = mutableSetOf<Int>()
    var finalString = ""

    fun compareEachNumber(newList: List<Int>, oldList: List<Int>, i: Int): Boolean {

        if (i < newList.size && newList[i] == oldList[i]) {
                compareEachNumber(newList, oldList, i + 1)
        } else if (i < newList.size && newList[i] < oldList[i]) {
                return true
        } else{
                return false
        }

        return true
    }

    for (c in s) {
        val number = c - 'a' + 1
        if (number in quickFindList) {
            val compareList = slowFindList.toMutableList()
            compareList.remove(number)
            compareList.add(number)
            val isReplace = compareEachNumber(compareList, slowFindList, 0)

            println(isReplace)

            if (isReplace) {
                slowFindList.remove(number)
                slowFindList.add(number)
                finalString.replace(Regex(c.toString()), "")//未删掉

                println("one:" + finalString)

                finalString += c
            }

        } else {
            quickFindList.add(number)
            slowFindList.add(number)
            finalString += c
        }
    }
    return finalString
}