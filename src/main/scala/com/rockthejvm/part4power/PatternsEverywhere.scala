package com.rockthejvm.part4power

object PatternsEverywhere {

  val potentialFailure: String = try {
    // some arbitrary code that can throw
    "a string"
  } catch { // catches are actually pattern matches
    case e: RuntimeException => "runtime exception"
    case npe: NullPointerException => "null pointer exception"
    case _ => "some other exception"
  }

  val aList: List[Int] = List(1, 2, 3, 4)
  val evenNumbers: List[Int] = for {
    n <- aList if n % 2 == 0 // generators in for-comps are based on pattern matching
  } yield 10 * n

  val tuples: List[(Int, Int)] = List((1, 2), (3, 4))
  val filterTuples: List[Int] = for {
    (first, second) <- tuples if first < 3
  } yield second * 100

  // tuple deconstruction
  val aTuple: (Int, Int, Int) = (1, 2, 3)
  val (a, b, c) = aTuple

  // other pattern-matching deconstructions
  val head :: tail = tuples

  def main(args: Array[String]): Unit = {

  }
}
