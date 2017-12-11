package com.alds.chapter17;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NameFrequencyCalculatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateNameFrequenciesWhenUnputNull() {
        NameFrequencyCalculator.calculateNameFrequencies(null, null);

    }

    @Test
    public void testCalculateNameFrequenciesWhenInitialMapEmpty() {
        Map<String, String> synonyms = new HashMap<>();
        synonyms.put("name1", "name2");
        synonyms.put("name3", "name4");
        assertTrue(NameFrequencyCalculator
                .calculateNameFrequencies(new HashMap<>(), synonyms)
                .isEmpty());
    }

    @Test
    public void testCalculateNameFrequenciesWhenNoSynonyms() {
        Map<String, Integer> initialMap = new HashMap<>();
        initialMap.put("name1", 10);
        initialMap.put("name3", 11);
        assertEquals(initialMap, NameFrequencyCalculator
                .calculateNameFrequencies(initialMap, new HashMap<>()));
    }

    @Test
    public void testCalculateNameFrequenciesWithSynonymsNotChanged() {
        Map<String, Integer> initialMap = new HashMap<>();
        initialMap.put("name1", 10);
        initialMap.put("name3", 11);
        Map<String, String> synonyms = new HashMap<>();
        synonyms.put("name1", "name2");
        Map<String, Integer> result = NameFrequencyCalculator
                .calculateNameFrequencies(initialMap, synonyms);
        assertEquals(initialMap.size(), result.size());
        assertEquals(initialMap.get("name3"), result.get("name3"));
        if (result.get("name2") == null)
            assertTrue((initialMap.get("name1") == result.get("name1")));
        else
            assertTrue((initialMap.get("name1") == result.get("name2") && result.get("name1") == null));

    }

    @Test
    public void testCalculateNameFrequenciesWithSynonymsTwoBranchesMerged() {
        Map<String, Integer> initialMap = new HashMap<>();
        initialMap.put("name1", 10);
        initialMap.put("name2", 11);
        Map<String, String> synonyms = new HashMap<>();
        synonyms.put("name1", "name2");
        Map<String, Integer> result = NameFrequencyCalculator
                .calculateNameFrequencies(initialMap, synonyms);
        Map.Entry<String, Integer> element = result.entrySet().iterator().next();
        assertEquals(1, result.size());
        assertEquals(21, element.getValue().intValue());
        assertTrue("name1".equals(element.getKey()) ||
                "name2".equals(element.getKey()) ||
                "name3".equals(element.getKey()));

    }
}
