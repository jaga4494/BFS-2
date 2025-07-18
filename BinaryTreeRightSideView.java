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
    List<Integer> result = new ArrayList<>();
    
    // if level == result.size() - add new element to list. otherwise replace element in list for that index since right child is visited later and that will be visible from right side.
    public List<Integer> rightSideViewDFSLeftToRight(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int level) {
        if(root == null) {
            return;
        }

        if(level == result.size()) {
            result.add(root.val);
        } else {
            result.set(level, root.val);
        }

        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    // No need to replace the existing value as we are traversing the right child first. 
    // Rightmost node in every level is processed first. 
    // Just keep adding to the result.
    public List<Integer> rightSideViewDFSRightToLeft(TreeNode root) {
        dfs1(root, 0);
        return result;
    }

    private void dfs1(TreeNode root, int level) {
        if(root == null) {
            return;
        }

        if(level == result.size()) {
            result.add(root.val);
        }

        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
        
    }

    // BFS solution.
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList();

        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                // Add last element in each level to result. 
                // If we are adding right child first to queue, we can check i == 0
                if (i == size - 1) {
                    result.add(cur.val);
                }

                if (cur.left != null) {
                    q.add(cur.left);
                }

                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
        }
        return result;
    }
}