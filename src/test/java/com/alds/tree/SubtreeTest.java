package com.alds.tree;
import static org.junit.Assert.*;

import org.junit.Test;
public class SubtreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testHasSubtreeWhenNullInput(){
        BinaryTreeProcessor.hasSubtree(null,null);
    }

    @Test
    public void testHasNotSubtreeWhenTreeIsEmpty(){
        BinaryTree<Integer> tree = new BinaryTree<>();
        BinaryTree<Integer> subtree = new BinaryTree<>(new BinaryTreeNode<>(2));
        assertFalse(BinaryTreeProcessor.hasSubtree(tree,subtree));
    }
    @Test
    public void testHasNotSubtreeWhenSubtreeIsEmpty(){
        BinaryTree<Integer> subtree = new BinaryTree<>(new BinaryTreeNode<>(2));
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertFalse(BinaryTreeProcessor.hasSubtree(tree,subtree));
    }

    @Test
    public void testHasSubtreeWhenOneElementInEachTree(){
        BinaryTree<Integer> tree = new BinaryTree<>(new BinaryTreeNode<>(2));
        BinaryTree<Integer> subtree = new BinaryTree<>(new BinaryTreeNode<>(2));
        assertTrue(BinaryTreeProcessor.hasSubtree(tree,subtree));
    }

    @Test
    public void testHasSubtreeWhenAllDuplicates(){
        BinaryTree<Integer> tree = new BinaryTree<>(buildDuplicatesLeftBranchLongerForTree());
        BinaryTree<Integer> subtree = new BinaryTree<>(buildDuplicatesLeftBranchLongerForSubtree());
        assertTrue(BinaryTreeProcessor.hasSubtree(tree,subtree));
    }

    @Test
    public void testHasNotSubtreeWhenAllDuplicates(){
        BinaryTree<Integer> tree = new BinaryTree<>(buildDuplicatesLeftBranchLongerForTree());
        BinaryTree<Integer> subtree = new BinaryTree<>(buildDuplicatesRightBranchLongerForSubtree());
        assertFalse(BinaryTreeProcessor.hasSubtree(tree,subtree));
    }

    private BinaryTreeNode<Integer> buildDuplicatesLeftBranchLongerForTree(){
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(2);
        node.appendLeft(2);
        node.appendLeft(2);
        node.appendLeft(2);
        node.appendRight(2);
        node.getLeftChild().appendRight(2);
        return node;
    }
    private BinaryTreeNode<Integer> buildDuplicatesLeftBranchLongerForSubtree(){
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(2);
        node.appendLeft(2);
        node.appendLeft(2);
        node.appendRight(2);
        return node;
    }
    private BinaryTreeNode<Integer> buildDuplicatesRightBranchLongerForSubtree(){
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(2);
        node.appendRight(2);
        node.appendRight(2);
        node.appendLeft(2);
        return node;
    }


}
