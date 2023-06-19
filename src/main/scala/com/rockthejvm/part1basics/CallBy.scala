package com.rockthejvm.part1basics

object CallBy {
  // CBV - call by value - args are evaluated before invocation
  def increment(arg: Int): Int = arg + 1
  val incremented = increment(22 + 10) // increment(32)

  // CBN - call by name - args are passed as an expression (LITERALLY)
  def incrementByName(arg: => Int): Int = arg + 1
  val incrementedByName = incrementByName(22 + 10)

  def printTwiceByVal(x: Long): Unit = {
    println("by val: " + x)
    println("by val: " + x)
  }

  def printTwiceByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  def infinite(): Int = {
    // infinite recursion, stack overflow imminent
    1 + infinite()
  }

  def printFirst(x: Int, y: => Int) = println(x)

  def main(args: Array[String]): Unit = {
    println(System.nanoTime())
    println(System.nanoTime())
    printTwiceByVal(System.nanoTime())
    printTwiceByName(System.nanoTime())

    printFirst(4, infinite()) // this is fine because y will never be evaluated
  }
}
