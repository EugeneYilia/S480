import java.util.ArrayList;
import java.util.List;

public class SearchTreePostOrder {


    // left right root
    static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;
    }

//    public static void main(String[] args) {
//        RootNode root = new RootNode();
//        root.value = 7;
//
//        root.left = new RootNode();
//        root.left.value = 4;
//
//        root.right = new RootNode();
//        root.right.value = 8;
//
//        root.left.right = new RootNode();
//        root.left.right.value = 9;
//
//        root.right.left = new RootNode();
//        root.right.left.value = 1;
//
//        searchNode(root);
//    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        searchNode(root, result);

        return result;
    }

    public void searchNode(TreeNode root, List<Integer> result){
        if (root.left != null) {
            searchNode(root.left, result);
        }

        if (root.right != null) {
            searchNode(root.right, result);
        }

        result.add(root.val);
    }

}
