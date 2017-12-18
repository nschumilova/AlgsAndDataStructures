package com.alds.test;

import com.alds.chapter16.Trie;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class SuffixTrie extends Trie {

    private List<Integer> longestWord = new LinkedList<>();


    SuffixTrie getIntersectionSubtree(String word) {
        Trie.TrieNode node = this.root;
        SuffixTrie subtree = new SuffixTrie();
        Deque<Integer> intersectionStack = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            int index = charToInt(word, i);
            if (node.containsChild(index)) {
                intersectionStack.push(index);
                node = node.getChildAt(index);
            } else {
                splitIntersectionAndAddBranches(subtree, intersectionStack);
                node = root;
                if (root.containsChild(index))
                    i--;
            }
        }
        if (!intersectionStack.isEmpty())
            splitIntersectionAndAddBranches(subtree, intersectionStack);
        return subtree;
    }

    private static void splitIntersectionAndAddBranches(SuffixTrie intersectionSubtree, Deque<Integer> intersectionStack) {
        LinkedList<Integer> word = new LinkedList<>();
        while (!intersectionStack.isEmpty()) {
            word.addFirst(intersectionStack.pop());
            intersectionSubtree.addWord(word);
        }
    }

    private void addWord(List<Integer> letters) {
        Trie.TrieNode node = this.root;
        for (Integer letter : letters) {
            if (!node.containsChild(letter))
                node.setChildAt(letter, false);
            node = node.getChildAt(letter);
        }
        empty = false;
        if (longestWord.size() < letters.size())
            longestWord = letters;
    }

    String getLongestWord() {
        StringBuilder sb = new StringBuilder();
        for (Integer index : longestWord) {
            sb.append(intToChar(index));
        }
        return sb.toString();
    }

}
