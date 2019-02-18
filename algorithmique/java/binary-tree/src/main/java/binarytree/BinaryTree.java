package binarytree;

public class BinaryTree<K extends Comparable<K>,V> {
	private K id;
	private V content;
	private BinaryTree<K, V> right;
	private BinaryTree<K, V> left;

	public BinaryTree(K id, V value) {
		this.id = id;
		this.content = value;
		this.right = null;
		this.left = null;
	}

	public void setRight(BinaryTree<K, V> right) {
		this.right = right;
	}

	public BinaryTree<K, V> getRight() {
		return right;
	}

	public void setLeft(BinaryTree<K, V> left) {
		this.left = left;
	}

	public BinaryTree<K, V> getLeft() {
		return left;
	}

	public V getContent() {
		return content;
	}

	public String prefix() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		if (getLeft() != null) {
			sb.append(getLeft().prefix());
		}

		if (getRight() != null) {
			sb.append(getRight().prefix());
		}

		return sb.toString();
	}

	public String infix() {
		StringBuilder sb = new StringBuilder();

		if (getLeft() != null) {
			sb.append(getLeft().infix());
		}

		sb.append(id);

		if (getRight() != null) {
			sb.append(getRight().infix());
		}

		return sb.toString();
	}

	public String postfix() {
		StringBuilder sb = new StringBuilder();

		if (getLeft() != null) {
			sb.append(getLeft().postfix());
		}

		if (getRight() != null) {
			sb.append(getRight().postfix());
		}

		sb.append(id);

		return sb.toString();
	}
}
