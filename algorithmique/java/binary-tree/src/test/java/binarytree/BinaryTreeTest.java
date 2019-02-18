package binarytree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {

    private BinaryTree<Integer, String> root;

    BinaryTreeTest() {
        root = new BinaryTree<>(0, "a");
        BinaryTree<Integer, String> a = new BinaryTree<>(1, "b");
        BinaryTree<Integer, String> b = new BinaryTree<>(2, "c");
        BinaryTree<Integer, String> c = new BinaryTree<>(3, "d");
        BinaryTree<Integer, String> d = new BinaryTree<>(4, "e");
        BinaryTree<Integer, String> e = new BinaryTree<>(5, "f");
        BinaryTree<Integer, String> f = new BinaryTree<>(6, "g");
        a.setLeft(c);
        a.setRight(d);
        b.setLeft(e);
        b.setRight(f);
        root.setLeft(a);
        root.setRight(b);
    }

    @Test
    @DisplayName("Given a binary tree, we display the prefix traversal of the id of the nodes")
    public void testPrefix() {
        String prefix = root.prefix();
        System.out.println(prefix);
        assertEquals("0134256", prefix);
    }

    @Test
    @DisplayName("Given a binary tree, we display the infix traversal of the id of the nodes")
    public void testInfix() {
        String infix = root.infix();
        System.out.println(infix);
        assertEquals("3140526", infix);
    }

    @Test
    @DisplayName("Given a binary tree, we display the postfix traversal of the id of the nodes")
    public void testPostfix() {
        String postfix = root.postfix();
        System.out.println(postfix);
        assertEquals("3415620", postfix);
    }

}
