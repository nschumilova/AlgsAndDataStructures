package com.alds.test;

import java.util.Optional;
import java.util.Set;

import static com.alds.util.Helper.checkNotNull;

public class LongestSubstringFinder {

    /**
     * Time for implementation and testing: <i>120 min</i><br>
     * Finds longest substring of given strings' intersection
     * <p>Performance: <br>
     * - building the suffixes trie <i>O(K^2)</i>
     * where K is the length of the shortest word  plus <br>
     * - double loop (set traversal * string traversal) <i>O((N-1)^ i)</i>,
     * where i is the length of i-th string</p>
     *
     * @param strings given set of strings
     * @return longest substring if such was found
     */
    public static Optional<String> find(Set<String> strings) {
        checkNotNull(strings);
        if (strings.size() == 0)
            return Optional.empty();
        if (strings.size() == 1)
            return Optional.of(strings.iterator().next());
        String shortest = getShortest(strings).toLowerCase();

        SuffixTrie suffixesTrie = new SuffixTrie();
        for (int i = shortest.length() - 1; i >= 0; i--) {
            suffixesTrie.addWord(shortest.substring(i, shortest.length()));
        }

        for (String string : strings) {
            String lowerCaseStr = string.toLowerCase();
            if (lowerCaseStr.equals(shortest))
                continue;
            suffixesTrie = suffixesTrie.getIntersectionSubtree(lowerCaseStr);
            if (suffixesTrie.isEmpty())
                return Optional.empty();
        }
        return Optional.of(suffixesTrie.getLongestWord().toUpperCase());
    }

    private static String getShortest(Set<String> strings) {
        return strings.stream()
                .sorted((str1, str2) -> Integer.compare(str2.length(), str1.length()))
                .findFirst().get();
    }
}
