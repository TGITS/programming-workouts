/**
 * 
 */
package com.github.tgits;

/**
 * @author cvaudry
 * 
 *         TODO
 * 
 *         - rendre le test unitaire plus propre et plus complet
 * 
 *         - améliorer l'affichage en ajoutant un séparateur (peut-être passer
 *         par une liste et un affichage idoine de cette denrière sous forme de
 *         chaine)
 */
public class BinaryTree<T> {
	BinaryTree<T> left;
	BinaryTree<T> right;
	T content;
	int id;

	public BinaryTree() {
		this.id = 0;
		this.content = null;
		this.left = null;
		this.right = null;
	}

	public BinaryTree(int id) {
		this.id = id;
		this.content = null;
		this.left = null;
		this.right = null;
	}

	public BinaryTree(int id, T value) {
		this.id = id;
		this.content = value;
		this.left = null;
		this.right = null;
	}

	public void setRight(BinaryTree<T> t) {
		this.right = t;
	}

	public BinaryTree<T> getRight() {
		return right;
	}

	public void setLeft(BinaryTree<T> t) {
		this.left = t;
	}

	public BinaryTree<T> getLeft() {
		return left;
	}

	public T getContent() {
		return content;
	}

	public String prefix() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		if (this.left != null) {
			sb.append(this.left.prefix());
		}

		if (this.right != null) {
			sb.append(this.right.prefix());
		}

		return sb.toString();
	}

	public String infix() {
		StringBuilder sb = new StringBuilder();

		if (this.left != null) {
			sb.append(this.left.infix());
		}

		sb.append(id);

		if (this.right != null) {
			sb.append(this.right.infix());
		}

		return sb.toString();
	}

	public String postfix() {
		StringBuilder sb = new StringBuilder();

		if (this.left != null) {
			sb.append(this.left.postfix());
		}

		if (this.right != null) {
			sb.append(this.right.postfix());
		}

		sb.append(id);

		return sb.toString();
	}
}
