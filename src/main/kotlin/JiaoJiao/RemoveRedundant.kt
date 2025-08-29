package JiaoJiao



fun removeDuplicateLetters(s: String): String {

    val slowFindList = mutableListOf<Int>()
    val quickFindList = mutableSetOf<Int>()
    var finalString = ""

    for (c in s.reversed()) {
        val number = c - 'a' + 1
        if (number in quickFindList) {
            val compareList = slowFindList.toMutableList()
            compareList.remove(number)
            compareList.add(number)
            val lengthOfList = slowFindList.size
            var resultNumNew = 0
            var resultNumOld = 0

            for (i in 0 until lengthOfList) {
                resultNumNew += (i + 1) * compareList[i]
                resultNumOld += (i + 1) * slowFindList[i]
            }

            if (resultNumOld >= resultNumNew) {
                slowFindList.remove(number)
                slowFindList.add(number)
                finalString = finalString.replace(c.toString(), "")
                finalString += c
            }


        } else {
            quickFindList.add(number)
            slowFindList.add(number)
            finalString += c
        }
    }
    return finalString.reversed()
}