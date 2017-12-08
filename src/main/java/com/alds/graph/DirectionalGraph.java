package com.alds.graph;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

/**
 * Class which represemts a bidirectional graph
 *
 * @param <T> type of data which resides in the graph's nodes
 */
public class DirectionalGraph<T> {
    private List<Node<T>> nodes = new ArrayList<>();


    public void addNode(T item) {
        addAndGetNode(nodes, item);
    }

    public void addConnection(T from, T to) {
        addConnection(from, to, false);
    }

    public void addConnection(T from, T to, boolean bidirectional) {
        checkNotNull(from);
        checkNotNull(to);
        Node nodeFrom = addAndGetNode(nodes, from);
        Node nodeTo = addAndGetNode(nodes, to);
        if (!nodeFrom.contains(to))
            nodeFrom.children.add(nodeTo);

        if (bidirectional && !nodeTo.contains(from))
            nodeTo.children.add(nodeFrom);
    }

    /**
     * <p>Task 4.1. Time for implementation and testing :<i>20 min</i></p>
     * <p>Checks whether a graph contains a route between two nodes using BFS-algorithm</p>
     * <p>Performance depends on the length of the path <i>O(K)</i>,
     * where <i>K</i> can in worst case be equal to the number of all elements in a graph.
     * Memory usage depends on the length of the path as well <i>O(2K)</i> (size of a queue +
     * size of hashset)</p>
     *
     * @param from - start of the route
     * @param to   - end of the route
     * @return <code>true</code> if the route exists and <code>false</code> otherwise
     */
    public boolean routeExists(T from, T to) {
        checkNotNull(from);
        checkNotNull(to);


        Optional<Node<T>> fromNode = getNode(nodes, from);
        if (fromNode.isPresent()) {
            if (from.equals(to)) {
                if (fromNode.get().contains(from))
                    return true;
            }
            Set<T> visited = new HashSet<>();
            Queue<Node<T>> queue = new LinkedList<>();
            queue.add(fromNode.get());
            visited.add(fromNode.get().item);
            while (!queue.isEmpty()) {
                Node<T> node = queue.remove();
                if (node.item.equals(to))
                    return true;
                else {
                    for (Node<T> child : node.children) {
                        if (!visited.contains(child.item)) {
                            visited.add(child.item);
                            queue.add(child);
                        }
                    }
                }
            }
            return false;
        } else return false;
    }

    private Node addAndGetNode(List<Node<T>> listOfNodes, T item) {
        Optional<Node<T>> found = getNode(listOfNodes, item);
        if (found.isPresent())
            return found.get();
        else {
            Node node = new Node<>(item);
            listOfNodes.add(node);
            return node;
        }
    }

    private Optional<Node<T>> getNode(List<Node<T>> listOfNodes, T item) {
        for (Node node : listOfNodes) {
            if (node.item.equals(item))
                return Optional.of(node);
        }
        return Optional.empty();
    }

    private static class Node<T> {
        T item;
        List<Node<T>> children = new ArrayList<>();

        Node(T item) {
            this.item = item;
        }

        boolean contains(T item) {
            for (Node node : children) {
                if (node.item.equals(item))
                    return true;
            }
            return false;
        }
    }
}
