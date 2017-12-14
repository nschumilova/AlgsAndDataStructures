package com.alds.chapter16;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

public class PhonePrompter {
    private static final Map<Character, char[]> NUMBER_MAP;

    static {
        NUMBER_MAP = new HashMap<Character, char[]>() {
            {
                put('0', new char[]{Character.MIN_VALUE});
                put('1', new char[]{Character.MIN_VALUE});
                put('2', new char[]{'a', 'b', 'c'});
                put('3', new char[]{'d', 'e', 'f'});
                put('4', new char[]{'g', 'h', 'i'});
                put('5', new char[]{'j', 'k', 'l'});
                put('6', new char[]{'m', 'n', 'o'});
                put('7', new char[]{'p', 'q', 'r', 's'});
                put('8', new char[]{'t', 'u', 'v'});
                put('9', new char[]{'w', 'x', 'y', 'z'});
            }
        };
    }

    /**
     * <p>Task 16.20. Time for implementation and testing :<i>140 min</i></p>
     * Implements algorithm of words prompting (T9) after user prints a sequence of numbers on a mobile phone.<br>
     * Performance: depends on the entered sequence of numbers and can in worst be equal to <i>O(4^N)</i>
     * (all possible combinations of letters build a word) and in best case - <i>O(N)</i> (there is only one
     * combination of letters in the vocabulary). Where N - is the size of entered numbers <br>
     * Memory: <i>O(K + N)</i>. Where K is the size of found words and N - the depth of recursion
     * (size of entered numbers)
     *
     * @param numberSequence printed sequence of numbers
     * @param vocabulary     prefix tree which contains possible words
     * @return list of found words
     */
    public static Set<String> getWordPromptsByNumberSequence(String numberSequence, Trie vocabulary) {
        checkNotNull(numberSequence);
        checkNotNull(vocabulary);
        validateNumbers(numberSequence);

        Set<String> prompts = new HashSet<>();
        evaluateWordPromptsRecursively(numberSequence, vocabulary, 0, prompts, new StringBuilder());
        return prompts;
    }

    private static void evaluateWordPromptsRecursively(String numberSequence,
                                                       Trie vocabulary,
                                                       int numberIndex,
                                                       Set<String> prompts,
                                                       StringBuilder word) {

        String strWord = word.toString();
        if (numberIndex == numberSequence.length()) {
            if (Trie.CharSequenceType.WORD == vocabulary.getCharSequenceType(strWord))
                prompts.add(strWord);
            return;
        }
        char numberAtIndex = numberSequence.charAt(numberIndex);
        char[] letters = NUMBER_MAP.get(numberAtIndex);
        for (char letter : letters) {
            StringBuilder sb = new StringBuilder(numberSequence.length());
            sb.append(strWord).append(letter);
            if (numberIndex == numberSequence.length() - 1 ||
                    (numberIndex < (numberSequence.length() - 1) &&
                            Trie.CharSequenceType.NOT_WORD != vocabulary.getCharSequenceType(sb.toString())))
                evaluateWordPromptsRecursively(numberSequence, vocabulary, numberIndex + 1, prompts, sb);
        }
    }

    private static void validateNumbers(String numberSequence) {
        Integer.parseInt(numberSequence);
    }
}
