package DataStructure;

import org.w3c.dom.ls.LSInput;

//  Definition for a binary tree node.
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**
     * 前序遍历
     * @param t
     */
    public static void preorder(TreeNode t) {
        if (t == null) {
            System.out.print("null,");
            return;
        }
        System.out.print(t.val + ",");
        preorder(t.left);
        preorder(t.right);
    }
}

