package com.alds.tree;



import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DepthListsTest {

    @Test
    public void testConstructDepthListsWhenTreeIsEmpty(){
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertTrue(tree.constructDepthLists().isEmpty());
    }

    @Test
    public void testConstructDepthListsWhenTreeHasOneElement(){
        BinaryTree<Integer> tree = new BinaryTree<>(new BinaryTreeNode<>(2));
        Map<Integer, List<Integer>> map = tree.constructDepthLists();
        assertEquals(1,map.size());
        assertTrue(map.get(0).size()==1);
        assertEquals(2,map.get(0).get(0).intValue());
    }

    @Test
    public void testConstructDepthListsWhenTreeHasOneLongBranch(){
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0);
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        root.appendRight(1);
        root.appendRight(2);
        root.appendRight(3);
        Map<Integer, List<Integer>> expected = getExpectedForTreeWithOneLongBranch(4);
        Map<Integer, List<Integer>> result = tree.constructDepthLists();
        assertEquals(4,result.size());
        assertEquals(expected, result);
    }

    @Test
    public void testConstructDepthListsWhenTreeHasSeveralBranches(){
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0);
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        root.appendRight(2);
        root.appendRight(5);
        root.appendLeft(1);
        root.appendLeft(3);
        root.getLeftChild().appendRight(4);
        root.getLeftChild().appendRight(6);
        Map<Integer, List<Integer>> expected = getExpectedForTreeWithSeveralBranches(4);
        Map<Integer, List<Integer>> result = tree.constructDepthLists();
        assertEquals(4,result.size());
        assertEquals(expected, result);
    }

    private Map<Integer, List<Integer>> getExpectedForTreeWithOneLongBranch(int size){
        Map<Integer, List<Integer>> expected = createEmptyMap(size);
        for(int i = 0; i<size;i++){
            expected.get(i).add(i);
        }
        return expected;
    }
    private Map<Integer, List<Integer>> getExpectedForTreeWithSeveralBranches(int size){
        Map<Integer, List<Integer>> expected = createEmptyMap(size);
        expected.get(0).add(0);
        expected.get(1).add(1);
        expected.get(1).add(2);
        expected.get(2).add(3);
        expected.get(2).add(4);
        expected.get(2).add(5);
        expected.get(3).add(6);
        return expected;
    }

    private Map<Integer, List<Integer>> createEmptyMap(int size){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<size;i++){
            map.put(i, new LinkedList<>());
        }
        return map;
    }
}
