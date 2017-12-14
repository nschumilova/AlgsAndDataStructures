package com.alds.chapter16;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

import static junit.framework.TestCase.assertTrue;

public class PhonePrompterTest {

    private static  Trie vocabulary;
    private static  Map<String, Set<String>> words;

    @BeforeClass
    public static void initVocabulary() {
        words = new HashMap<>();
        words.put("543", new HashSet<>(Arrays.asList("lie", "kid", "lid")));
        words.put("6269", new HashSet<>(Arrays.asList("many")));
        words.put("626", new HashSet<>(Arrays.asList("man")));

        vocabulary = new Trie();
        words.values()
                .stream()
                .flatMap(set -> set.stream())
                .forEach(vocabulary::addWord);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetWordPromptsWhenNumbersIsNull() {
        PhonePrompter.getWordPromptsByNumberSequence(null, vocabulary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWordPromptsWhenVocabularyIsNull() {
        PhonePrompter.getWordPromptsByNumberSequence("1234", null);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetWordPromptsWhenNumbersAreNotParsable() {
        PhonePrompter.getWordPromptsByNumberSequence("12x34", vocabulary);
    }

    @Test
    public void testGetWordPromptsWhenVocabularyEmpty() {
        assertTrue(PhonePrompter.getWordPromptsByNumberSequence("1234", new Trie()).isEmpty());
    }

    @Test(expected = NumberFormatException.class)
    public void testGetWordPromptsWhenNumbersEmpty() {
        PhonePrompter.getWordPromptsByNumberSequence("", vocabulary);
    }

    @Test
    public void testGetWordPromptsWhenNumbersContainOne() {
        assertTrue(PhonePrompter.getWordPromptsByNumberSequence("6219", vocabulary).isEmpty());
    }
    @Test
    public void testGetWordPromptsWhenNumbersContainZero() {
        assertTrue(PhonePrompter.getWordPromptsByNumberSequence("6260", vocabulary).isEmpty());
    }

    @Test
    public void testGetWordPromptsWhenOneWordFound() {
        assertEquals(words.get("6269"),PhonePrompter.getWordPromptsByNumberSequence("6269", vocabulary));
    }

    @Test
    public void testGetWordPromptsWhenThreeWordsFound() {
        assertEquals(words.get("543"),PhonePrompter.getWordPromptsByNumberSequence("543", vocabulary));
    }

    @Test
    public void testGetWordPromptsWhenNoneWordFound() {
        assertTrue(PhonePrompter.getWordPromptsByNumberSequence("626243", vocabulary).isEmpty());
    }


}
