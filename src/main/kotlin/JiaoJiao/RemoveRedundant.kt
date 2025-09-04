package JiaoJiao

fun main(){
    println(removeDuplicateLetters("bcabc"))
}

fun removeDuplicateLetters(s: String): String {

    val slowFindList = mutableListOf<Int>()
    val quickFindList = mutableSetOf<Int>()
    var finalString = ""

    fun compareEachNumber(newList: List<Int>, oldList: List<Int>, i: Int): Boolean {
        while (i < newList.size){
            if (newList[i] == oldList[i]){
                compareEachNumber(newList, oldList, i + 1)
            }else if (newList[i] < oldList[i]){
                return true
            }else {
                return false
            }
        }
        return false
    }

    for (c in s) {
        val number = c - 'a' + 1
        if (number in quickFindList) {
            val compareList = slowFindList.toMutableList()
            compareList.remove(number)
            compareList.add(number)
            val isReplace = compareEachNumber(compareList, slowFindList, 0)
            if (isReplace) {
                slowFindList.remove(number)
                slowFindList.add(number)
                finalString.replace(c.toString(), "")
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