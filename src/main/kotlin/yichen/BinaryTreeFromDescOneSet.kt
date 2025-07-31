package yichen

class BinaryTreeFromDescOneSet {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun createBinaryTree(descriptions: Array<IntArray>): TreeNode? {
        val map = HashMap<Int, TreeNode>()
        val nodeList = mutableSetOf<Int>()

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

            nodeList.add(description[0])
        }

        for (description in descriptions) {
            nodeList.remove(description[1])
        }

        return map.get(nodeList.first())
    }
}