package com.rockthejvm.part1basics

import scala.annotation.tailrec

object Functions {

  def aFunction(a: String, b: Int): String = s"$a-$b"

  val anInvocation = aFunction("word", 11)

  def aNoArgFunction(): Int = 45
  def aNoParamFunction: Int = 22

  // recursion
  def concat(s: String, n: Int): String = {
    if n <= 0 then ""
    else if n == 1 then s
    else s + concat(s, n - 1)
  }

  val scalax3 = concat("Scala", 3)

  // "void" functions
  def aVoidFunction(aString: String): Unit = {
    println(aString)
  }

  // side effects are generally discouraged in functional programming
  def computeWithSideEffect(aString: String): String = {
    aVoidFunction(aString)
    aString + aString
  }

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n - 1)
  }

  def greet(name: String, age: Int) =
    s"Hi, my name is $name and I am $age years old"

  def factorial(n: Int): Int =
    if n < 0 then 0
    else if n == 0 then 1 // 0! = 1
    else n * factorial(n - 1)

  def fib(n: Int): Int =
    if n <= 2 then 1
    else fib(n - 1) + fib(n - 2)

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(anInvocation)
    println(aNoArgFunction())
    println(aNoParamFunction)
    println(scalax3)
    println(computeWithSideEffect("scala"))
    println(aBigFunction(7))
    println(greet("Dallin", 22))
    println(factorial(5))
    println(fib(5))
    println(isPrime(13))
  }
}
