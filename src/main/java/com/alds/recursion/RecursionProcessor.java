package com.alds.recursion;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

/**
 * Class that contains implementations of recursion algorithms
 */
public class RecursionProcessor {


    /**
     * <p>Task 8.6. Time for implementation and testing :<i>20 min</i></p>
     * Method  that implements hanoy towers problem.
     * <p>Performance depends on the size of the source stack and equals <i>O(2^N)</i>
     * (2 because the third recursive call takes O(1)).
     * Memory usage is <i>O(log(N/3))</i>
     *
     * @param sourceStack - stack which contains disks sorted from bottom to top (desc)
     * @param destinationStack - stack where the disks must be put
     * @param auxStack - auxiliary stack
     * @param <T> - type of elements in stacks
     */
    public static <T> void moveHanoyTowers(Deque<T> sourceStack,
                                   Deque<T> destinationStack,
                                   Deque<T> auxStack){
        checkNotNull(sourceStack);
        checkNotNull(destinationStack);
        checkNotNull(auxStack);
        if(sourceStack.size()>0){
            moveHanoyRecursevly(sourceStack, destinationStack,auxStack, sourceStack.size());
        }
    }

    private static <T> void moveHanoyRecursevly(Deque<T> sourceStack,
                                         Deque<T> destinationStack,
                                         Deque<T> auxStack,
                                         int size) {
        if(size==1)
            destinationStack.push(sourceStack.pop());
        else{
            moveHanoyRecursevly(sourceStack,auxStack,destinationStack,size-1);
            moveHanoyRecursevly(sourceStack,destinationStack,auxStack,1);
            moveHanoyRecursevly(auxStack,destinationStack,sourceStack,size-1);
        }

    }
}
