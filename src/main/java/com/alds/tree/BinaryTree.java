package com.alds.tree;

import java.util.*;

/**
 * Custom implementation of binary tree with basic methods set
 *
 * @param <T> type of the data which is stored in the node
 */
public class BinaryTree<T> {
    private BinaryTreeNode<T> root;

    public BinaryTree() {
    }

    public BinaryTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    /**
     * <p>Task 4.3. Time for implementation and testing :<i>40 min</i></p>
     * <p>Constructs a linked lists of all the nodes at each depth</p>
     * <p>Performance equals <i>O(N)</i> (tree traversal + stack traversal),
     * where <i>N</i> is the size of a tree.
     * Memory usage is <i>O(N)</i> (size of queue + size of stack)</p>
     *
     * @return map which contains lists of nodes grouped by depth
     */
    public Map<Integer, List<T>> constructDepthLists() {
        if (isEmpty())
            return new HashMap<>();
        Map<Integer, List<T>> map;
        Queue<NodeWrapper<T>> queue = new LinkedList<>();
        Deque<NodeWrapper<T>> stack = new LinkedList<>();
        queue.add(new NodeWrapper<>(root, 0));

        fillStack(queue, stack);

        map = new HashMap<>(stack.peek().level);
        fillMap(map, stack);
        return map;
    }

    private void fillMap(Map<Integer, List<T>> map, Deque<NodeWrapper<T>> stack) {
        while (!stack.isEmpty()) {
            NodeWrapper<T> wrapper = stack.pop();
            if (!map.containsKey(wrapper.level))
                map.put(wrapper.level, new LinkedList<>());
            map.get(wrapper.level).add(wrapper.node.getItem());
        }
    }

    private void fillStack(Queue<NodeWrapper<T>> queue, Deque<NodeWrapper<T>> stack) {
        while (!queue.isEmpty()) {
            NodeWrapper<T> wrapper = queue.remove();
            stack.push(wrapper);
            int nextLevel = wrapper.level + 1;
            if (wrapper.node.getRightChild() != null)
                queue.add(new NodeWrapper<>(wrapper.node.getRightChild(), nextLevel));
            if (wrapper.node.getLeftChild() != null)
                queue.add(new NodeWrapper<>(wrapper.node.getLeftChild(), nextLevel));
        }
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private static class NodeWrapper<T> {
        BinaryTreeNode<T> node;
        int level;

        public NodeWrapper(BinaryTreeNode<T> node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
