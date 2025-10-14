import java.util.LinkedList

class LevelOrderTree {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        root ?: return emptyList()

        val nodes = arrayOfNulls<TreeNode>(2001)
        val result = LinkedList<List<Int>>()
        var currentIndex = 0
        var validIndex = 1
        nodes[0] = root
        result.add(LinkedList<Int>().apply { add(root.`val`) })

        while (true) {
            val layerNodes = LinkedList<Int>()

            // 3   3
            // 3 4 5 [3,6)
            val curValidIndex = validIndex
            for (index in (currentIndex until curValidIndex)) {
                val node = nodes[index]

                val leftNode = node!!.left
                if (leftNode != null) {
                    layerNodes.add(leftNode.`val`)
                    nodes[validIndex++] = leftNode
                }

                val rightNode = node.right
                if (rightNode != null) {
                    layerNodes.add(rightNode.`val`)
                    nodes[validIndex++] = rightNode
                }
            }

            if (layerNodes.isNotEmpty()) {
                result.add(layerNodes)
            } else {
                break
            }
            currentIndex = curValidIndex
        }

        return result
    }
}