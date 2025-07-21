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

        val resultArray = arrayListOf<String>()

        fun calc(expression: String){
            fun calc(first: Int, second: Int, operator: Char): Int{
                if(operator == '+'){
                    return first + second
                } else if (operator == '-'){
                    return first - second
                }

                throw IllegalArgumentException("operator not recognized")
            }

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
                    resultArray.add(expression)
                }
            } else {
                if(first.toInt() == target){
                    resultArray.add(expression)
                }
            }
        }

        fun findQualifiedExpression(index:Int, buffer: String){
            if(index == srcArray.size){
                calc("${buffer}9")
                return
            }

            for(src in srcArray[index]){
                findQualifiedExpression(index+1, buffer + src)
            }
        }

        findQualifiedExpression(0, "")

        return resultArray
    }
}