package yichen

data class TreeNode(
    var left: TreeNode?,
    var right: TreeNode?,
    val `val`: Int
)

// 不满二叉树，找出递增序列最长的层高
fun main() {
    var root = TreeNode(TreeNode(null, null, 3), TreeNode(TreeNode(null, null, 6), null, 5), 4)
    println(longestConsecutive(root))
}

// https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence/description/
// 298. 二叉树最长连续序列
//
// 给你一棵指定的二叉树的根节点 root ，请你计算其中 最长连续序列路径 的长度。
//
//最长连续序列路径 是依次递增 1 的路径。该路径，可以是从某个初始节点到树中任意节点，通过「父 - 子」关系连接而产生的任意路径。且必须从父节点到子节点，反过来是不可以的。
//
//
//示例 1：
//
//
//输入：root = [1,null,3,2,4,null,null,null,5]
//输出：3
//解释：当中，最长连续序列是 3-4-5 ，所以返回结果为 3 。
//示例 2：
//
//
//输入：root = [2,null,3,2,null,1]
//输出：2
//解释：当中，最长连续序列是 2-3 。注意，不是 3-2-1，所以返回 2 。
//
//
//提示：
//
//树中节点的数目在范围 [1, 3 * 104] 内
//-3 * 104 <= Node.val <= 3 * 104
fun longestConsecutive(root: TreeNode?): Int {
    root ?: return 0

    var maxHigh = 1

    fun calcHigh(node: TreeNode, high: Int) {
        maxHigh = maxOf(high, maxHigh)

        if (node.left != null && node.left!!.`val` == node.`val` + 1) {
            calcHigh(node.left!!, high + 1)
        } else if (node.left != null) {
            calcHigh(node.left!!, 1)
        }

        if (node.right != null && node.right!!.`val` == node.`val` + 1) {
            calcHigh(node.right!!, high + 1)
        } else if (node.right != null) {
            calcHigh(node.right!!, 1)
        }
    }

    calcHigh(root, 1)

    return maxHigh
}