package com.alds.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class PermutationProcessor {

    public static Set<Integer> getAllDigitPermutations(int value) {
        String string = String.valueOf(value);
        List<StringBuilder> stringPermutations = getPermutationsRecursively(string, 0);
        return stringPermutations.stream().map(sb -> Integer.parseInt(sb.toString())).collect(toSet());
    }

    /**
     * Time for implementation and testing: <i>120 min</i><br>
     * Function which traverses all possible permutations of 9 digits and finds pairs in these permutations,
     * which represent a dividend and divisor which represent division without remainder.
     * * <p>Performance: <br>
     * - building all possible permutations <i>O(9!)</i>
     * - finding pairs in double loop  <i>O(9 * 9!)</i>,
     * where i is the length of i-th string</p>
     * @return
     */
    public static List<DivisionContainer> getDivisionPairsOf9DigitsPermutation() {
        List<DivisionContainer> containers = new LinkedList<>();
        Set<Integer> permutations = getAllDigitPermutations(123456789);
        for (Integer value : permutations) {
            for (int quotient = 1; quotient < 10; quotient++) {
                if (isEven(value) && !isEven(quotient))
                    continue;
                int divisor = value / quotient;
                int remainder = value % quotient;
                if (remainder==0 && permutations.contains(divisor))
                    containers.add(new DivisionContainer(value, divisor));
            }
        }
        return containers;
    }

    private static boolean isEven(int number) {
        return (number & 1) == 0;
    }

    private static List<StringBuilder> getPermutationsRecursively(String initialString, int index) {
        List<StringBuilder> list = new LinkedList<>();
        char charAtIndex = initialString.charAt(index);
        if (index == initialString.length() - 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(charAtIndex);
            list.add(sb);

        } else {
            List<StringBuilder> substrPermutation = getPermutationsRecursively(initialString, index + 1);
            for (StringBuilder sb : substrPermutation) {
                for (int i = 0; i <= sb.length(); i++)
                    list.add(appendCharBefore(charAtIndex, i, sb));
            }
        }
        return list;
    }

    private static StringBuilder appendCharBefore(char val, int index, StringBuilder sb) {
        StringBuilder newSb = new StringBuilder();
        for (int i = 0; i < index; i++)
            newSb.append(sb.charAt(i));
        newSb.append(val);
        for (int i = index; i < sb.length(); i++)
            newSb.append(sb.charAt(i));
        return newSb;
    }
}
