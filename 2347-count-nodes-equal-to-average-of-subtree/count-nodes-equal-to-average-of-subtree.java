/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingNodes = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodes = 0;
        postOrder(root);
        return matchingNodes;
    }

    // Returns an int array: [subtreeSum, subtreeNodeCount]
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = left[0] + right[0] + node.val;
        int currentCount = left[1] + right[1] + 1;

        if (node.val == currentSum / currentCount) {
            matchingNodes++;
        }

        return new int[]{currentSum, currentCount};
    }
}