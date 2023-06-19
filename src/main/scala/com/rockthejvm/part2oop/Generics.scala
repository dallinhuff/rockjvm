package com.rockthejvm.part2oop

import com.rockthejvm.part2oop.Generics.MyList

object Generics {

  abstract class MyList[A] {
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  val listOfInts = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

  // multiple generic types
  trait MyMap[K, V]

  object MyList {
    def fromTwoElements[A](elem1: A, elem2: A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }

  val listOfStrings = MyList.fromTwoElements[String]("Scala", "Java")
  // compiler can infer generic types
  val listOfStrings_v2 = MyList.fromTwoElements("Scala", "Java")
  val listOfStrings_v3 = new NonEmpty("Scala", new NonEmpty("Java", new Empty))

  def main(args: Array[String]): Unit = {

  }
}
