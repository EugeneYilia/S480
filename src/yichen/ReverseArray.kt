package yichen

class ReverseArrayLastK {
    fun reverseKArray(src: IntArray, k:Int) : IntArray{
        if(src.size<=1){
            return src
        }
        val reverseCount = k % src.size
        return src.copyOfRange(src.size - reverseCount, src.size) + src.copyOfRange(0, src.size - reverseCount)
    }

    fun rotate(nums: IntArray, k: Int): Unit {

    }
}

fun main(){
    ReverseArrayLastK().reverseKArray(intArrayOf(1,2,3,4,5,6,7,8), 3).forEach { print("${it},") }
}
