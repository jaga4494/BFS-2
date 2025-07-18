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
    TreeNode xParent, yParent;
    int xLevel = -1, yLevel = -1;
    // DFS solution.Traverse and find the parent and level of both x and y.
    // If their parents differ and levels same then cousins so return true 
    public boolean isCousinsDFS(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        dfs(root, null, 0, x, y);
        return xParent != yParent && xLevel == yLevel;
        
    }

    private void dfs(TreeNode root, TreeNode parent, int level, int x, int y) {
        if (root == null) {
            return;
        }

        // parent of both are calculated. No need to traverse further
        if (xParent != null && yParent != null) {
            return;
        }

        if (root.val == x) {
            xParent = parent;
            xLevel = level;
        }

        if (root.val == y) {
            yParent = parent;
            yLevel = level;
        }

        dfs(root.left, root, level + 1, x, y);
        dfs(root.right, root, level + 1, x, y);

    }

    // BFS solution. Level matters. same parent same level return false. if only one found in a level, return false. 
    // return true if both found in same level
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        boolean xFound = false;
        boolean yFound = false;
        Queue<TreeNode> q = new LinkedList();

        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();

            // check every level. level matters since cousins have to be in same level
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                
                if (cur.val == x) {
                    xFound = true;
                }
                if (cur.val == y) {
                    yFound = true;
                }

                if(cur.left != null && cur.right != null && ((cur.left.val == x && cur.right.val == y) || (cur.left.val == y && cur.right.val == x))) {
                    return false;
                }
                
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }

            // if both found in same level, return true
            if (xFound && yFound) {
                    return true;
            }
            // if only one found in same level, return false
            if (xFound || yFound) {
                return false;
            }

        }

        return false;


    }
}