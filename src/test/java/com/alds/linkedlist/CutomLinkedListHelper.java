package com.alds.linkedlist;

public class CutomLinkedListHelper {


    public static <T> void initializeList(CustomLinkedList<T> list, T...values){
        for (T val: values)
            list.addFirst(val);
    }
}
