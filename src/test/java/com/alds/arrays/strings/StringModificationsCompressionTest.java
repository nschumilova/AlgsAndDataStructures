package com.alds.arrays.strings;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringModificationsCompressionTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCompressZeroInput(){
        StringModifications.compressString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompressNonUpperOrLowerCaseLetters(){
        StringModifications.compressString("abD?fff");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompressEmptyString(){
        StringModifications.compressString("");
    }

    @Test
    public void testCompressNewStringShorter(){
        String initial = "aabbbccddddd";
        String result = "a2b3c2d5";
        assertEquals(result, StringModifications.compressString(initial));
    }

    @Test
    public void testCompressNewStringLonger(){
        String initial = "abcdef";
        String invalidResult = "a1b1c1d1e1f1";
        validateCompression(initial, invalidResult);
    }

    @Test
    public void testCompressNewStringSameSize(){
        String initial = "aabb";
        String invalidResult = "a2b2";
        validateCompression(initial, invalidResult);
    }

    private void validateCompression(String initialAndExpectedResult, String invalidResult) {
        String result = StringModifications.compressString(initialAndExpectedResult);
        assertNotEquals(invalidResult, result);
        assertEquals(initialAndExpectedResult, result);
    }


}
