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
            val resultOldString = slowFindList.reversed().joinToString("")
            val resultNewString = compareList.reversed().joinToString("")
            val resultNumNew = resultNewString.toInt()
            val resultNumOld = resultOldString.toInt()

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