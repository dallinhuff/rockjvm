package com.rockthejvm.practice

import scala.annotation.tailrec

abstract class LList {
  def head: Int
  def tail: LList
  def isEmpty: Boolean
  def add(element: Int): LList = new Cons(element, this)
}

class Empty extends LList {
  override def head: Int = throw new NoSuchElementException
  override def tail: LList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def toString: String = "[]"
}

class Cons(override val head: Int, override val tail: LList) extends LList {
  override def isEmpty: Boolean = false
  override def toString: String = {
    @tailrec
    def concatElements(remainder: LList, acc: String): String = {
      if remainder.isEmpty then acc
      else concatElements(remainder.tail, s"$acc, ${remainder.head}")
    }
    s"[${concatElements(this.tail, s"$head")}]"
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = new Empty
    println(empty)
    println(empty.isEmpty)

    val first3Nums = new Cons(1, new Cons(2, new Cons(3, empty)))
    println(first3Nums)
    println(first3Nums.isEmpty)

    val first3Nums_v2 = empty.add(1).add(2).add(3)
    println(first3Nums_v2)
    println(first3Nums_v2.isEmpty)
  }
}
