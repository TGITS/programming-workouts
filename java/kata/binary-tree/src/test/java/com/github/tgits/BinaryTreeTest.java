/**
 * 
 */
package com.github.tgits;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author cvaudry
 *
 */
public class BinaryTreeTest {

	/**
	 * Test method for {@link com.github.tgits.BinaryTree#prefix()}.
	 */
	@Test
	public void testPrefix() {
		BinaryTree<String> root = new BinaryTree<String>();
		BinaryTree<String> a = new BinaryTree<String>(1,"a");
		BinaryTree<String> b = new BinaryTree<String>(2,"b");
		BinaryTree<String> c = new BinaryTree<String>(3,"c");
		BinaryTree<String> d = new BinaryTree<String>(4,"d");
		BinaryTree<String> e = new BinaryTree<String>(5,"e");
		BinaryTree<String> f = new BinaryTree<String>(6,"f");
		a.setLeft(c);
		a.setRight(d);
		b.setLeft(e);
		b.setRight(f);
		root.setLeft(a);
		root.setRight(b);
		String prefix = root.prefix();
		System.out.println(prefix);
		assertEquals("0134256", prefix);
	}

	/**
	 * Test method for {@link com.github.tgits.BinaryTree#infix()}.
	 */
	@Test
	public void testInfix() {
		BinaryTree<String> root = new BinaryTree<String>();
		BinaryTree<String> a = new BinaryTree<String>(1,"a");
		BinaryTree<String> b = new BinaryTree<String>(2,"b");
		BinaryTree<String> c = new BinaryTree<String>(3,"c");
		BinaryTree<String> d = new BinaryTree<String>(4,"d");
		BinaryTree<String> e = new BinaryTree<String>(5,"e");
		BinaryTree<String> f = new BinaryTree<String>(6,"f");
		a.setLeft(c);
		a.setRight(d);
		b.setLeft(e);
		b.setRight(f);
		root.setLeft(a);
		root.setRight(b);
		String infix = root.infix();
		System.out.println(infix);
		assertEquals("3140526", infix);
	}

	/**
	 * Test method for {@link com.github.tgits.BinaryTree#postfix()}.
	 */
	@Test
	public void testPostfix() {
		BinaryTree<String> root = new BinaryTree<String>();
		BinaryTree<String> a = new BinaryTree<String>(1,"a");
		BinaryTree<String> b = new BinaryTree<String>(2,"b");
		BinaryTree<String> c = new BinaryTree<String>(3,"c");
		BinaryTree<String> d = new BinaryTree<String>(4,"d");
		BinaryTree<String> e = new BinaryTree<String>(5,"e");
		BinaryTree<String> f = new BinaryTree<String>(6,"f");
		a.setLeft(c);
		a.setRight(d);
		b.setLeft(e);
		b.setRight(f);
		root.setLeft(a);
		root.setRight(b);
		String postfix = root.postfix();
		System.out.println(postfix);
		assertEquals("3415620", postfix);
	}

}
