package yichen

fun main(){
    println(CombineNumberToTarget().combineNumberToTarget(100))
}

class CombineNumberToTarget {
    // 1 + 2
    // 1 - 2
    // 12
    // 1 2 3 4 5 6 7 8 9    =>  100
    // 1 + 2 + 34 - 5 + 67 - 8 + 9 = 100
    fun combineNumberToTarget(target: Int): List<String>{
        val srcArray = ArrayList<ArrayList<String>>()

        for (i in 1..8) {
            srcArray.add(arrayListOf("${i}+", "${i}-", "${i}"))
        }

        var countArray = arrayListOf<String>()
        var valueArray = arrayListOf<String>()

        for (index in srcArray[0]){
            for (index2 in srcArray[1]){
                for (index3 in srcArray[2]){
                    for (index4 in srcArray[3]){
                        for (index5 in srcArray[4]){
                            for (index6 in srcArray[5]){
                                for (index7 in srcArray[6]){
                                    for (index8 in srcArray[7]){
                                        countArray.add(index + index2 + index3 + index4 + index5 + index6 + index7 + index8 + "9")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }



        fun calc(first: Int, second: Int, operator: Char): Int{
            if(operator == '+'){
                return first + second
            } else if (operator == '-'){
                return first - second
            }

            throw IllegalArgumentException("operator not recognized")
        }

        for (expression in countArray){
            var first = ""
            var second: String? = null
            var operator: Char? = null
            for (token in expression){
                if (token == '+' || token == '-'){
                    if(second != null){
                        first = calc(first.toInt(), second.toInt(), operator!!).toString()
                    }
                    operator = token
                    second = ""
                } else {
                    if(second != null){
                        second += token
                    } else {
                        first += token
                    }
                }
            }
            if(operator != null){
                val result = calc(first.toInt(), second!!.toInt(), operator)
                if(result == target){
                    valueArray.add(expression)
                }
            } else {
                if(first.toInt() == target){
                    valueArray.add(expression)
                }
            }
        }

        return valueArray
    }
}