package yichen

fun main() {

}

class MinKInBinarySearchTree {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        if (root == null) return -1

        var resultCount = 0
        var result = -1

        fun searchMinKValue(root: TreeNode, k: Int) {
            if (root.left != null) {
                searchMinKValue(root.left!!, k)
            }

            resultCount++
            if (resultCount == k) {
                result = root.`val`
                return
            }

            if (root.right != null) {
                searchMinKValue(root.right!!, k)
            }
        }

        searchMinKValue(root, k)

        return result
    }
}