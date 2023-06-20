package com.rockthejvm.part2oop

import scala.annotation.tailrec

object Exceptions {

  val aString: String = null // should be avoided in idiomatic code

  // throw exceptions
  // val aWeirdValue: Int = throw new NullPointerException // returns type Nothing

  // type Throwable
    // type Error (e.g., Stack Overflow Error, Out-of-Memory Error)
    // type Exception (e.g., NullPointerException, NoSuchElementException, etc.)

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No Int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    // handling failure
    // most specific exceptions first
    case e: NullPointerException => 35
    case e: RuntimeException => 54
    //...
  }

  def soCrash(): Unit = {
    def infinite(): Int = 1 + infinite()
    infinite()
  }

  def oomCrash(): Unit = {
    @tailrec
    def bigString(n: Int, acc: String): String = {
      if n == 0 then acc
      else bigString(n - 1, acc + acc)
    }
    bigString(5000000, "Scala")
  }

  class MyException extends RuntimeException {
    override def getMessage: String = "MY EXCEPTION"
  }


  def main(args: Array[String]): Unit = {
    // println(aString.length) // throws a null-pointer exception
    println(potentialFail)
    // println(soCrash()) // throws a stack overflow error
    println(oomCrash()) // throws an out of memory error
  }
}
