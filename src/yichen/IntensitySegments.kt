package yichen

import java.util.TreeMap

fun main() {
    val segments = IntensitySegments()

    check(segments.toString() == "[]") { "Test 1 failed: $segments" }

    segments.add(10, 30, 1)
    check(segments.toString() == "[[10,1],[30,0]]") { "Test 2 failed: $segments" }

    segments.add(20, 40, 1)
    check(segments.toString() == "[[10,1],[20,2],[30,1],[40,0]]") { "Test 3 failed: $segments" }

    segments.add(10, 40, -2)
    check(segments.toString() == "[[10,-1],[20,0],[30,-1],[40,0]]") { "Test 4 failed: $segments" }

    segments.set(15, 35, 5)
    check(segments.toString() == "[[10,-1],[15,5],[35,-1],[40,0]]") { "Test 5 failed: $segments" }

    segments.set(10, 40, 0)
    check(segments.toString() == "[]") { "Test 6 failed: $segments" }

    segments.add(5, 15, 3)
    segments.add(10, 20, 2)
    segments.set(8, 12, 0)
    check(segments.toString() == "[[5,3],[8,0],[12,5],[15,2],[20,0]]") { "Test 7 failed: $segments" }

    segments.set(10, 10, 999)
    check(segments.toString() == "[[5,3],[8,0],[12,5],[15,2],[20,0]]") { "Test 8 failed: $segments" }

    println("✅ All tests passed!")
}

class IntensitySegments {
    val map = TreeMap<Int, Int>()

    // O(1)
    fun add(from: Int, to: Int, value: Int) {
        if(value == 0){
            return
        }

        if (from >= to){
            return
        }

        map[from] = (map[from] ?: 0) + value
        map[to] = (map[to] ?: 0) - value

        removeZero(from)
        removeZero(to)
    }

    // O(n)
    fun set(from: Int, to: Int, value: Int) {
        if (from >= to) {
            return
        }

        var sum = 0
        var fromSum : Int? = null

        val mapIter = map.iterator()
        while(mapIter.hasNext()) {
            val entry = mapIter.next()
            val key = entry.key
            val value = entry.value

            if(key >= from && fromSum == null){
                fromSum = sum
            }

            if(key > to){
                break
            }

            sum += value

            if(key >= from){
                mapIter.remove()
            }
        }

        map[from] = value - (fromSum ?: 0)
        map[to] = sum - value

        removeZero(from)
        removeZero(to)
    }

    private fun removeZero(index: Int) {
        if(map[index] == 0){
            map.remove(index)
        }
    }

    override fun toString() : String{
        var sum = 0
        val stringBuilder = StringBuilder("[")
        for (entry in map.entries) {
            sum += entry.value
            stringBuilder.append("[${entry.key},${sum}],")
        }
        if(stringBuilder.length > 1){
            stringBuilder.setLength(stringBuilder.length - 1)
        }
        return stringBuilder.append("]").toString()
    }
}