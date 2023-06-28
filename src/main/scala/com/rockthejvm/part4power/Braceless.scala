package com.rockthejvm.part4power

object Braceless:

  // braceless-if
  val aCond: String =
    if 2 > 3 then "bigger"
    else "smaller"

  // has significant indentation
  val aCondExpr: String =
    if 2 > 3 then
      val aLocalVal = 7
      s"put $aLocalVal here"
    else
      val anotherVal = 2
      s"put $anotherVal there instead"

  // braceless for-comp
  val aComprehension: Seq[String] =
    for
      n <- List(1, 2, 3)
      s <- List("Black", "White")
    yield s"$s : $n"

  // braceless pattern matching
  val meaningOfLife = 42
  val aMatch: String = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // braceless methods
  def computeMeaningOfLife(arg: Int): Int =
    val partialResult = 40
    partialResult + 2

  // braceless class
  class Animal: // colon required for class, trait, object, enum, etc.
    def eat(): Unit =
      println("I'm eating")
      // + lots more lines
    end eat

    def grow(): Unit =
      println("I'm growing")

  end Animal // optional, but can help with readability for long classes

  // braceless anonymous class
  val aSpecialAnimal: Animal = new Animal:
    override def eat(): Unit =
      println("I'm eating in a special way")

  def main(args: Array[String]): Unit =
    println(computeMeaningOfLife(99))