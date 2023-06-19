package com.rockthejvm.part1basics

object Expressions {

  // expressions: structures that can be evaluated to a value
  val meaningOfLife = 40 + 2

  // mathematical expressions: +, -, *, /, bitwise |, &, <<, >>, >>>
  val mathExp = 2 + 3 * 4

  // comparison expressions: <, <=, >, >=, ==, !=
  val equalityTest = 1 == 2

  // boolean expressions: !, ||, &&
  val nonEqualityTest = !equalityTest

  // instructions vs expressions
  // expressions are evaluated, instructions are executed
  // in Scala, we think in terms of expressions

  // if expressions
  val aCondition = true
  val anIfExpression = if aCondition then 45 else 99

  // code block expressions
  val aCodeBlock = {
    val localValue = 78
    // return value = last expression in block
    localValue + 54
  }

  /** Exercise */
  val someVal = {
    2 < 3 // true
  }

  val otherVal = {
    if someVal then 239 else 986
    42
  } // 42

  val yetAnotherVal = println("Scala") // Unit

  def main(args: Array[String]): Unit = {
//    println(meaningOfLife)
//    println(anIfExpression)
//    println(aCodeBlock)

    println("EXERCISES")
    println(someVal)
    println(otherVal)
    println(yetAnotherVal)
  }
}
