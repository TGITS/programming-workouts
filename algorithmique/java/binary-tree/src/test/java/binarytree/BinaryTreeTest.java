package binarytree;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @DisplayName(("Given a binary tree, we obtain the list of nodes as a list of tuples (id, content) in a prefix order"))
    public void listOfNodesInPrefixOrder() {
        List<Tuple2<Integer,String>> nodesInPrefixOrder = this.root.getNodesInPrefixOrder();
        List<Tuple2<Integer,String>> expectedNodesList = List.of(
                Tuple.of(0, "a"),
                Tuple.of(1, "b"),
                Tuple.of(3, "d"),
                Tuple.of(4, "e"),
                Tuple.of(2, "c"),
                Tuple.of(5, "f"),
                Tuple.of(6, "g")
        );
        assertEquals(expectedNodesList,nodesInPrefixOrder);
        System.out.println(nodesInPrefixOrder.toString());
    }

    @Test
    @DisplayName("Given a binary tree, we display prefix traversal of the id of the nodes")
    public void nodesInPrefixOrderAsString() {
        String prefix = root.displayPrefixTraversal();
        System.out.println(prefix);
        assertEquals("[(0, a), (1, b), (3, d), (4, e), (2, c), (5, f), (6, g)]", prefix);
    }


    @Test
    @DisplayName(("Given a binary tree, we obtain the list of nodes as a list of tuples (id, content) in an infix order"))
    public void listOfNodesInInfixOrder() {
        List<Tuple2<Integer,String>> nodesInInfixOrder = this.root.getNodesInInfixOrder();
        List<Tuple2<Integer,String>> expectedNodesList = List.of(
                Tuple.of(3, "d"),
                Tuple.of(1, "b"),
                Tuple.of(4, "e"),
                Tuple.of(0, "a"),
                Tuple.of(5, "f"),
                Tuple.of(2, "c"),
                Tuple.of(6, "g")
        );
        assertEquals(expectedNodesList,nodesInInfixOrder);
    }

    @Test
    @DisplayName("Given a binary tree, we obtain a String with le tuple (id,content) in the infix order")
    public void nodesInInfixOrderAsString() {
        String infix = root.displayInfixTraversal();
        System.out.println(infix);
        assertEquals("[(3, d), (1, b), (4, e), (0, a), (5, f), (2, c), (6, g)]", infix);
    }

    @Test
    @DisplayName(("Given a binary tree, we obtain the list of nodes as a list of tuples (id, content) in a postfix traversal"))
    public void listOfNodesInPostfixOrder() {
        List<Tuple2<Integer,String>> nodesInPostfixOrder = this.root.getNodesInPostfixOrder();
        List<Tuple2<Integer,String>> expectedNodesList = List.of(
                Tuple.of(3, "d"),
                Tuple.of(4, "e"),
                Tuple.of(1, "b"),
                Tuple.of(5, "f"),
                Tuple.of(6, "g"),
                Tuple.of(2, "c"),
                Tuple.of(0, "a")
        );
        assertEquals(expectedNodesList,nodesInPostfixOrder);
        System.out.println(nodesInPostfixOrder.toString());
    }

    @Test
    @DisplayName("Given a binary tree, we display the postfix traversal of the id of the nodes")
    public void nodesInAPostfixOrderAsString() {
        String postfix = root.displayPostfixTraversal();
        System.out.println(postfix);

        assertEquals("[(3, d), (4, e), (1, b), (5, f), (6, g), (2, c), (0, a)]", postfix);
    }

}
