package com.alds.tree;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static com.alds.util.Helper.checkNotNull;

/**
 * Custom implementation of binary tree node with basic methods set
 *
 * @param <T> type of the data which is stored in the node
 */
public class BinaryTreeNode<T> {
    private T item;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T item) {
        checkNotNull(item);
        this.item = item;
    }

    public void appendRight(T item) {
        rightChild = append(rightChild, item,
                (node) -> node.getRightChild(),
                (parent, child) -> parent.rightChild = child);
    }

    public void appendLeft(T item) {
        leftChild = append(leftChild, item,
                (node) -> node.getLeftChild(),
                (parent, child) -> parent.leftChild = child);
    }

    private BinaryTreeNode<T> append(BinaryTreeNode<T> node, T item,
                                     UnaryOperator<BinaryTreeNode<T>> getter,
                                     BiConsumer<BinaryTreeNode<T>, BinaryTreeNode<T>> setter) {
        if (node == null) {
            return new BinaryTreeNode<>(item);
        } else {
            //node.child = append(node.child, item);
            setter.accept(node, append(getter.apply(node), item, getter, setter));
            return node;
        }

    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public T getItem() {
        return item;
    }

    public  boolean hasLeftChild(){
        return leftChild!=null;
    }

    public  boolean hasRightChild(){
        return rightChild!=null;
    }

}
