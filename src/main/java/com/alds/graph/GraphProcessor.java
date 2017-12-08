package com.alds.graph;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

public class GraphProcessor {

    /**
     * <p>Task 4.7. Time for implementation and testing :<i>70 min</i></p>
     * <p>Builds a correct order of projects' processing so that all project's dependencies will be processed
     * before the project is</p>
     * <p>Performance equals <i>O(N*K)</i>,
     * where <i>N</i> is the size of projects and <i>K</i> is the size of path of dependencies in a project.
     * Memory usage : size of graph <i>O((N+L))</i> (<i>N</i> is the size of projects in a graph,
     *  <i>L</i> is the size of dependencies in a graph)(the sizes of stack and list of correct
     *  are not taken into account, because when an element is added to a list, the same element
     *  is removed from graph and stack)</p>
     *
     * @param projects       list of projects
     * @param dependencyList list of projects' dependencies
     * @param <T>            type of project
     * @return - list which represents the correct order of processing
     * @throws IllegalStateException when dependencies build a cycle
     */
    public static <T> List<T> constructSequenceOfProcessing(List<T> projects,
                                                            List<DependencyPair<T>> dependencyList) {
        checkNotNull(projects);
        checkNotNull(dependencyList);
        if (projects.isEmpty())
            return new LinkedList<>();

        Map<T, List<T>> graph = initializeGraph(projects, dependencyList);

        Set<T> visited = new HashSet<>();
        Deque<T> stack = new LinkedList<>();
        List<T> correctOrder = new LinkedList<>();
        while (!graph.isEmpty()) {
            visited.clear();
            stack.push(graph.keySet().iterator().next());
            while (!stack.isEmpty()) {

                T node = stack.peek();
                if (visited.contains(node))
                    throw new IllegalStateException(
                            "Graph contains cycle. No correct sequence of processing can be built");
                List<T> nodeDependencies = graph.get(node);
                if (nodeDependencies == null || nodeDependencies.isEmpty()) {
                    if (nodeDependencies != null) {
                        visited.add(node);
                        correctOrder.add(node);
                    }
                    stack.pop();
                    graph.remove(node);
                } else {
                    stack.push(nodeDependencies.remove(0));
                }
            }
        }

        return correctOrder;
    }

    private static <T> Map<T, List<T>> initializeGraph(List<T> projects, List<DependencyPair<T>> dependencyList) {
        Map<T, List<T>> graph = new HashMap<>();
        for (T project : projects) {
            graph.put(project, new LinkedList<>());
        }
        for (DependencyPair<T> dependencyPair : dependencyList) {
            graph.get(dependencyPair.getDependant()).add(dependencyPair.getIndependant());
        }
        return graph;
    }
}
