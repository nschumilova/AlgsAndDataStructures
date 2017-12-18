package com.alds.chapter16;

/**
 * Class which represents a prefix tree, which can be used to store words (a word is represented by a path
 * from the root node to a leaf node)
 */
public class Trie {
    private static final int ALPHABET_SIZE = 26;
    private static final char START_OF_ALPHABET = 'a';
    protected TrieNode root = new TrieNode(false);

    protected boolean empty = true;

    public static class TrieNode {
        private boolean wordEnd;
        private TrieNode[] children = new TrieNode[ALPHABET_SIZE];


        TrieNode(boolean isWordEnd) {
            this.wordEnd = isWordEnd;
            children = new TrieNode[ALPHABET_SIZE];
        }

        public boolean isWordEnd() {
            return wordEnd;
        }

        public boolean containsChild(int index) {
            return children[index] != null;
        }

        public TrieNode getChildAt(int index) {
            return children[index];
        }

        public void setChildAt(int index, boolean isChildWordEnd) {
            children[index] = new TrieNode(isChildWordEnd);
        }
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = charToInt(word, i);
            if (!isValidCharacter(index))
                throw new IllegalArgumentException(word.charAt(i) + " is not alphabetic ");
            if (!node.containsChild(index))
                node.setChildAt(index, i == word.length() - 1);
            else if (i == word.length() - 1)
                node.wordEnd = true;
            node = node.getChildAt(index);
        }
        empty=false;
    }

    protected int charToInt(String word, int index) {
        return word.charAt(index) - START_OF_ALPHABET;
    }
    protected char intToChar(int index){
        return (char) (index+START_OF_ALPHABET);
    }

    public CharSequenceType getCharSequenceType(String charSequence) {
        TrieNode node = root;
        for (int i = 0; i < charSequence.length(); i++) {
            int index = charToInt(charSequence, i);
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

    public boolean isEmpty() {
        return empty;
    }

    private boolean isValidCharacter(int index) {
        return index >= 0 && index < ALPHABET_SIZE;
    }

    public enum CharSequenceType {
        NOT_WORD, WORD_PREFIX, WORD
    }

}
