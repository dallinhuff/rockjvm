package com.rockthejvm.part1basics

import scala.annotation.tailrec

object Recursion {

  def sumUntil(n: Int): Int = {
    if n <= 0 then 0
    else n + sumUntil(n - 1)  // STACK recursion
  }

  def sumUntil_v2(n: Int): Int = {
    @tailrec
    def sumUntilTailRec(x: Int, acc: Int): Int = {
      if x <= 0 then acc
      else sumUntilTailRec(x - 1, acc + x) // TAIL recursion
    }
    sumUntilTailRec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int = {
    if a > b then 0
    else a + sumNumbersBetween(a + 1, b)
  }

  def sumNumbersBetween_v2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailRec(curr: Int, acc: Int): Int = {
      if curr > b then acc
      else sumTailRec(curr + 1, acc + curr)
    }
    sumTailRec(a, 0)
  }

  // concat a string n times
  def repeat(str: String, n: Int): String = {
    @tailrec
    def repeatTail(timesLeft: Int, acc: String = ""): String = {
      if timesLeft <= 0 then acc
      else repeatTail(timesLeft - 1, acc + str)
    }
    repeatTail(n)
  }

  def fib(n: Int): Int = {
    @tailrec
    def fibTail(i: Int, last: Int, prev: Int): Int = {
      if i >= n then last
      else fibTail(i + 1, last + prev, last)
    }
    if n <= 2 then 1 else fibTail(2, 1, 1)
  }

  def main(args: Array[String]): Unit = {
    println(sumUntil(10))
    println(sumUntil_v2(10))
    println(sumUntil_v2(20000))
    println(repeat("Scala", 5))
    println(fib(7))
  }
}
