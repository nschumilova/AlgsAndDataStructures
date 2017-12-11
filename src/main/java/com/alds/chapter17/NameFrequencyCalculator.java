package com.alds.chapter17;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

public class NameFrequencyCalculator {

    /**
     * <p>Task 17.7. Time for implementation and testing :<i>60 min</i></p>
     * Calculates frequencies for the most popular names taking into account  names' synonyms
     * <p>Performance: graph initialization <i>O(N1+N2)</i>  (N1 - size of initialNameFrequencies,
     * N2 - size of synonyms), graph traversal: <i>O(N)</i> ( N - size of graph (number of all unique names))<br>
     * Memory :  <i>O(N+K)</i> (N - size of graph (number of all unique names),
     * K - size of newly calculated map of names' frequencies, K = number of not connected subgraphs)</p>
     *
     * @param initialNameFrequencies initial map, which contains names and their frequencies
     *                               without any information about names' synonyms
     * @param synonyms               map which contains pairs name-> names'synonym
     * @return - newly calculated map with names and their frequencies after analysis of names' synonyms
     */
    public static Map<String, Integer> calculateNameFrequencies(Map<String, Integer> initialNameFrequencies, Map<String, String> synonyms) {
        checkNotNull(initialNameFrequencies);
        checkNotNull(synonyms);

        Map<String, Set<String>> graph = buildGraph(initialNameFrequencies.keySet(), synonyms);
        return calculateNewFrequencies(initialNameFrequencies, graph);
    }


    private static Map<String, Set<String>> buildGraph(Set<String> initialNames, Map<String, String> synonyms) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String name : initialNames) {
            graph.put(name, new HashSet<>());
        }
        for (String name : synonyms.keySet()) {
            String synonym = synonyms.get(name);
            if (!name.equals(synonym)) {
                Set<String> synonymsChildren = graph.remove(synonym);
                if (!graph.containsKey(name))
                    graph.put(name, new HashSet<>());
                graph.get(name).add(synonym);
                if (synonymsChildren != null)
                    graph.get(name).addAll(synonymsChildren);
            }

        }
        return graph;
    }

    private static Map<String, Integer> calculateNewFrequencies(Map<String, Integer> initialNameFrequencies, Map<String, Set<String>> graph) {
        Map<String, Integer> nameFreqMap = new HashMap<>();
        for (String name : graph.keySet()) {
            int result = initialNameFrequencies.getOrDefault(name, 0);
            for (String child : graph.get(name))
                result += initialNameFrequencies.getOrDefault(child, 0);
            if (result > 0)
                nameFreqMap.put(name, result);
        }
        return nameFreqMap;
    }

}
