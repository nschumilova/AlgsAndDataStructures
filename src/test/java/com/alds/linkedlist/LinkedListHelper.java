package com.alds.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class LinkedListHelper {


    public static <T> void initializeList(CustomLinkedList<T> list, T...values){
        for (T val: values)
            list.addFirst(val);
    }

    public static <T> void initializeList(List<T> list, T...values){
        for (T val: values)
            list.add(val);
    }
}
