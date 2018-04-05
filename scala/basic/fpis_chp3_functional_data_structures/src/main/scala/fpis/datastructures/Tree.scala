package fpis.datastructures

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  /* Correction on https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/datastructures/Tree.scala */

  /* Exercise 3.25
   * Write a function size that counts the number of nodes (leaves and branches) in a tree.
   * */
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }

  /* Exercise 3.26
   * Write a function maximum that returns the maximum element in a Tree[Int]. (Note:
   * In Scala, you can use x.max(y) or x max y to compute the maximum of two integers x and y.)
   * */
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(v) => v
    case Branch(l, r) => maximum(l) max maximum(r)
  }

  /* Exercise 3.27
   * Write a function depth that returns the maximum path length from the root of a tree to any leaf.
   * */
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }

  /* Exercise 3.28
   * Write a function map, analogous to the method of the same name on List,
   * that modifies each element in a tree with a given function.
   * */
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

  /* Exercise 3.29
   * Generalize size, maximum, depth, and map, writing a new function fold that abstracts
   * over their similarities. Re-implement them in terms of this more general function. Can
   * you draw an analogy between this fold function and the left and right folds for List?
   * */
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l, r) => g((fold(l)(f)(g)), (fold(r)(f)(g)))
  }

  def size_2[A](t: Tree[A]): Int = fold[A, Int](t)(_ => 1)((b1, b2) => (1 + b1 + b2))

  def maximum_2(t: Tree[Int]): Int = fold[Int, Int](t)(a => a)((b1, b2) => b1 max b2)

  def depth_2[A](t: Tree[A]): Int = fold[A, Int](t)(_ => 1)((b1, b2) => 1 + (b1 max b2))

  def map_2[A, B](t: Tree[A])(f: A => B): Tree[B] = fold[A, Tree[B]](t)(a => Leaf(f(a)))((b1, b2) => Branch(b1, b2))
}