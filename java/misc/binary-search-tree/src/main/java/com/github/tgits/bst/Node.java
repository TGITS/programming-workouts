/**
 * @author TGITS
 *
 * Node is the main class for implementing a Binary Search Tree (BST)
 *
 * It has 2 type parameters.
 *
 * The type K must extends Comparable<K> : this is the type of the key
 *
 * The type T is for the content of the node.
 *
 * The key on which the properties of the BST are verified is distinct from the content of the node wich can be something
 * else not necessarily comparable.
 * */

package com.github.tgits.bst;

import java.util.Random;

public class Node<K extends Comparable<K>,T> {
    private static final Random random = new Random();
    private final K key;
    private final T content;
    private Node<K,T> parent;
    private Node<K,T> left;
    private Node<K,T> right;

    public Node(K key,T content) {
        this.key = key;
        this.content = content;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public void setParent(Node<K,T> parent){
        this.parent = parent;
    }

    private void setLeft(Node<K,T> left){
        this.left = left;
        this.left.setParent(this);
    }

    private void setRight(Node<K,T> right){
        this.right = right;
        this.right.setParent(this);

    }

    public K getKey() {
        return key;
    }

    public T getContent() {
        return content;
    }

    private void addLeft(Node<K,T> node){
        if(this.hasLeft()){
            this.left.addNode(node);
        }
        else {
            this.setLeft(node);
        }
    }

    private void addRight(Node<K,T> node){
        if(this.hasRight()){
            this.right.addNode(node);
        }
        else {
            this.setRight(node);
        }
    }
    /**
     * Soit x un noeud d'un arbre binaire de recherche.
     * Si y est noeud du sous-arbre gauche de x alors clé[y] <= clé[x].
     * Si y est un noeud du sous-arbre droit de x alors clé[x] <= clé[y]
     **/
    public void addNode(Node<K,T> node){
        if(node == null) {
            throw new IllegalArgumentException("You cannot pass a null as argument to this method");
        }

        if(this.key.compareTo(node.getKey()) > 0) {
            this.addLeft(node);
        } else if(this.key.compareTo(node.getKey()) < 0) {
            this.addRight(node);
        } else if(this.key.compareTo(node.getKey()) == 0){
            if(random.nextBoolean()) {
                this.addLeft(node);
            }
            else {
                this.addRight(node);
            }
        }
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }
}
