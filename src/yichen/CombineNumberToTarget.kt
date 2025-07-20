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
        var oneArray = arrayListOf<String>("1+", "1-", "1")
        var twoArray = arrayListOf<String>("2+", "2-", "2")
        var threeArray = arrayListOf<String>("3+", "3-", "3")
        var fourArray = arrayListOf<String>("4+", "4-", "4")
        var fiveArray = arrayListOf<String>("5+", "5-", "5")
        var sixArray = arrayListOf<String>("6+", "6-", "6")
        var sevenArray = arrayListOf<String>("7+", "7-", "7")
        var eightArray = arrayListOf<String>("8+", "8-", "8")
        var countArray = arrayListOf<String>()
        var valueArray = arrayListOf<String>()
        for (index in oneArray){
            for (index2 in twoArray){
                for (index3 in threeArray){
                    for (index4 in fourArray){
                        for (index5 in fiveArray){
                            for (index6 in sixArray){
                                for (index7 in sevenArray){
                                    for (index8 in eightArray){
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