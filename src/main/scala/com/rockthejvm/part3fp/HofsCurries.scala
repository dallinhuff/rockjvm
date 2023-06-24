package com.rockthejvm.part3fp

import scala.annotation.tailrec

object HofsCurries {

  // HOFS: functions that use functions as args or results
  val aHof: (Int, Int => Int) => Int = (x, func) => func(x + 1)
  val anotherHof: Int => Int => Int = x => y => y + 2 * x

  val superFunc: (Int, (String, Int => Boolean) => Int) => (Int => Int) =
    (x, func) => y => x + func("Str", a => y > 0 && a == 2)

  // examples: map, filter, flatMap

  // more examples
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if n <= 0 then x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  def nTimes_v2(f: Int => Int, n: Int): Int => Int =
    if n <= 0 then (x: Int) => x
    else (x: Int) => nTimes_v2(f, n - 1)(f(x))

  // currying
  val superAdder = (x: Int) => (y: Int) => x + y
  val plus4 = superAdder(4)
  val fivePlus4 = plus4(5)
  val fivePlus4_v2 = superAdder(4)(5)

  // curried methods: methods with multiple argument lists
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val stdFormat = curriedFormatter("%4.2f")
  val preciseFormat = curriedFormatter("%10.8f")


  def toCurry[A, B, C](f: (A, B) => C): A => B => C =
    x => y => f(x, y)

  val superAdder_v2 = toCurry[Int, Int, Int](_ + _)

  def fromCurry[A, B, C](f: A => B => C): (A, B) => C =
    (x, y) => f(x)(y)

  val normalAdder = fromCurry(superAdder_v2)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  val incrementor: Int => Int = _ + 1
  val doubler: Int => Int = _ * 2
  val doublerPlusOne = compose(incrementor, doubler)

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val plusOneDoubled = andThen(incrementor, doubler)


  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(stdFormat(math.Pi))
    println(preciseFormat(math.Pi))
  }
}
