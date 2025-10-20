package yichen

fun main() {
    println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"))
}


// dir or file
// level 0 store 0
// level 1 store 1
// level 2 store 2
fun lengthLongestPath(input: String): Int {
    var maxPathLength = 0

    val stack = IntArray(maxOf(input.length / 4, 4))

    var isDir = true
    var contentLength = 0
    var levelCount = 0

    fun calcNewContent() {
        if (isDir) {
            stack[levelCount] = contentLength + 1
        } else {
            var currentLength = contentLength
            for (index in 0 until levelCount) {
                currentLength += stack[index]
            }

            maxPathLength = maxOf(maxPathLength, currentLength)
        }

        levelCount = 0
        contentLength = 0
        isDir = true
    }

    for (char in input) {
        if (char == '\n') {
            calcNewContent()
            continue
        }

        if (char == '\t') {
            levelCount++
            continue
        }

        contentLength++
        if (char == '.') {
            isDir = false
        }
    }

    calcNewContent()

    return maxPathLength
}

