package yichen

import java.util.*

fun main() {
    println(removeDuplicateLetters("bcabc"))
}

fun removeDuplicateLetters(s: String): String {
    val positionMap = TreeMap<Char, ArrayList<Int>>()
    for ((index, c) in s.withIndex()) {
        positionMap.computeIfAbsent(c){ArrayList()}.add(index)
    }

    val positionList = positionMap.entries.toList()
    val letterSize = positionMap.size

    var result: String? = null

    fun convertCharsToString(combination: TreeMap<Int, Char>):String{
        var newString = ""

        for(ch in combination.values){
            newString += ch
        }

        return newString
    }

    fun compareAndUpdate(newString: String){
        for(i in 0 until newString.length){
            if(newString[i] < result!![i]){
                result = newString
                return
            } else if(newString[i] > result!![i]){
                return
            }
        }
    }

    fun combineLetter(start: Int, combination: TreeMap<Int, Char>){
        if(start == letterSize){
            if(result == null){
                result = convertCharsToString(combination)
            } else {
                compareAndUpdate(convertCharsToString(combination))
            }
        }

        val ch = positionList[start].key
        val positions = positionList[start].value
        for(i in 0 until positions.size){
            combineLetter(start + 1, TreeMap(combination).also {
                it.put(positions[i], ch)
            })
        }
    }

    combineLetter(0, TreeMap())

    return result!!
}