package com.alds.arrays.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.alds.util.Helper.checkNotEmpty;
import static com.alds.util.Helper.checkNotNull;
import static com.alds.util.Helper.sameObjects;

/**
 * Class that implements permutation operations on different strings
 */
public class Permutations {

    /**
     * <p>Task 1.2. Time for implementation and testing :<i>40 min</i></p>
     * <p>Checks whether one string is a permutation of the other</p>
     * <p>Performance depends on the sorting algorithm and equals <i>O(N log(N))</i> (quicksort is used),
     * where <i>N</i> is the size of a string.
     * Memory usage is <i>O(N)</i> (size of first string's copy + size of second string's copy)</p>
     *
     * @param str1 - initial string
     * @param str2 - possible candidate for permutation
     * @return - <code>true</code> if two strings represent a permutation of each other
     * and <code>false</code> otherwise
     */
    public static boolean isPermutation(String str1, String str2) {
        checkNotNull(str1);
        checkNotNull(str2);
        checkNotEmpty(str1);
        checkNotEmpty(str2);

        if (differentLengths(str1, str2))
            return false;
        if(sameObjects(str1, str2))
            return true;

        char[] str1Content = str1.toCharArray();
        char[] str2Content = str2.toCharArray();
        Arrays.sort(str1Content);
        Arrays.sort(str2Content);

        for (int i = 0; i < str1Content.length; i++) {
            if (str1Content[i] != str2Content[i])
                return false;
        }
        return true;
    }

    /**
     * <p>Task 1.4. Time for implementation and testing :<i>75 min</i></p>
     * <p>Checks whether a string is a permutation of palindrome</p>
     * <p>Performance depends on the HashMap traversing and equals <i>O(N)</i>,
     * where <i>N</i> is the size of a string.
     * Memory usage is <i>O(N)</i> (size of string's copy + size of Map (= size of string))</p>
     * @param string
     * @return
     */
    public static boolean isPalindromePermutation(String string) {
        checkNotNull(string);
        checkNotEmpty(string);

        char[] strContent = string.trim().toCharArray();

        Map<Character, Integer> mapOfCharOccurrences =
                convertToMapOfCharOccurrences(strContent);
        //if phrase consists of no alphabetic or digit chars then it is not palindrome
        if(mapOfCharOccurrences.size()==0)
            return false;
        //if there are more than one odd occurrences of some chars then it is not palindrome
        if(countOddOccurrences(mapOfCharOccurrences)>1)
            return false;
        return true;
    }





    private static boolean differentLengths(String str1, String str2) {
        return str1.length() != str2.length();
    }

    private static Map<Character, Integer> convertToMapOfCharOccurrences(char[] stringContent) {
        Map<Character, Integer> map = new HashMap<>(stringContent.length);
        for (char ch : stringContent) {
            char lowerCase = Character.toLowerCase(ch);
            if (isDigitOrAlphabetic(lowerCase)) {
                map.merge(lowerCase, 1, Integer::sum);
            }
        }
        return map;
    }

    private static boolean isDigitOrAlphabetic(char value) {
        return Character.isDigit(value) || Character.isAlphabetic(value);
    }

    private static int countOddOccurrences(Map<Character, Integer> mapOfCharOccurrences){
       return mapOfCharOccurrences
               .values()
               .stream()
               .mapToInt(Integer::intValue)
               .reduce(0,(prev, curr) -> ((curr%2==0)? prev : (prev+1)));
    }

}
