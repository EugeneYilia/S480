package JiaoJiao



fun removeDuplicateLetters(s: String): String {

    val slowFindList = mutableListOf<Int>()
    val quickFindList = mutableSetOf<Int>()
    var finalString = ""

    for (c in s) {
        val number = c - 'a' + 1
        if (number in quickFindList) {
            val compareList = slowFindList.toMutableList()
            compareList.remove(number)
            compareList.add(number)

            val lengthOfList = slowFindList.size
            var isReplace = false

            fun compareEachNumber(oneList: List<Int>, twoList: List<Int>, i: Int): Boolean {
                if(oneList[i] > twoList[i]) {
                    return true
                }else{
                    return false
                }
            }

            var i = 0
            if (!compareEachNumber(slowFindList, compareList, i)){
                i = i + 1
                compareEachNumber(slowFindList, compareList, i)
            }


        } else {
            quickFindList.add(number)
            slowFindList.add(number)
            finalString += c
        }
    }
    return finalString
}