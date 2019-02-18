package binarytree;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class BinaryTree<K extends Comparable<K>,V> {
    @NonNull
    private final K id;
    @NonNull
    private V content;
    private BinaryTree<K, V> right;
    private BinaryTree<K, V> left;

    public List<Tuple2<K, V>> getNodesInPrefixOrder() {
        List<Tuple2<K, V>> nodesInPrefixOrder = new ArrayList<>();
        nodesInPrefixOrder.add(Tuple.of(id, content));

        if (this.left != null) {
            nodesInPrefixOrder.addAll(left.getNodesInPrefixOrder());
        }

        if (this.right != null) {
            nodesInPrefixOrder.addAll(right.getNodesInPrefixOrder());
        }

        return nodesInPrefixOrder;
    }

    public String displayPrefixTraversal() {
        return getNodesInPrefixOrder().toString();
    }

    public List<Tuple2<K, V>> getNodesInInfixOrder() {
        List<Tuple2<K, V>> nodesInInfixOrder = new ArrayList<>();

        if (this.left != null) {
            nodesInInfixOrder.addAll(this.left.getNodesInInfixOrder());
        }

        nodesInInfixOrder.add(Tuple.of(id, content));

        if (this.right != null) {
            nodesInInfixOrder.addAll(this.right.getNodesInInfixOrder());
        }

        return nodesInInfixOrder;
    }

    public String displayInfixTraversal() {
        return getNodesInInfixOrder().toString();
    }

    public List<Tuple2<K, V>> getNodesInPostfixOrder() {
        List<Tuple2<K, V>> nodesInPostfixOrder = new ArrayList<>();

        if (this.left != null) {
            nodesInPostfixOrder.addAll(this.left.getNodesInPostfixOrder());
        }

        if (this.right != null) {
            nodesInPostfixOrder.addAll(this.right.getNodesInPostfixOrder());
        }

        nodesInPostfixOrder.add(Tuple.of(id, content));

        return nodesInPostfixOrder;
    }

    public String displayPostfixTraversal() {
        return getNodesInPostfixOrder().toString();
    }



}
