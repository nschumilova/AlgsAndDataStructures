package com.alds.arrays.strings;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringModificationsZeroOneEditTest {

    @Test(expected = IllegalArgumentException.class)
    public void testZeroOneEditWhenInputNull(){
        StringModifications.isOneOrZeroEditDifference("vv", null);
    }

    @Test
    public void testZeroEditDifference(){
        assertTrue(StringModifications.isOneOrZeroEditDifference(new String("pale"), new String("pale")));
    }

    @Test
    public void testOneRemoveDifference(){
        assertTrue(StringModifications.isOneOrZeroEditDifference("pale", "ple"));
    }

    @Test
    public void testOneInsertDifference(){
        assertTrue(StringModifications.isOneOrZeroEditDifference("pale", "pales"));
    }

    @Test
    public void testOneReplaceDifference(){
        assertTrue(StringModifications.isOneOrZeroEditDifference("pale", "pile"));
    }

    @Test
    public void testTwoInsertsIsNotZeroOneDifference(){
        assertFalse(StringModifications.isOneOrZeroEditDifference("pale", "prales"));
    }

    @Test
    public void testTwoReplacesIsNotZeroOneDifference(){
        assertFalse(StringModifications.isOneOrZeroEditDifference("pale", "same"));
    }

    @Test
    public void testInsertReplaceIsNotZeroOneDifference(){
        assertFalse(StringModifications.isOneOrZeroEditDifference("pale", "sales"));
    }
}
