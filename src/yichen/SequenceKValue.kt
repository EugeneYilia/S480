package yichen

fun main() {
    val sequenceKValue = SequenceKValue()

    val expected = listOf(
        1, 3, 4, 7, 9, 10, 13, 15, 19, 21,
        22, 27, 28, 31, 39, 40, 43, 57, 58, 63,
        64, 67, 79, 84, 85, 87, 93, 103, 109, 120,
        121, 124, 127, 129, 133, 145, 157, 159, 169, 171,
        172, 183, 189, 190, 193, 195, 199, 201, 211, 213,
        217, 219, 220, 229, 235, 237, 247, 249, 250, 255,
        259, 271, 273, 283, 289, 291, 301, 309, 313, 319,
        321, 327, 339, 361, 363, 367, 375, 381, 390, 403,
        409, 421, 427, 433, 445, 447, 453, 469, 471, 475,
        481, 489, 499, 507, 511, 519, 529, 543, 553, 561
    )

    var allPass = true
    for (i in expected.indices) {
        val actual = sequenceKValue.valueAtK(i + 1)
        if (actual != expected[i]) {
            println("❌ Failed at n=${i + 1}: Expected ${expected[i]}, got $actual")
            allPass = false
        } else {
            println("√ Success at n=${i + 1}: Expected ${expected[i]}, got $actual")
        }
    }

    if (allPass) {
        println("✅ All ${expected.size} test cases passed!")
    }

    // Spot check for larger n
    val spotChecks = listOf(100, 150, 200, 300, 500, 1000)
    for (n in spotChecks) {
        val value = sequenceKValue.valueAtK(n)
        println("nthSpecial($n) = $value")
    }
}

class SequenceKValue {
    fun valueAtK(k: Int): Int {
        var sequence2Index = 0
        var sequence3Index = 0

        val result = linkedSetOf(1)

        while (result.size < k){
            if(sequence2Index <= result.size -1 && sequence3Index <= result.size -1) {
                if (2 * result.elementAt(sequence2Index) <= 3 * result.elementAt(sequence3Index)) {
                    result.add(2 * result.elementAt(sequence2Index) + 1)
                    sequence2Index++
                } else {
                    result.add(3 * result.elementAt(sequence3Index) + 1)
                    sequence3Index++
                }
            } else if(sequence2Index <= result.size -1) {
                result.add(2 * result.elementAt(sequence2Index) + 1)
                sequence2Index++
            } else if(sequence3Index <= result.size -1) {
                result.add(3 * result.elementAt(sequence3Index) + 1)
                sequence3Index++
            }
        }

        return result.elementAt(k - 1)
    }
}