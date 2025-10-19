import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchTreePostOrderByStack {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        TreeNode[] nodeStack = new TreeNode[101];
        //
        // 4 5 1 6 7 3 2
        //         2
        //     1        3
        //  4   5     6   7
        // 1 2 3 4   5 6 7 8
        //,,,,,,,
        // 2 3 1 7 6 5 4
        int stackIndex = 0;

        // index指向的下一位
        nodeStack[stackIndex++] = root;

        while (stackIndex > 0) {
            TreeNode cur = nodeStack[--stackIndex];
            result.addFirst(cur.val);

            if (cur.left != null) {
                nodeStack[stackIndex++] = cur.left;
            }

            if (cur.right != null) {
                nodeStack[stackIndex++] = cur.right;
            }
        }

        return result;
    }
}
