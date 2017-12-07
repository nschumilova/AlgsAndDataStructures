package com.alds.tree;

import java.util.LinkedList;
import java.util.Queue;

import static com.alds.util.Helper.checkNotNull;

public class BinaryTreeProcessor {

    /**
     * <p>Task 4.10. Time for implementation and testing :<i>50 min</i></p>
     * Function that checks whether a tree can contain another tree as subtree in one of own nodes.
     * <p>Performance and memory: in worst case <i>O((N1*(2^(N2+1)))</i> (N1 - size of a tree, N2 - size of subtree).
     *
     * @param tree    - tree to check
     * @param subtree - possible candidate for subtree
     * @param <T>     - type of trees' elements
     * @return <code>true</code> if subtree exists and <code>false</code> otherwise
     */
    public static <T> boolean hasSubtree(BinaryTree<T> tree, BinaryTree<T> subtree) {

        checkNotNull(tree);
        checkNotNull(subtree);

        if (tree.isEmpty() || subtree.isEmpty())
            return false;
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(tree.getRoot());
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.remove();
            if (hasSubtree(node, subtree.getRoot()))
                return true;
            else {
                if (node.hasLeftChild())
                    queue.add(node.getLeftChild());
                if (node.hasRightChild())
                    queue.add(node.getRightChild());
            }
        }
        return false;
    }

    /**
     *
     * Performance and Memory: 1 + 2 + 4 + 8 +...+ 2^N = 2^(N+1) - 1 in worst case,
     * where N is the size of subtree
     *
     */
    private static <T> boolean hasSubtree(BinaryTreeNode<T> root, BinaryTreeNode<T> subtree) {
        if (root == null && subtree == null)
            return true;
        else if (root != null && subtree != null) {
            return root.getItem().equals(subtree.getItem())
                    && hasSubtree(root.getLeftChild(), subtree.getLeftChild())
                    && hasSubtree(root.getRightChild(), subtree.getRightChild());
        } else return false;
    }


}
