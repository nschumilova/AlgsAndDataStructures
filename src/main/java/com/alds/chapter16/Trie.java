package com.alds.chapter16;

/**
 * Class which represents a prefix tree, which can be used to store words (a word is represented by a path
 * from the root node to a leaf node)
 */
public class Trie {
    private static final int ALPHABET_SIZE = 26;
    private static final char START_OF_ALPHABET = 'a';
    private TrieNode root = new TrieNode(false);

    private static class TrieNode {
        boolean wordEnd;
        private TrieNode[] children = new TrieNode[ALPHABET_SIZE];


        public TrieNode(boolean isWordEnd) {
            this.wordEnd = isWordEnd;
            children = new TrieNode[ALPHABET_SIZE];
        }

        boolean isWordEnd() {
            return wordEnd;
        }

        boolean containsChild(int index) {
            return children[index] != null;
        }

        TrieNode getChildAt(int index) {
            return children[index];
        }

        void setChildAt(int index, boolean isChildWordEnd) {
            children[index] = new TrieNode(isChildWordEnd);
        }
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - START_OF_ALPHABET;
            if (!isValidCharacter(index))
                throw new IllegalArgumentException(word.charAt(i) + " is not alphabetic ");
            if (!node.containsChild(index))
                node.setChildAt(index, i == word.length() - 1);
            else if (i == word.length() - 1)
                node.wordEnd = true;
            node = node.getChildAt(index);
        }
    }

    public CharSequenceType getCharSequenceType(String charSequence) {
        TrieNode node = root;
        for (int i = 0; i < charSequence.length(); i++) {
            int index = charSequence.charAt(i) - START_OF_ALPHABET;
            if (!isValidCharacter(index) || !node.containsChild(index))
                return CharSequenceType.NOT_WORD;
            else if (i == charSequence.length() - 1) {
                if (node.getChildAt(index).isWordEnd())
                    return CharSequenceType.WORD;
                else
                    return CharSequenceType.WORD_PREFIX;
            }
            node = node.getChildAt(index);
        }
        return CharSequenceType.NOT_WORD;
    }

    private boolean isValidCharacter(int index) {
        return index >= 0 && index < ALPHABET_SIZE;
    }

    public enum CharSequenceType {
        NOT_WORD, WORD_PREFIX, WORD
    }

}
