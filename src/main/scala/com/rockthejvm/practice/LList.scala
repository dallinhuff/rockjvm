package com.rockthejvm.practice

import scala.annotation.tailrec

trait Predicate[A] {
  def test(element: A): Boolean
}

val evenPredicate = new Predicate[Int] {
  override def test(element: Int): Boolean =
    element % 2 == 0
}

trait Transformer[A, B] {
  def transform(element: A): B
}

val doubler = new Transformer[Int, Int] {
  override def transform(element: Int): Int =
    element * 2
}

val incList = new Transformer[Int, LList[Int]] {
  override def transform(element: Int): LList[Int] =
    Cons(element, Cons(element + 1, Empty()))
}

trait StringToInt extends Transformer[String, Int] {
  override def transform(element: String): Int =
    element.toInt
}

abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean = this == Empty()
  def add(element: A): LList[A] = Cons(element, this)
  infix def ++(other: LList[A]): LList[A]
  def map[B](transformer: Transformer[A, B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
  def find(predicate: Predicate[A]): A
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LList[A] = throw new NoSuchElementException
  override def toString: String = "[]"
  override infix def ++(other: LList[A]): LList[A] = other
  override def map[B](t: Transformer[A, B]): LList[B] = Empty()
  override def filter(p: Predicate[A]): LList[A] = Empty()
  override def flatMap[B](t: Transformer[A, LList[B]]): LList[B] = Empty()
  override def find(predicate: Predicate[A]): A = throw new NoSuchElementException
}

case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
  override def toString: String = {
    @tailrec
    def concatElements(remainder: LList[A], acc: String): String = {
      if remainder.isEmpty then acc
      else concatElements(remainder.tail, s"$acc, ${remainder.head}")
    }
    s"[${concatElements(this.tail, s"$head")}]"
  }

  override infix def ++(other: LList[A]): LList[A] = Cons(head, tail ++ other)

  override def map[B](t: Transformer[A, B]): LList[B] =
    Cons(t.transform(head), tail.map(t))

  override def flatMap[B](t: Transformer[A, LList[B]]): LList[B] =
    t.transform(head) ++ tail.flatMap(t)

  override def filter(p: Predicate[A]): LList[A] =
    if p.test(head) then Cons(head, tail.filter(p))
    else tail.filter(p)

  override def find(predicate: Predicate[A]): A =
    if predicate.test(head) then head
    else tail.find(predicate)
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = Empty[Int]()
    println(empty)
    println(empty.isEmpty)

    val first3Nums = Cons(1, Cons(2, Cons(3, empty)))
    println(first3Nums)
    println(first3Nums.isEmpty)

    val first3Nums_v2 = empty.add(1).add(2).add(3)
    println(first3Nums_v2)
    println(first3Nums_v2.isEmpty)

    val doubledNums = first3Nums.map(doubler)
    println(doubledNums)

    val nestedNums = first3Nums.map(incList)
    println(nestedNums)

    val evenNums = first3Nums.filter(evenPredicate)
    println(evenNums)

    val concatted = first3Nums ++ first3Nums_v2
    println(concatted)

    val flatMapped = first3Nums.flatMap(incList)
    println(flatMapped)

    val twoFound = first3Nums.find(evenPredicate)
    println(twoFound)

    // throws no such element exception
//    val sevenFound = first3Nums.find((element: Int) => element == 7)
//    println(sevenFound)
  }
}
