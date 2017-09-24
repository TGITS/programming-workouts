package com.github.tgits.bst;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void createNode() {
        Node<Integer,String> n1 = new Node<Integer,String>(1,"One");
        Assert.assertTrue("This node should be a root", n1.isRoot());
        Assert.assertTrue("This node should be a leaf", n1.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n1.hasLeft());
        Assert.assertTrue("This node shouldn't have a right child", !n1.hasRight());

        Node<Integer,String> n2 = new Node<Integer,String>(2,"Two");
        Assert.assertTrue("This node should be a root", n2.isRoot());
        Assert.assertTrue("This node should be a leaf", n2.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n2.hasLeft());
        Assert.assertTrue("This node shouldn't have a right child", !n2.hasRight());

        Node<Integer,String> n3 = new Node<Integer,String>(3,"Three");
        Assert.assertTrue("This node should be a root", n3.isRoot());
        Assert.assertTrue("This node should be a leaf", n3.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n3.hasLeft());
        Assert.assertTrue("This node shouldn't have a right child", !n3.hasRight());

        n1.addNode(n2);
        Assert.assertTrue("This node should be a root", n1.isRoot());
        Assert.assertTrue("This node shouldn't be a leaf", !n1.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n1.hasLeft());
        Assert.assertTrue("This node should have a right child", n1.hasRight());
        Assert.assertTrue("This node shouldn't be a root", !n2.isRoot());
        Assert.assertTrue("This node should be a leaf", n2.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n2.hasLeft());
        Assert.assertTrue("This node shouldn't have a right child", !n2.hasRight());

        n1.addNode(n3);
        Assert.assertTrue("This node should be a root", n1.isRoot());
        Assert.assertTrue("This node shouldn't be a leaf", !n1.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n1.hasLeft());
        Assert.assertTrue("This node should have a right child", n1.hasRight());
        Assert.assertTrue("This node shouldn't have a left child", !n2.hasLeft());
        Assert.assertTrue("This node should have a right child", n2.hasRight());
        Assert.assertTrue("This node shouldn't be a leaf", !n2.isLeaf());
        Assert.assertTrue("This node shouldn't be a root", !n3.isRoot());
        Assert.assertTrue("This node should be a leaf", n3.isLeaf());
        Assert.assertTrue("This node shouldn't have a left child", !n3.hasLeft());
        Assert.assertTrue("This node shouldn't have a right child", !n3.hasRight());
    }
}
