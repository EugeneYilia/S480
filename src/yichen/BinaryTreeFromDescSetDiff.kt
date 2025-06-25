package yichen

fun main(){
    val data: Array<IntArray> = arrayOf(
        intArrayOf(20, 15, 1),
        intArrayOf(20, 17, 0),
        intArrayOf(50, 20, 1),
        intArrayOf(50, 80, 0),
        intArrayOf(80, 19, 1)
    )

    val result = BinaryTreeFromDescSetDiff().createBinaryTree(data)
    print(result?.`val`)
}

class BinaryTreeFromDescSetDiff {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun createBinaryTree(descriptions: Array<IntArray>): TreeNode? {
        val map = HashMap<Int, TreeNode>()
        val parentNodeSet = mutableSetOf<Int>()
        val childNodeSet = mutableSetOf<Int>()

        for (description in descriptions) {
            var parentNode = map.get(description[0])
            var childNode = map.get(description[1])

            if(parentNode == null){
                parentNode = TreeNode(description[0])
                map.put(description[0], parentNode)
            }

            if(childNode == null){
                childNode = TreeNode(description[1])
                map.put(description[1], childNode)
            }

            val isLeft = description[2] == 1
            if(isLeft){
                parentNode.left = childNode
            } else {
                parentNode.right = childNode
            }

            parentNodeSet.add(description[0])
            childNodeSet.add(description[1])
        }

        return map.get((parentNodeSet-childNodeSet).first())
    }
}