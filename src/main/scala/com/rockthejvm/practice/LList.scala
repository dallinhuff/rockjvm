package com.rockthejvm.practice

import scala.annotation.tailrec

abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean = this == Empty()
  def add(element: A): LList[A] = Cons(element, this)
  infix def ++(other: LList[A]): LList[A]
  def map[B](transform: A => B): LList[B]
  def filter(test: A => Boolean): LList[A]
  def flatMap[B](transform: A => LList[B]): LList[B]
  def find(test: A => Boolean): A
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LList[A] = throw new NoSuchElementException
  override def toString: String = "[]"
  override infix def ++(other: LList[A]): LList[A] = other
  override def map[B](transform: A => B): LList[B] = Empty()
  override def filter(test: A => Boolean): LList[A] = Empty()
  override def flatMap[B](transform: A => LList[B]): LList[B] = Empty()
  override def find(test: A => Boolean): A = throw new NoSuchElementException
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

  override def map[B](transform: A => B): LList[B] =
    Cons(transform(head), tail.map(transform))

  override def flatMap[B](transform: A => LList[B]): LList[B] =
    transform(head) ++ tail.flatMap(transform)

  override def filter(test: A => Boolean): LList[A] =
    if test(head) then Cons(head, tail.filter(test))
    else tail.filter(test)

  override def find(test: A => Boolean): A =
    if test(head) then head
    else tail.find(test)
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

    val doubledNums = first3Nums.map(_ * 2)
    println(doubledNums)

    val nestedNums = first3Nums.map(x => Cons(x, Cons(x + 1, Empty())))
    println(nestedNums)

    val evenNums = first3Nums.filter(_ % 2 == 0)
    println(evenNums)

    val concatted = first3Nums ++ first3Nums_v2
    println(concatted)

    val flatMapped = first3Nums.flatMap(x => Cons(x, Cons(x + 1, Empty())))
    println(flatMapped)

    val twoFound = first3Nums.find(_ % 2 == 0)
    println(twoFound)
  }
}
