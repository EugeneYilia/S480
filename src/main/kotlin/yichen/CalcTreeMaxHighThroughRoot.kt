package yichen

fun main(){
    val root = TreeNode(TreeNode(null, null, 1), TreeNode(null, null ,3), 2)

    val treeCalc = CalcTreeMaxHighThroughRoot()
    println(treeCalc.longestConsecutive(root))
}

// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/submissions/1816033858/
class CalcTreeMaxHighThroughRoot {
    fun longestConsecutive(root: TreeNode?): Int {
        root ?: return 0

        var maxHigh = 1

        fun calcHigh(node: TreeNode, high: IntArray): IntArray {
            var leftAscendingValue: Int? = null
            var leftDescendingValue: Int? = null

            var rightAscendingValue: Int? = null
            var rightDescendingValue: Int? = null

            val curLeftHigh = high.copyOf()
            if (node.left != null && node.left!!.`val` == node.`val` + 1) {
                curLeftHigh[0] = curLeftHigh[0] + 1
                curLeftHigh[1] = 1
                leftAscendingValue = calcHigh(node.left!!, curLeftHigh)[0]
            } else if (node.left != null && node.left!!.`val` == node.`val` - 1) {
                curLeftHigh[1] = curLeftHigh[1] + 1
                curLeftHigh[0]= 1
                leftDescendingValue = calcHigh(node.left!!, curLeftHigh)[1]
            } else if (node.left != null) {
                calcHigh(node.left!!, intArrayOf(1, 1))
            }

            val currentRightHigh = high.copyOf()
            if (node.right != null && node.right!!.`val` == node.`val` + 1) {
                currentRightHigh[1] = 1
                currentRightHigh[0] = currentRightHigh[0] + 1
                rightAscendingValue = calcHigh(node.right!!, currentRightHigh)[0]
            } else if (node.right != null && node.right!!.`val` == node.`val` - 1) {
                currentRightHigh[0] = 1
                currentRightHigh[1] = currentRightHigh[1] + 1
                rightDescendingValue = calcHigh(node.right!!, currentRightHigh)[1]
            } else if (node.right != null) {
                calcHigh(node.right!!, intArrayOf(1, 1))
            }

            maxHigh = maxOf((leftDescendingValue ?: high[1]) - high[1] + (rightAscendingValue ?: high[0]) - high[0] + 1, maxHigh)
            maxHigh = maxOf((leftAscendingValue ?: high[0]) - high[0] + (rightDescendingValue ?: high[1]) - high[1] + 1, maxHigh)

            val descendingValue = maxOf(leftDescendingValue ?: high[1], rightDescendingValue ?: high[1])
            val ascendingValue = maxOf(leftAscendingValue ?: high[0], rightAscendingValue ?: high[0])

            maxHigh = maxOf(maxHigh, ascendingValue)
            maxHigh = maxOf(maxHigh, descendingValue)

            return intArrayOf(ascendingValue, descendingValue)
        }

        calcHigh(root, intArrayOf(1, 1))

        return maxHigh
    }
}